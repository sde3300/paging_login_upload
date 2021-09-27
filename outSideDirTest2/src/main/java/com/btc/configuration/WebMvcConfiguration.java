package com.btc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
	@Value("${resources.img.location}")
	private String imgLocation;
	
	@Value("${resources.location}")
	private String resourcesLocation;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadImagePath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		String path1 = "file:///" + imgLocation;
//		String path2 = "file:///" + resourcesLocation;
//		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/java102/img/"); //리눅스 root에서 시작하는 폴더 경로
//		registry.addResourceHandler("/images/**").addResourceLocations("file:/" + imgLocation);
//		registry.addResourceHandler("/images/**").addResourceLocations("file://" + imgLocation, "file://" + resourcesLocation);
//		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/java102/img/", "file:///C:/java102/upload/");
//		registry.addResourceHandler("/images/**").addResourceLocations(path1, path2);
		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/java602/outsideImages/");
//		registry.addResourceHandler("/image/**").addResourceLocations("file://" + imgLocation);
//		registry.addResourceHandler("/images/**").addResourceLocations("file://" + resourcesLocation);
//		registry.addResourceHandler("/upload/**").addResourceLocations("file:///"+uploadImagePath) // 웹에서 이미지 호출시 'file:///' 설정됨
//		.setCachePeriod(3600)
//		.resourceChain(true)
//		.addResolver(new PathResourceResolver());
		
//		registry.addResourceHandler("/images/**").addResourceLocations("file:///C:/java602/outsideImages/");
	}
	
	
//	전체 파일경로 작성하고 뒤에 /붙여줘야함
}
