package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class Test {
	public static void main(String[] args) throws Exception {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/spring");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setMaxTotal(20);
		dataSource.setInitialSize(3);
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);
		Statement st = con.createStatement();
		st.executeUpdate("insert into Card(id, name) values (3, 'card 01')");
		// System.out.println(1 / 0);
		st.executeUpdate("insert into Card(id, name) values (4, 'card 02')");
		con.commit();

		// need close all resources
		// need roll back if exception occurs
	}
}
