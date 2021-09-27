package btc.mapper;

import org.apache.ibatis.annotations.Mapper;

import btc.dto.UserDto;

@Mapper
public interface LoginMapper {

	void signUp(UserDto user) throws Exception;
	
	int isUser(UserDto user) throws Exception;
	
	UserDto login(UserDto user) throws Exception;
}
