package btc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import btc.dto.UserDto;
import btc.serviece.LoginService;

@Controller
public class LoginController {
   
//	세션(session) : 사전적 의미로는 시간을 의미함
//	클라이언트와 서버간의 통신 연결에서 두 개체의 활성화 된 접속 정보를 뜻함
//	클라이언트가 서버에 요청하여 처음 서버에 접속하면 서버는 요청한 클라이언트에 대하여 고유한 임시 ID를 부여하게 되는데 이 ID를 세션이라 함
//	세션 ID는 서버에 임시로 저장되며 각 페이지 이동 시 이용하거나, 클라이언트가 서버에 재접속 할 경우 해당 클라이언트를 구분하는 유일한 수단임
//	세션 및 쿠키는 인터넷상의 가상 저장공간 (간단한 데이터를 저장하여 활용할 수 있음)
//	세션은 서버에 정보를 저장
//	세션은 서버에 일정 시간동안 저장되어 보관되어 있다가 시간 경과 시 세션은 자동 삭제됨
	
//	웹페이지는 각각의 페이지가 하나의 프로그램으로 동작
//	각각의 페이지는 데이터를 서로 공유하지 않음
//	기존의 페이지에서 작업한 중요 정보를 다른 페이지에서도 공유하기 위해서 세션을 사용함
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="/login/sessionTest", method=RequestMethod.GET)
	public String sessrionTest() throws Exception {
		return "login/sessionTest";
	}
   
	@RequestMapping(value="/login/sessionInput", method=RequestMethod.GET)
  	public String sessionInput(HttpServletRequest request) throws Exception {
	   
//	   HttpServletRequest : 서버로 요청된 데이터에 대한 모든 내용을 가지고 있는 클래스
//	   getSession() : HttpServletRequest가 가지고 있는 클라이언트의 세션 정보를 가져옴
	   
//	   HttpSession : 웹 페이지의 세션에 데이터를 저장하거나 가져올 수 있는 클래스	   
//	   setAttribute(세션명, 저장할 데이터) : 세션에 지정한 이름으로 데이터를 저장
//	   getAttribute(세션명) : 세션에 지정한 이름으로 저장된 데이터를 가져옴
//	   setMaxInactiveInterval(시간) : 지정한 시간동안 세션 정보가 살아있음(초 단위), 해당 시간 이후에 세션이 서버에서 삭제됨
//	   removeAttribute(세션명) : 서버에서 지정한 이름으로 저장된 세션을 삭제함
//	   invalidate() : 서버에 저장된 모든 세션 정보를 삭제
	   
		HttpSession session = request.getSession();
		session.setAttribute("userId", "sessionTestId");
		session.setMaxInactiveInterval(30);
	   
		return "redirect:/login/sessionTest";
	}
   
   	@RequestMapping(value="/login/login", method=RequestMethod.GET)
   	public String login() throws Exception {
	   
   		return "/login/login";
   	}
   
//   	login.html에서 전송된 userId와 userPw를 가지고 서버에 지정된 id/pw와 비교
   	@RequestMapping(value="/login/loginCheck", method=RequestMethod.POST)
   	public String loginCheck(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request) throws Exception {
   		
   		int count = loginService.selectUserInfoYn(userId, userPw);
   		
//   	DB에 사용자 정보가 존재 시 필요한 사용자 정보 로드 및 세션에 추가
   		if (count == 1) {
   			
   			UserDto user = loginService.selectUserInfo(userId);
   			
//   		클라이언트에서 전송된 HttpServletRequest 클래스 타입의 변수가 가지고 있는 클라이언트 세션 정보를 받아옴
//   		받아온 정보를 HttpSession 클래스 타입의 변수에 대입
   			HttpSession session = request.getSession();
//   		setAttribute() 메서드를 사용하여 세션에 저장
   			session.setAttribute("userId", user.getUserId());
   			session.setAttribute("userName", user.getUserName());
   			session.setAttribute("userEmail", user.getUserEmail());
   			session.setAttribute("userLevel", user.getUserLevel());
//   		세션이 서버 내에 살아있을 수 있는 시간 설정
   			session.setMaxInactiveInterval(30);
   			
//   		리다이렉트로 loginOK 페이지로 이동
   			return "redirect:/login/loginOK";
   		}
   		
   		else {
//   		리다이렉트로 loginFail 페이지로 이동
   			return "redirect:/login/loginFail";
   		}
//   		Map<String, String> userInfo = new HashMap<String, String>();
   	}
   	
   	@RequestMapping(value="/login/loginOK", method=RequestMethod.GET)
   	public String loginOK(HttpServletRequest request) throws Exception {
   		
//   		예전에는 각 페이지마다 세션을 직접 확인하여 세션 정보가 있을 경우에만 페이지를 출력했다
   		
//   		HttpSession session = request.getSession();
//   		
//   		if (session.getAttribute("userId") != null) {
//  			return"/login/loginOK";
//  		}
//   		
//   		else {
//   			return "redirect:/login/loginFail";
//   		}
   		
//   		스프링프레임워크에서는 인터셉터를 사용하여 세션을 자동체크함
//   		인터셉터 : 클라이언트에서 서버로 접속하여 서비스를 요청 시 서버의 컨트롤러와 맵핑되기 바로 직전에 해당 데이터를 가져와서 사용하는 방식
   		return "/login/loginOK";
   	}
   	
   	@RequestMapping(value="login/loginFail", method=RequestMethod.GET)
   	public String loginFail() throws Exception {
   		return "/login/loginFail";
   	}
   	
   	@RequestMapping(value="login/logout", method=RequestMethod.GET)
   	public String logout(HttpServletRequest request) throws Exception {
   		
//     	클라이언트의 정보를 가지고 있는 HttpServletRequest 클래스의 객체 request 가져옴
//   	request를 통해서 서버에 접속한 클라이언트의 세션 정보를 받아옴
//   	기존에 생성된 세션 ID와 현재 logout 페이지에 접속한 클라이언트의 세선 ID가 동일함
   		HttpSession session = request.getSession();
//   	removeAttribute() 메서드를 사용하여 지정한 세선 정보를 삭제함	
   		session.removeAttribute("userId");
//   	해당 세션 ID가 가지고 있는 모든 세션 정보를 삭제함
//   	maxInactiveInteral의 시간이 남아있어도 세션정보가 삭제되었기 때문에 세션정보를 확인할 수 있음
   		session.invalidate();
   		
   		return "/login/logout";
   	}
}
