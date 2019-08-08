package kr.ac.hit.ex;

import org.springframework.stereotype.Component;

//이 클래스의 인스턴스를 생성하여, "h"라는 이름으로 스프링에 등록
@Component("h")
public class Hello implements Greet {
	
	@Override
	public void say() {
		System.out.println("Hello~");
	}

}
