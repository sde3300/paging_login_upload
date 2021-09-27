package com.btc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btc.dto.PageDto;
import com.btc.mapper.PageMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class PageServiceImpl implements PageService {
	@Autowired
	private PageMapper mapper;

	@Override
	public Page<PageDto> getEmpName(int pageNo) throws Exception {
//		PageHelper : 빌드 그레이들에서 설정했던 페이지헬퍼 클래스를 가져오는것
//		startPage : pageHelper에서 제공하는 메서드이다. 
//		(pageNo, 10) 첫번째 매개변수-시작할 페이지, 두번째 매개변수-한 화면에 표시할 페이지 수, 1번부터 열개 출력한다.
		
//		현재 내가 보는 페이지 설정
		PageHelper.startPage(pageNo, 5);
//		전체 데이터를 가져옴
        return mapper.findUser();
	}

}
