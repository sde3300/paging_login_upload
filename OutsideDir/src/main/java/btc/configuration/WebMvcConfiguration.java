package btc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		addResourceHandler : 스프링부트에서 확인할 폴더의 위치 설정
//		addResourceLocations : 실제 시스템의 폴더 위치, 윈도우 시스템의 경우 'file:///경로명' 형태로 사용함
		registry.addResourceHandler("/outsideImg/**").addResourceLocations("file:///C:/java602/outsideImages/");
		registry.addResourceHandler("/movie/**").addResourceLocations("file:///C:/java602/movie/");
		
	}
}
