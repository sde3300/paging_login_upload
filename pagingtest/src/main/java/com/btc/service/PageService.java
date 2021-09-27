package com.btc.service;

import com.btc.dto.PageDto;
import com.github.pagehelper.Page;

public interface PageService {
//	Page : pageHelper에서 지원하는 데이터 타입임 (List타입이라고 보면 됨)
//	반환 타입이 Page 클래스 타입이임. getEmpName는 원하는대로 이름 정해주면됨
//	pageNo : 컨트롤러에서 전송한 페이지번호
	public Page<PageDto> getEmpName(int pageNo) throws Exception;
}
