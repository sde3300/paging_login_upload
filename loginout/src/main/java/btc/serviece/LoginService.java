package btc.serviece;

import btc.dto.UserDto;

public interface LoginService {
	int selectUserInfoYn(String userId, String userPw) throws Exception;
	
	UserDto selectUserInfo(String userId) throws Exception;
	
}
