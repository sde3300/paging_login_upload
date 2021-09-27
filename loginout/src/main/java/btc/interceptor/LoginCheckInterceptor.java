package btc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginCheckInterceptor implements HandlerInterceptor {

//	기존에는 HandlerInterceptorAdapter 클래스를 사용했음
//	현재는 HandlerInterceptor 인터페이스를 사용해야함(사용 방법은 동일)
//	3가지의 메서드가 존재함
//	preHandle() : 지정한 컨트롤러가 동작하기 직전에 먼저 수행
//	postHandle() : 지정한 컨트롤러가 동작 후 View를 표기하기 직전에 수행
//	afterConpletion : 모든 동작이 완료된 후 수행
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("==============  start  ==============");
		System.out.println("Request URI \t : " + request.getRequestURI());
		
		HttpSession session = request.getSession();
		
		if ((String)session.getAttribute("userId") == null) {
			System.out.println("---------- 비 로그인 상태 ----------");

			response.sendRedirect("/login/loginFail");
			return false;
		}
		else {
			System.out.println("------------ 로그인 상태 ------------");
			System.out.println((String)session.getAttribute("userId"));
			session.setMaxInactiveInterval(120);
			return true;
		}	
		
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		System.out.println("==============   end   ==============");
	}
}
