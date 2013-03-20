import java.awt.EventQueue;

public class LancementApplication {
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
