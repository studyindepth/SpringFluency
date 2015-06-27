package data;

public interface TransactionManager {
	Transaction getTransaction();

	void commit();

	void rollback();
}
