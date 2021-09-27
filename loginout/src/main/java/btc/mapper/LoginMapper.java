package btc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import btc.dto.UserDto;

@Mapper
public interface LoginMapper {

	int selectUserInfoYn(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;
	
	UserDto selectUserInfo(String userId) throws Exception;
}