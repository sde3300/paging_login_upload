package btc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import btc.interceptor.LoginCheckInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//	생성한 인터셉터를 실행하기 위해서 스프링프레임워크의 InterceptorRegistry에 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		addPathPatterns("패턴") : 인터셉터를 사용할 컨트롤러를 선택(전체 주소)
//		excludePathPatterns("주소") : 인터셉터를 적용하지 않을 컨트롤러를 선택
		registry.addInterceptor(new LoginCheckInterceptor())
		.addPathPatterns("/login/*")
		.excludePathPatterns("/login/loginFail")
		.excludePathPatterns("/login/login")
		.excludePathPatterns("/login/loginCheck");
	}
}
