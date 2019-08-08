package kr.ac.hit.ex;

import org.springframework.stereotype.Component;

@Component("b")
public class Bye implements Greet {

	@Override
	public void say() {
		System.out.println("Bye~");
	}

}
