package jdbc;

public class TransactionManagerTest {
	public static void main(String[] args) {
		TransactionManager txManager = null;
		Transaction tx = txManager.getTransaction();
		try {
			tx.execute(new DBCommand() {

				@Override
				public void execute() {

				}

			});
		} catch (Exception e) {
			txManager.rollback();
		}

	}
}
