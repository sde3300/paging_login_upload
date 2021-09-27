package btc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import btc.dto.TokenDto;
import btc.dto.UserDto;
import btc.jwt.JwtFilter;
import btc.jwt.TokenProvider;
import btc.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/signup")
	public void signup(@RequestBody UserDto user) throws Exception {
		
		loginService.signUp(user);
	}
	
	@PostMapping("/login")
//	public ResponseEntity<TokenDto> authorize(@Valid @RequestBody UserDto user) {
	public Object authorize(@Valid @RequestBody UserDto user) {

//		UsernamePasswordAuthenticationToken 클래스 : 스프링 시큐리티에서 지원
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());
//
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//		
////		SecurityContextHolder : Authentication 정보가 저장되는 곳
		SecurityContextHolder.getContext().setAuthentication(authentication);

//		TokenProvider를 사용하여 jwt 토큰을 생성
		String jwt = tokenProvider.createToken(authentication);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:8080")
	@PostMapping("/login2")
	public Object login(@Valid @RequestBody UserDto user) throws Exception {
		UserDto loginUser = null;
		if (loginService.isUser(user) == 1) {
			loginUser = loginService.login(user);
		}
		
		return loginUser;
	}

}
