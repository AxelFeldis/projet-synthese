import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame {
	
	private JPanel Panel;			//C'est ce qui contiendra tous les éléments de notre fenêtre
	private JLabel Label1;			//Label fixe : "Veuillez saisir un monument :"
	private JTextField motCherche;	//Elément qui permet à l'utilisateur de saisir sa recherche
	private JButton ok;				//Bouton de validation
	private JLabel labelImage;		//Label qui contiendra les images

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	///**
	 //* @param args
	 //*/
	
	Fenetre() {						//Constructeur qui fait simplement appel
		super();					//au constructeur de la classe supérieure
		build();					//et a la méthode build definit ci-dessous
	}
	
	private void build() {
		setTitle("VisioScope");							//Nom 
		setSize(1000, 800); 							//Taille
		setResizable(false);							//Redimentionnable ou non, ici non
		setLocationRelativeTo(null);					//Centrer la fenêtre sur l'écran
		setContentPane(buildContentPanel());			//On lui associe un Panel directement créé par la méthode buildContentPanel definit ci-dessous
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);	//La fenêtre se ferme lors du clic sur la croix
		//pack();
	}
	
	private JPanel buildContentPanel() {
		Panel = new JPanel();									//Création du Panel
		Panel.setLayout(new FlowLayout());						//On choisit le layout, ici FlowLayout qui place les différents éléments les uns à la suite des autres et les centre.
		Panel.setBackground(Color.white);						//Fond blanc
		
		Label1 = new JLabel();									//Création et ajout du jlabel1
		Label1.setText("Veuillez saisir un monument :");
		Panel.add(Label1);
		
		motCherche = new JTextField();							//Création et ajout de motCherche
		motCherche.setColumns(10);
		Panel.add(motCherche);
		
		ok = new JButton(new RecuperationPhoto(this, "OK!"));	//Création et ajout du bouton ok
		Panel.add(ok);
		
		labelImage = new JLabel();								//Création et ajout du label1
		Panel.add(labelImage);
		
		
		return Panel;
		
	}
	
	//OBSERVATEURS
	
	public JLabel getLabel1() {	
		return Label1;
	}
	
	public JTextField getTextField() {
		return motCherche;
	}
	
	public JLabel getLabelPhoto() {
		return labelImage;
	}
	
	public JPanel getPanel() {
		return Panel;
	}
	
	public JButton getButton() {
		return ok;
	}

}
