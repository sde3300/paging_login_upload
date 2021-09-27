package btc.serviece;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import btc.dto.UserDto;
import btc.mapper.LoginMapper;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	public int selectUserInfoYn(String userId, String userPw) throws Exception {
		
		return loginMapper.selectUserInfoYn(userId, userPw);
	}
	
	@Override
	public UserDto selectUserInfo(String userId) throws Exception {
		return loginMapper.selectUserInfo(userId);
	}

}
