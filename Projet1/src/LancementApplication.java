import java.awt.EventQueue;

public class LancementApplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//FenetrePropre window = new FenetrePropre();
					FenetreClean window = new FenetreClean();
					//window.pack();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Ca marche!");
				System.out.println("Fenetre ouverte");
			}
		});
	}

}
