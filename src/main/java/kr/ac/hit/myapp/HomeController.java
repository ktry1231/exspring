package kr.ac.hit.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//스프링 웹 앱에서 특정 주소로 요청을 보내면 실행될 코드를 담고 있는 클래스
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// "/" 주소로 GET 방식의 요청이 오면 이 메서드를 실행 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// Locale : 현재 애플리케이션의 지역(언어,국가) 정보를 받을 수 있다
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//현재 시간을 담고 있는 Date 객체를 생성
		Date date = new Date();
		//날짜시간 데이터를 현재 지역에 맞는 형식의 문자열로 변환 
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		//스프링에서 컨트롤러의 데이터를 JSP로 전달하기 위해서는
		//컨트롤러 메서드의 파라미터로 Map, Model, ModelMap 타입의 객체를 받은 후,
		//그 객체에 데이터를 원하는 이름으로 저장하면,
		//JSP에서 그 이름으로 저장했던 데이터를 꺼내서 사용 가능
		//model.addAttribute("serverTime", formattedDate );
		//모델 객체에 "serverTime"라는 이름으로 formattedDate 를 저장
		//JSP에서는 ${serverTime} 라는 표현으로 꺼내서 사용 가능
		
		//스프링 컨트롤러의 메서드에서 문자열(String)을 반환하면, 스프링은 이것을 뷰(JSP)이름으로 해석
		//return "home";
		//스프링 설정 파일(servlet-context.xml)에 등록된 InternalResourceViewResolver에
		//설정한 접두어(prefix)와 접미어(suffix)를 반환된 뷰이름의 앞 뒤로 붙여서
		//만든 JSP 파일로 이동(forward)
		
		return "redirect:/member/list.do";
	}
	
}






