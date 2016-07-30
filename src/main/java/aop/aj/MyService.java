package aop.aj;

import org.springframework.stereotype.Service;

@Service
class MyService {
	@Log
	public void service(int i) {
		System.out.println("service running");
	}

	@Log
	public void service2(int i) {
		System.out.println("service2 running");
		throw new RuntimeException("Runtime Exception");
	}
}
