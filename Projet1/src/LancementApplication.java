import java.awt.EventQueue;


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
