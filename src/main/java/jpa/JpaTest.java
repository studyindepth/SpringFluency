package jpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JpaTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		CardRepository repo = context.getBean(CardRepository.class);
		repo.create(new Card("card 1"));
		System.out.println(repo.findOne(1L));
		context.registerShutdownHook();
	}
}
