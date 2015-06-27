package data;

import javax.sql.DataSource;

public class ProgrammaticTransactionManager implements TransactionManager {

	private DataSource dataSource;

	public ProgrammaticTransactionManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Transaction getTransaction() {
		return new TransactionImpl(dataSource);
	}

	@Override
	public void commit() {

	}

	@Override
	public void rollback() {

	}

}
