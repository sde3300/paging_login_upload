package btc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import btc.jwt.JwtAccessDeniedHandler;
import btc.jwt.JwtAuthenticationEntryPoint;
import btc.jwt.JwtSecurityConfig;
import btc.jwt.TokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenProvider tokenProvider;
//
////	유효한 자격증명을 제공하지 않고 접근하려 할때 401
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//	
////	필요한 권한이 없이 접근하려 할때 403
	@Autowired
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//			교차 사이트 접속 보안 해제
			.csrf().disable()
			
//			인증 인가 오류 발생 시 아래의 접근 권한으로 오류 표시
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint) // 401 에러 (자격 없음)
			.accessDeniedHandler(jwtAccessDeniedHandler) // 403 에러 (권한 없음)

			// enable h2-console
			.and()
			.headers()
			.frameOptions()
			.sameOrigin()

			// 세션을 사용하지 않기 때문에 STATELESS로 설정
//			ALWAYS(항상사용), IF_REQUIRED(필요시 생성,기본값), NEVER(생성하지 않음, 기존에 존재 시 사용), STATELESS (생성하지 않고, 기존 것이 있어도 사용 안함)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//			사이트 접속 권한 설정
//			authorizeRequests() : 스프링 시큐리티 처리에 HttpServletRequest 사용
//			antMatchers() : 특정한 경로를 지정
//			permitAll() : 모든 사용자 접근 가능
//			anyRequest() : 모든 접속 경로
//			authenticated() : 자격 증명 사용
			.and()
			.authorizeRequests()
//			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
			.antMatchers("/**").permitAll()
			.antMatchers("/api/hello").permitAll()
			.antMatchers("/api/login").permitAll()
			.antMatchers("/api/login2").permitAll()
			.antMatchers("/api/signup").permitAll()

			.anyRequest().authenticated()

//			Jwt설정 사용 
			.and()
			.apply(new JwtSecurityConfig(tokenProvider));
	}
}
