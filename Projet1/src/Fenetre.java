import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.net.URL;


public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	private JLabel zone;
	
	Fenetre(String titre, int largeur, int hauteur) {
		super(titre);
		GridLayout place = new GridLayout(1,1,3,3);
		getContentPane().setLayout(place);
		zone = new JLabel ("");
		zone.setPreferredSize(new Dimension(largeur, hauteur));
		getContentPane().add(zone);
		pack();
	}
	
	public void affichageImage(URL url) {
		ImageIcon img = new ImageIcon(url);
		zone.setIcon(img);
	}

}
