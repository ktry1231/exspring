package kr.ac.hit.myapp.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.hit.myapp.member.MemberVo;

//스프링의 인터셉터를 만들기 위해서는 HandlerInterceptor 인터페이스를 구현
//HandlerInterceptor 인터페이스를 직접 구현하면 필요없는 메서드까지 모두 구현해야 하기 때문에,
//보통은 미리 해당 인터페이스를 구현해놓은 HandlerInterceptorAdapter 를 상속하여
//필요한 메서드만 재정의(Override)하여 구현
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	//컨트롤러(@Controller)의 메서드(@RequestMapping)가 실행되기 전에 먼저 실행
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		System.out.println("LoginInterceptor:preHandle!!");
		
		HttpSession session = req.getSession(); //현재 요청의 세션객체 가져오기
		//로그인한 사용자인지 확인
		MemberVo vo = (MemberVo) session.getAttribute("loginUser");
		if (vo==null) { //로그인하지 않은 경우
			resp.sendRedirect( req.getContextPath() + "/member/login.do"); //로그인 주소로 이동
			return false; //이후의 인터셉터나 컨트롤러는 실행하지 않도록
		}
		
		return true; //이 후에 실행될 인터셉터 또는 컨트롤러를 실행시킬지 여부를 반환
	}

	//컨트롤러(@Controller)의 메서드(@RequestMapping)가 실행된 후에 실행
//	@Override
//	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
//			throws Exception {
//		System.out.println("LoginInterceptor:postHandle!!");
//	}

	//뷰 렌더링(JSP 출력)까지 모두 끝난 후에 실행
//	@Override
//	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
//			throws Exception {
//		System.out.println("LoginInterceptor:afterCompletion!!");
//	}
}
