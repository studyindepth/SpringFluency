package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TransactionImpl implements Transaction {

	private DataSource datasource;

	public TransactionImpl(DataSource datasource) {
		super();
		this.datasource = datasource;
	}

	@Override
	public void start() {
		Connection con = ConnectionManager.getConnection(datasource);
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}

	@Override
	public void commit() {
		Connection con = ConnectionManager.getConnection(datasource);
		try {
			con.commit();
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}

	@Override
	public void rollback() {
		Connection con = ConnectionManager.getConnection(datasource);
		try {
			con.rollback();
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}

	@Override
	public void execute(DBCommand command) {
		command.execute();
	}

}
