package com.btc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.btc.dto.PageDto;
import com.btc.service.PageService;
import com.github.pagehelper.PageInfo;

@Controller
public class PageController {
	
	@Autowired
	private PageService pageService;

    @RequestMapping("/page")
    public ModelAndView page(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception {
    	ModelAndView mv = new ModelAndView("page");
    	
        PageInfo<PageDto> p = new PageInfo<PageDto>(pageService.getEmpName(pageNum), 10);
        
        mv.addObject("users", p);
        
//        model.addAttribute("users", p);
//        model.addAttribute("search", search);
        return mv;
        
//    뷰에서 사용할때는 @ResponseBody(액시오스사용)를 추가하고 모델앤뷰 대신 object 타입을 사용해야한다.
    }
}

//	defualtValue : 사용자의 입력이 없을 경우 지정한 데이터를 사용한다는 의미.
//	pageNo : 페이지번호

//	PageInfo : pageHelper에서 지원하는 데이터 타입 (List 타입과 동일).
//  PageInfo 생성자의 첫번째 매개변수 : 실제 데이터
//  PageInfo 생성자의 두번째 매개변수 : 화면에 표시할 페이지 번호 수
//	PageDto 타입의 리스트를 생성해서 데이터를 가져온다.
//	PageInfo 타입이 mapper를 통해 가져온 데이터와 PageHelper.startPage()를 통해서 설정했던 현재 페이지 정보를 통합함
//  (pageService.getEmpName(pageNum), 10) 10은 네비게이션의 번호를 몇개 출력할 것인지를 나타냄 

//	숫자 10은 한 페이지에 몇개 출력할 것인지를 나타낸다.

//	mv.addObject("users", p); 가져온 데이터를 model에 입력함.


//	PageInfo.java의 멤버
//	ex) get.page 형태로 쓰면 됨

//	pageNum : 현재 페이지 번호
//	pageSize : 페이지당 데이터수
//	size : 현재페이지수
//	startRow : 현재 페이지의 첫번째 요소의 줄번호
//	endRow : 현재 페이지의 마지막 요소의 줄번호
//	pages : 전체 페이지 수
//	prePage : 이전 페이지 번호
//	nextPage : 다음 페이지 번호
//	isFirstPage : 첫번째 페이지가 있는지 확인
//	isLastPage : 마지막 페이지가 있는지 확인
//	hasPreviousPage : 이전 페이지가 있는지 확인
//	hasNextPage : 다음 페이지가 있는지 확인
//	navigatePages : 네비게이션 페이지 번호
//	navigatepateNums : 모든 네비게이션 페이지 번호
//	navigateFirstPage : 네비게이션 바의 첫 페이지
//	navigateLastPage : 네비게이션 바의 마지막 페이지
//	list : 실제 데이터 리스트, Page 클래스 타입