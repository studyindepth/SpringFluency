package di;

import javax.inject.Inject;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public class DITests {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(Config.class);
        context.refresh();
        TestService service = context.getBean(TestService.class);
        System.out.println(service.repo);
        context.registerShutdownHook();
    }
}

@Service
@Profile("prod")
class TestService {
    Repo repo;

    public TestService(Repo repo) {
        this.repo = repo;
    }

}

@Repository("r")
@Profile("test")
class TestRepo implements Repo {

}

@Profile("prod")
@Repository
class RepoImpl implements Repo {

}

interface Repo {

}

@Configuration
@ComponentScan("di")
class Config {

}
