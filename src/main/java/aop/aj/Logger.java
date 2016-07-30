package aop.aj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
	@Before(value = "@annotation(log)")
	public void annotation(JoinPoint point, Log log) throws Throwable {
		System.out.println("Logging " + point.getSignature());
	}

	@After(value = "@annotation(log)")
	public void annotation1(JoinPoint point, Log log) throws Throwable {
		System.out.println("after Logging " + point.getSignature());
	}
}
