package jdbc;


public interface Transaction {
	void start();

	void commit();

	void rollback();

	void execute(DBCommand command);

}
