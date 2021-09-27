package btc.service;

import btc.dto.UserDto;

public interface LoginService {

	void signUp(UserDto user) throws Exception;
	
	int isUser(UserDto user) throws Exception;
	
	UserDto login(UserDto user) throws Exception;
}
