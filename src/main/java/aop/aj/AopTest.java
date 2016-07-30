package aop.aj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class AopTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		MyService service = context.getBean(MyService.class);
		service.service(1);
		System.out.println("\n---------------------------------------\n");

		// exception thrown
		service.service2(2);

		// continue running
		System.out.println("\n---------------------------------------\n");
		service.service(2);
		context.registerShutdownHook();
	}

	@Configuration
	@ComponentScan("aop")
	@EnableAspectJAutoProxy
	static class Config {
	}

}
