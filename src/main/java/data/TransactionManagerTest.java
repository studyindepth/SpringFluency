package data;

public class TransactionManagerTest {
	public static void main(String[] args) {
		TransactionManager txManager = null;
		Transaction tx = txManager.getTransaction();
		try {
			tx.execute(() -> {
				// doing thing here
			});
		} catch (Exception e) {
			txManager.rollback();
		}

	}
}
