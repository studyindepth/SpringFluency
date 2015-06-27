package aop.aj;

import org.springframework.stereotype.Service;

@Service
class MyService {
	@Trace
	public void service(int i) {
		System.out.println("service running");
	}
}
