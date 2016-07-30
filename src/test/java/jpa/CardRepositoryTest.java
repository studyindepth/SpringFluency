package jpa;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config1.class)
@ActiveProfiles("test")
public class CardRepositoryTest {

	@Autowired
	CardRepository repo;

	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		try {
			repo.create(new Card("111111111111111111111111111111111111"));
		} catch (Exception e) {
			System.out.println(e.getClass());
			e.printStackTrace();
		}

	}
}

@Configuration
@ComponentScan("jpa")
@PropertySource(value = "classpath:config.properties")
@Profile("test")
class Config1 {
	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() {
		// HikariDataSource
		System.out.println(env.getProperty("jdbc.url"));
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setMaxTotal(20);
		dataSource.setInitialSize(3);
		return dataSource;
	}

	private Map<String, ?> jpaProperties() {
		Map<String, String> jpaPropertiesMap = new HashMap<String, String>();
		jpaPropertiesMap.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		jpaPropertiesMap.put("hibernate.hbm2ddl.auto", "create");
		return jpaPropertiesMap;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactorytest(DataSource dataSource) {
		System.out.println(env.getProperty("hibernate.dialect"));
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		bean.setJpaPropertyMap(jpaProperties());
		bean.setPackagesToScan("jpa");
		bean.setDataSource(dataSource);
		System.out.println(bean instanceof EntityManagerFactory);
		return bean;
	}

	@Bean
	public PlatformTransactionManager jpaPlatformTransactionManager(EntityManagerFactory emf) {
		System.out.println(emf);
		System.out.println(emf.getClass());
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

	@Bean
	public static PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		PersistenceExceptionTranslationPostProcessor bean = new PersistenceExceptionTranslationPostProcessor();
		return bean;
	}
}