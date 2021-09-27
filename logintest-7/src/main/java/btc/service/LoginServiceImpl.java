package btc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import btc.dto.UserDto;
import btc.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void signUp(UserDto user) throws Exception {
//		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		
		loginMapper.signUp(user);
	}
	
	@Override
	public int isUser(UserDto user) throws Exception {
//		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		
		return loginMapper.isUser(user);
	}
	
	@Override
	public UserDto login(UserDto user) throws Exception {
//		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		
		return loginMapper.login(user);
	}
}
