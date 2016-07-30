package di;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config1.class)
public class IntegrationTests {

    @Inject
    ApplicationContext context;

    @Test
    public void test() {
        while (true) {

        }
    }

}

@Configuration
class Config1 {
    
}
