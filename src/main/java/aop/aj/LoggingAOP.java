package aop.aj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

public class LoggingAOP {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		MyService service = context.getBean(MyService.class);
		// LogAspect LogAspect = context.getBean(LogAspect.class);
		System.out.println(service);
		// System.out.println(LogAspect);
		service.service(1);
		// context.close();
		context.registerShutdownHook();
	}

	@org.aspectj.lang.annotation.Aspect
	@Component
	static class LogAspect {

		@Before("execution(public * aop..*(..))")
		public void before(JoinPoint joinPoint) throws Throwable {
			System.out.println(joinPoint.getTarget().getClass());
			System.out.println("before");
		}

		@Before("within(aop.aj.MyService)")
		public void within(JoinPoint point) {
			System.out.println("within");
		}

		@Before(value = "@annotation(trace)")
		public void annotation(JoinPoint point, Trace trace) throws Throwable {
			System.out.println("annotation");
			System.out.println(trace);
		}
	}

	@Configuration
	@ComponentScan("aop")
	@EnableAspectJAutoProxy
	static class Config {

	}

}
