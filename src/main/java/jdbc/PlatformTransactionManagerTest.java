package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

public class PlatformTransactionManagerTest {
	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Config.class);

		CardService service = context.getBean(CardService.class);
		service.create("card dd1");
	}

	static class CardService {
		private DataSource dataSource;

		public CardService(DataSource dataSource) {
			this.dataSource = dataSource;
		}

		@Transactional //(rollbackFor = Exception.class)
		public void create(String name) throws Exception {
			Connection con = DataSourceUtils.getConnection(dataSource);
			try {
				Statement st = con.createStatement();
				st.executeUpdate("insert into card values ('" + name + "')");
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				DataSourceUtils.releaseConnection(con, dataSource);
			}

			// throw new Exception();
		}
	}

	@Configuration
	@EnableTransactionManagement
	static class Config {
		@Bean
		public DataSource dataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost/spring");
			dataSource.setUsername("root");
			dataSource.setPassword("");
			dataSource.setMaxTotal(20);
			dataSource.setInitialSize(3);
			return dataSource;
		}

		@Bean
		public PlatformTransactionManager transactionManager() {
			return new DataSourceTransactionManager(dataSource());
		}

		@Bean
		public CardService service() {
			CardService cardService = new CardService(dataSource());
			return cardService;
		}
	}
}
