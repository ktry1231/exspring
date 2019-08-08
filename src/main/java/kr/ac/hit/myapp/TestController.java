package kr.ac.hit.myapp;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hit.myapp.bbs.BbsService;
import kr.ac.hit.myapp.bbs.BbsVo;
import kr.ac.hit.myapp.member.MemberVo;

@Controller
public class TestController {
	
	//"/test/a.do" 주소로 요청이 오면 이 메서드를 실행 
	@RequestMapping("/test/a.do") 
	public String aaa() {
		return "test";
	}
	
//	@RequestMapping("/test/b.do") 
	public String bbb( int x, @RequestParam("y") int b, Map m ) {
		//전송되어오는 파라미터 이름과 동일한 이름의 변수를 인자로 선언하여
		//파라미터 값을 받을 수 있다. (변수타입에 맞게 자동으로 형변환)
		//변수명이 파라미터명과 다른 경우, @RequestParam()로 파라미터명 지정 
		System.out.println(x);
		System.out.println(b);
		int sum = x + b;
		
		//컨트롤러 메서드의 인자로 Map, Model, ModelMap 타입의 변수를 선언하고,
		//	해당 Map, Model, ModelMap 변수에 데이터를 저장하여 JSP에 전달 가능
		
		//JSP 파일로 저장할 데이터를 담는 모델에 "total"라는 이름으로 sum을 저장
		//JSP 파일에서 ${total} 표현으로 sum 값 사용 가능
		m.put("total", sum); 
		
		return "result";
	}
	
	//파라미터 값을 받기 위해 사용한 객체를 JSP에서 사용하고 싶으면,
	//@ModelAttribute()를 붙이고 JSP에서 사용할 이름을 지정
	//@ModelAttribute() 생략시 타입이름(첫글자만소문자로변환)으로 모델에 자동 저장
	@RequestMapping("/test/b.do") 
	public String ccc( //@ModelAttribute("pointVo") 
						PointVo vo, Map m ) {
		//사용자가 정의한 클래스 타입의 인자를 선언하면,
		//스프링이 메서드를 실행할 때 해당 클래스의 객체를 생성하고,
		//객체의 속성(변수)명과 동일한 이름의 파라미터 값을 저장하여 전달해준다.
		
		int sum = vo.getX() + vo.getY();
		
		//JSP 파일로 저장할 데이터를 담는 모델에 "total"라는 이름으로 sum을 저장
		//JSP 파일에서 ${total} 표현으로 sum 값 사용 가능
		m.put("total", sum); 
		
		return "result";
	}
	
	@Resource
	private BbsService bbsService;
	
	@RequestMapping("/test/auto.do")
	public String auto(HttpSession session) {
		MemberVo loginUser = (MemberVo) session.getAttribute("loginUser"); //로그인한 사용자
		BbsVo vo = new BbsVo();
		vo.setBbsWriter( loginUser.getMemId() ); //로그인한 사용자 아이디로 글쓴이를 강제설정
		
		for (int i = 0; i < 50; i++) {
			vo.setBbsTitle("제목" + System.nanoTime() );
			vo.setBbsContent("내용" + UUID.randomUUID());
			bbsService.insert(vo);
		}
		
		return "redirect:/";
	}
}





