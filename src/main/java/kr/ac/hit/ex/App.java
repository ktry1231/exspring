package kr.ac.hit.ex;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

//이 클래스의 인스턴스를 생성하여 "a"라는 이름으로 스프링컨텍스트(컨테이너)에 등록 
@Component("a")
public class App {
//	private Greet greet = new Hello();
	
	//@Autowired, @Inject, @Resource 중 하나를 사용하여 스프링에 등록된 객체들 중
	//이 변수의 타입에 맞는 객체를 자동으로 주입(저장)
	@Resource(name="b") //"b"라는 이름으로 스프링에 등록된 객체를 주입(저장)
	private Greet greet;
	
	public void act() {
		greet.say();
	}

	public Greet getGreet() {
		return greet;
	}

	public void setGreet(Greet greet) {
		this.greet = greet;
	}
	
}
