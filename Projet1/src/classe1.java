import javax.swing.SwingUtilities;

public class classe1 {
	/**
	 * @param args
	 */	
	public static void main(String[] args) {
		//SwingUtilities.invokeLater(new Runnable(){			//Cr�ation d'un thread??????????  �x�cution du code en parall�le de celui-ci
			//public void run(){
				Fenetre fenetre = new Fenetre();			//qui cr�e une fen�tre
				fenetre.setVisible(true);					//et la rend visible.
			//}
		//});
	
	}

}
