package aop.aj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandler {

	@Around("execution(public * aop..*(..))")
	public void handle(ProceedingJoinPoint joinPoint) {
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			System.out.println(e + "was thrown from " + joinPoint.getSignature());
		}
	}

}
