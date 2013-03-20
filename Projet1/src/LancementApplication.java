import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class LancementApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetrePropre window = new FenetrePropre();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("yo Propre ta mere");
			}
		});
	}

}
