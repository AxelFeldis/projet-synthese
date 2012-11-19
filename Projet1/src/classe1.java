import javax.swing.SwingUtilities;

public class classe1 {
	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){			//Création d'un thread??????????  éxécution du code en parallèle de celui-ci
			public void run(){
				Fenetre fenetre = new Fenetre();			//qui crée une fenêtre
				fenetre.setVisible(true);					//et la rend visible.
			}
		});
	
	}

}
