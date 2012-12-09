import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;

public class Fenetre extends JFrame {
	
	private JPanel Panel;			//C'est ce qui contiendra tous les �l�ments de notre fen�tre
	private JLabel Label1;			//Label fixe : "Veuillez saisir un monument :"
	private JTextField motCherche;	//El�ment qui permet � l'utilisateur de saisir sa recherche
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
		super();					//au constructeur de la classe sup�rieure
		build();					//et a la m�thode build definit ci-dessous
	}
	
	private void build() {
		setTitle("VisioScope");							//Nom 
		setSize(1000, 800); 							//Taille
		setResizable(false);							//Redimentionnable ou non, ici non
		setLocationRelativeTo(null);					//Centrer la fen�tre sur l'�cran
		setContentPane(buildContentPanel());			//On lui associe un Panel directement cr�� par la m�thode buildContentPanel definit ci-dessous
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);	//La fen�tre se ferme lors du clic sur la croix
		//pack();
	}
	
	private JPanel buildContentPanel() {
		Panel = new JPanel();									//Cr�ation du Panel
		Panel.setLayout(new FlowLayout());						//On choisit le layout, ici FlowLayout qui place les diff�rents �l�ments les uns � la suite des autres et les centre.
		Panel.setBackground(Color.white);						//Fond blanc
		
		Label1 = new JLabel();									//Cr�ation et ajout du jlabel1
		Label1.setText("Veuillez saisir un monument :");
		Panel.add(Label1);
		
		motCherche = new JTextField();							//Cr�ation et ajout de motCherche
		motCherche.setColumns(10);
		Panel.add(motCherche);
		
		ok = new JButton(new RecuperationPhoto(this, "OK!"));	//Cr�ation et ajout du bouton ok
		Panel.add(ok);
		
		labelImage = new JLabel();								//Cr�ation et ajout du label1
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
