import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.aetrion.flickr.photos.PhotosInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FenetreClean extends JFrame {
	private static final long serialVersionUID = 1L;
	// private JTextField txtTapezVotreRecherche = new JTextField();
	private JLabel lblTitre = new JLabel("VisioScope");
	private JLabel lblPhotoCentrale = new JLabel("");
	private JTextArea txtrBienvenueSurVisioscope = new JTextArea();
	private JLabel choixMonument = new JLabel("Veuillez choisir un monument");
	private JLabel nbPhotos = new JLabel("Choisissez le nombre de photos");
	private JLabel nbBilk = new JLabel("Bilk :");
	private JLabel nbIn = new JLabel("In :");
	private JLabel nbOut = new JLabel("Out :");
	private JButton btnPrecedent = new JButton("Precedent");
	private JButton btnOk = new JButton("Ok");
	private JButton btnO = new JButton("O");
	private JButton btnS = new JButton("S");
	private JButton btnE = new JButton("E");
	private JButton btnN = new JButton("N");
	private JButton btnSo = new JButton("SO");
	private JButton btnSe = new JButton("SE");
	private JButton btnNo = new JButton("NO");
	private JButton btnNe = new JButton("NE");
	private JButton btnSuivant = new JButton("Suivant");
	private JButton button1 = new JButton("1");
	private JButton button2 = new JButton("2");
	private JButton button3 = new JButton("3");
	private JButton button4 = new JButton("4");
	private JButton button5 = new JButton("5");
	private JButton btnQuitter = new JButton("Quitter");
	private JButton btnCentral = new JButton();
	private ThreadRecup thread;
	private Integer[] tabNbOrdonne = { 4, 9, 16, 25, 36, 49, 64, 81 };
	private Integer[] tabNbBilk = { 5, 10, 20, 30, 40, 50, 60, 70, 80, 2000 };
	private JComboBox<Integer> choixNbBilk = new JComboBox(tabNbBilk);
	private JComboBox<String> comboBox = new JComboBox();
	private static PhotosInterface photosInterface;
	private int p1ou2 = 1;

	public JButton btnIn = new JButton("In");
	public JButton btnOut = new JButton("Out");
	public JButton btnBilk = new JButton("Bilk");
	public JComboBox<Integer> choixNbOut = new JComboBox(tabNbOrdonne);
	public JComboBox<Integer> choixNbIn = new JComboBox(tabNbOrdonne);
	public JProgressBar progressBar = new JProgressBar();

	public Visit visit1;
	public Site site;

	public FenetreClean() throws SQLException, BadLocationException {
		site = new Site();
		setTitle("VisioScope");
		setVisible(true);
		setSize(1100, 850);
		setResizable(true);
		addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                   try {
					site.getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                   try {
					if (site.getConnection().isClosed()){
						   System.out.println("connection fermé");
					   }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
             }
    });

		// Création du panel représentant la partie gauche de notre application
		// avec un BorderLayout
		JPanel gauche = new JPanel();
		BorderLayout layoutGauche = new BorderLayout();
		gauche.setLayout(layoutGauche);

		// Création du panel représentant la partie droite de notre application
		// avec un BorderLayout
		JPanel droite = new JPanel();
		BorderLayout layoutDroite = new BorderLayout();
		droite.setLayout(layoutDroite);

		// Découpage de l'application avec un séparateur
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gauche, droite);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(240);
		Dimension minimumSize = new Dimension(100, 50);
		gauche.setMinimumSize(minimumSize);
		droite.setMinimumSize(minimumSize);
		getContentPane().add(splitPane);

		// Création du panel représentant la partie validation placé en haut à
		// gauche(dans la partie NORTH du panel gauche)
		// il est découpé avec un GridBagLayout
		JPanel partieValidation = new JPanel();
		GridBagLayout layoutPartieValidation = new GridBagLayout();
		partieValidation.setLayout(layoutPartieValidation);
		partieValidation.setBackground(Color.DARK_GRAY);
		partieValidation.setForeground(Color.GRAY);
		gauche.add(partieValidation, BorderLayout.NORTH);

		GridBagConstraints contraintes = new GridBagConstraints();

		// ---------------------------------------------------------PARTIE-GAUCHE-------------------------------------------------------------\\

		// label "Veuillez choisir un monument"
		choixMonument.setForeground(Color.GRAY);
		contraintes.insets = new Insets(5, 5, 5, 5);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixMonument, contraintes);
		partieValidation.add(choixMonument);

		// Liste déroulante, choix du monument
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		comboBox = new JComboBox<String>();
		this.fillComboBox();
		layoutPartieValidation.setConstraints(comboBox, contraintes);
		partieValidation.add(comboBox);

		// Label "Choisissez le nombre de photos"
		nbPhotos.setForeground(Color.GRAY);
		contraintes.gridx = 0;
		contraintes.gridy = 2;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbPhotos, contraintes);
		partieValidation.add(nbPhotos);

		// label "Bilk :"
		nbBilk.setForeground(Color.GRAY);
		contraintes.gridx = 0;
		contraintes.gridy = 3;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbBilk, contraintes);
		partieValidation.add(nbBilk);

		// label "Out :"
		nbOut.setForeground(Color.GRAY);
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbOut, contraintes);
		partieValidation.add(nbOut);

		// label "In :"
		nbIn.setForeground(Color.GRAY);
		contraintes.gridx = 2;
		contraintes.gridy = 3;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbIn, contraintes);
		partieValidation.add(nbIn);

		// Liste déroulante, choix nombre de photos en diapo
		contraintes.gridx = 0;
		contraintes.gridy = 4;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbBilk, contraintes);
		partieValidation.add(choixNbBilk);

		// Liste déroulante, choix nombre de photos en extérieur
		contraintes.gridx = 1;
		contraintes.gridy = 4;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbOut, contraintes);
		partieValidation.add(choixNbOut);

		// Liste déroulante, choix nombre de photos en intérieur
		contraintes.gridx = 2;
		contraintes.gridy = 4;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbIn, contraintes);
		partieValidation.add(choixNbIn);

		// BOUTON DE VALIDATION
		contraintes.gridx = 1;
		contraintes.gridy = 5;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		btnOk.setVisible(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { //
					String st = comboBox.getSelectedItem().toString();
					site.retrieveGeoSite(st);
				} catch (SQLException e1) { // //// On appelle la méthode
											// retrieveGeoSite() qui fait
					// TODO Auto-generated catch block ////// la recherche dans
					// la BDD du nom de site choisi
					e1.printStackTrace(); //
				} //
				progressBar.setVisible(true);
				btnOk.setEnabled(false);
				choixNbBilk.setEnabled(false);
				choixNbOut.setEnabled(false);
				choixNbIn.setEnabled(false);

				visit1 = new Visit(site.getNameS());
				thread = new ThreadRecup((Integer) choixNbOut.getSelectedItem(), (Integer) choixNbBilk.getSelectedItem(), (Integer) choixNbIn.getSelectedItem(), FenetreClean.this);
				thread.start();
			}
		});
		layoutPartieValidation.setConstraints(btnOk, contraintes);
		partieValidation.add(btnOk);

		// BOUTON VISITE EN DIAPO
		btnBilk.setEnabled(false);
		contraintes.gridx = 0;
		contraintes.gridy = 6;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		// btnBilk.setVisible(false);
		btnBilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if (progressBar.getValue() != progressBar.getMaximum()) {
				// progressBar.setBounds(23, 240, 146, 14);
				// }
				visit1.setVisitState(0);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
				btnQuitter.setVisible(true);
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(true);
				visit1.move("init", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		layoutPartieValidation.setConstraints(btnBilk, contraintes);
		partieValidation.add(btnBilk);

		// BOUTON VISITE EN EXTERIEUR
		btnOut.setEnabled(false);
		contraintes.gridx = 1;
		contraintes.gridy = 6;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		// btnOut.setVisible(false);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if (progressBar.getValue() != progressBar.getMaximum()) {
				// progressBar.setBounds(23, 240, 146, 14);
				// }
				visit1.setVisitState(1);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
//				btnN.setVisible(true);
//				btnNe.setVisible(true);
//				btnNo.setVisible(true);
//				btnSo.setVisible(true);
//				btnO.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		layoutPartieValidation.setConstraints(btnOut, contraintes);
		partieValidation.add(btnOut);

		// BOUTON VISITE EN INTERIEUR
		btnIn.setEnabled(false);
		contraintes.gridx = 2;
		contraintes.gridy = 6;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		// btnIn.setVisible(false);
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(2);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
//				btnN.setVisible(true);
//				btnNe.setVisible(true);
//				btnNo.setVisible(true);
//				btnSo.setVisible(true);
//				btnO.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		layoutPartieValidation.setConstraints(btnIn, contraintes);
		partieValidation.add(btnIn);

		// BARRE DE CHARGEMENT
		progressBar.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 7;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		progressBar.setBounds(749, 365, 146, 14);
		// progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		layoutPartieValidation.setConstraints(progressBar, contraintes);
		partieValidation.add(progressBar);

		// Texte descriptif
		// Ajout du texte descriptif dans la partie CENTER de gauche
		
		GridBagLayout layoutPanelTexte = new GridBagLayout();
//		SimpleAttributeSet style = new SimpleAttributeSet();
//	    StyleConstants.setAlignment(style,StyleConstants.ALIGN_CENTER);
	    JPanel panelTexte = new JPanel();
//	    txtrBienvenueSurVisioscope.getDocument().insertString(txtrBienvenueSurVisioscope.getDocument().getLength(), "Bienvenue sur VisioScope, \nune application vous permettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!", style);
		panelTexte.setLayout(layoutPanelTexte);
		panelTexte.setBackground(Color.DARK_GRAY);
		gauche.add(panelTexte, BorderLayout.CENTER);
		txtrBienvenueSurVisioscope.setEditable(false);

		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.BOTH;
		//contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		txtrBienvenueSurVisioscope
				.setText("         Bienvenue sur VisioScope,\n\n     une application vous permettant\n      d'effectuerdes visites virtuelles\n de sites touristiques, archéologiques\n           ou bien de monuments.\n\n           Commencez par choisir\n        votre monument ci-dessus,\n              choisissez ensuite \n le nombre de photos de vos visites\n\n             et valider avec \"OK!\" \n Une fois que vos visites sont prêtes \n     les boutons se déverrouillent, \n            il ne vous reste plus \n          qu'a choisir votre visite! \n\n                 Voyagez bien!");
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		txtrBienvenueSurVisioscope.setForeground(Color.GRAY);
		layoutPanelTexte.setConstraints(txtrBienvenueSurVisioscope, contraintes);
		panelTexte.add(txtrBienvenueSurVisioscope);

		// ---------------------------------------------------------PARTIE-DROITE-------------------------------------------------------------\\

		// ------------------PARTIE HAUTE-------------//

		// Nous placons les 3 boutons No, N, Ne dans la deuxieme ligne dans un
		// gridLayout de 3 colonnes
		JPanel boutonHaut = new JPanel();
		GridBagLayout layoutBoutonHaut = new GridBagLayout();
		boutonHaut.setLayout(layoutBoutonHaut);
		boutonHaut.setBackground(Color.DARK_GRAY);
		boutonHaut.setForeground(Color.GRAY);
		droite.add(boutonHaut, BorderLayout.NORTH);
		
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		//contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		lblTitre.setForeground(Color.GRAY);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 28));
		layoutBoutonHaut.setConstraints(lblTitre, contraintes);
		boutonHaut.add(lblTitre);

		// BOUTON NO
		btnNo.setVisible(false);
		//btnNo.setEnabled(false);
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		//contraintes.anchor = GridBagConstraints.WEST;
		//contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 5, 5, 100);
		btnNo.setPreferredSize(new Dimension(100,100));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("NO", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layoutBoutonHaut.setConstraints(btnNo, contraintes);
		boutonHaut.add(btnNo);
		
		// BOUTON N
		//btnN.setEnabled(false);
		btnN.setVisible(false);
		contraintes.gridx = 1;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;;
		contraintes.insets = new Insets(5, 90, 5, 90);
		btnN.setPreferredSize(new Dimension(100,100));
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("N", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		layoutBoutonHaut.setConstraints(btnN, contraintes);
		boutonHaut.add(btnN);

		// BOUTON NE
		//btnNe.setEnabled(false);
		btnNe.setVisible(false);
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.EAST;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;;
		btnNe.setPreferredSize(new Dimension(100,100));
		contraintes.insets = new Insets(5, 90, 5, 5);
		btnNe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("NE", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonHaut.setConstraints(btnNe, contraintes);
		boutonHaut.add(btnNe);

		// ------------------PARTIE GAUCHE-------------//
		JPanel partieGauche = new JPanel();
		GridBagLayout layoutPartieGauche = new GridBagLayout();
		partieGauche.setLayout(layoutPartieGauche);
		partieGauche.setBackground(Color.DARK_GRAY);
		partieGauche.setForeground(Color.GRAY);
		droite.add(partieGauche, BorderLayout.WEST);
		
		// BOUTON O
		//btnO.setEnabled(false);
		btnO.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(0, 5, 0, 5);
		btnO.setPreferredSize(new Dimension(100,100));
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("O", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutPartieGauche.setConstraints(btnO, contraintes);
		partieGauche.add(btnO);
		
		// BOUTON PRECEDENT
		//btnPrecedent.setEnabled(false);
		btnPrecedent.setVisible(false);
				contraintes.gridx = 0;
				contraintes.gridy = 1;
				contraintes.gridwidth = 1;
				contraintes.gridheight = 1;
				contraintes.anchor = GridBagConstraints.CENTER;
				//contraintes.fill = GridBagConstraints.BOTH;
				contraintes.weightx = 0;
				contraintes.weighty = 0;
				contraintes.insets = new Insets(0, 5, 0, 5);
				//btnE.setPreferredSize(new Dimension(100,100));
				btnPrecedent.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						p1ou2 = 1;
						visit1.move("prec", FenetreClean.this);
						try {
							GestionBoutons();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println(p1ou2);

					}
				});
				layoutPartieGauche.setConstraints(btnPrecedent, contraintes);
				partieGauche.add(btnPrecedent);

		// ------------------PARTIE DROITE-------------//
		JPanel partieDroite = new JPanel();
		GridBagLayout layoutPartieDroite = new GridBagLayout();
		partieDroite.setLayout(layoutPartieDroite);
		partieDroite.setBackground(Color.DARK_GRAY);
		partieDroite.setForeground(Color.GRAY);
		droite.add(partieDroite, BorderLayout.EAST);
		
		// BOUTON E
		//btnE.setEnabled(false);
		btnE.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(0, 5, 0, 5);
		btnE.setPreferredSize(new Dimension(100,100));
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("E", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutPartieDroite.setConstraints(btnE, contraintes);
		partieDroite.add(btnE);
		
		// BOUTON SUIVANT
		//btnSuivant.setEnabled(false);
		btnSuivant.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(0, 5, 0, 5);
		//btnE.setPreferredSize(new Dimension(100,100));
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("suiv", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(p1ou2);

			}
		});
		layoutPartieDroite.setConstraints(btnSuivant, contraintes);
		partieDroite.add(btnSuivant);
		

		// ------------------PARTIE CENTRALE-------------//
		JPanel panelCentral = new JPanel();
		GridBagLayout layoutPanelCentral = new GridBagLayout();
		panelCentral.setLayout(layoutPanelCentral);
		panelCentral.setBackground(Color.DARK_GRAY);
		//btnCentral.setMargin(new Insets(100, 100, 100, 100));
		droite.add(panelCentral, BorderLayout.CENTER);
		//btnCentral.setEnabled(false);
		btnCentral.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 100;
		contraintes.weighty = 100;
		contraintes.insets = new Insets(5, 50,5, 50);
		btnCentral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = (p1ou2 == 1) ? 2 : 1;
				affichageImage();

			}
		});
		
		
		layoutPanelCentral.setConstraints(btnCentral, contraintes);
		panelCentral.add(btnCentral);

		// ------------------PARTIE BASSE-------------//
		// Creation du panel représentant la partie en bas de notre application,
		// il est divisé avec un GridLayout de 2lignes
		JPanel boutonBas = new JPanel();
		GridLayout layoutBoutonBas = new GridLayout(2, 1, 1, 1);
		boutonBas.setLayout(layoutBoutonBas);
		boutonBas.setBackground(Color.DARK_GRAY);
		boutonBas.setForeground(Color.GRAY);
		droite.add(boutonBas, BorderLayout.SOUTH);

		// Dans la première ligne nous ajoutons un panel qui contiendra les 3
		// boutons So, S,Se divisé avec un GridLayout de 3 colonnes
		JPanel boutonBasIn = new JPanel();
		GridBagLayout layoutBoutonBasIn = new GridBagLayout();
		boutonBasIn.setLayout(layoutBoutonBasIn);
		boutonBasIn.setBackground(Color.DARK_GRAY);
		boutonBasIn.setForeground(Color.GRAY);
		boutonBas.add(boutonBasIn);

		// BOUTON SO
		//btnSo.setEnabled(false);
		btnSo.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.WEST;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 5, 5, 90);
		btnSo.setPreferredSize(new Dimension(100,100));
		btnSo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("SO", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasIn.setConstraints(btnSo, contraintes);
		boutonBasIn.add(btnSo);

		// BOUTON S
		//btnS.setEnabled(false);
		btnS.setVisible(false);
		contraintes.gridx = 1;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 90, 5, 90);
		btnS.setPreferredSize(new Dimension(100,100));
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("S", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasIn.setConstraints(btnS, contraintes);
		boutonBasIn.add(btnS);

		// BOUTON SE
		//btnSe.setEnabled(false);
		btnSe.setVisible(false);
		contraintes.gridx = 2;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.EAST;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 90, 5, 5);
		btnSe.setPreferredSize(new Dimension(100,100));
		btnSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("SE", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasIn.setConstraints(btnSe, contraintes);
		boutonBasIn.add(btnSe);

		// Ajout dans la deuxieme ligne d'un panel avec un FlowLayout contenant
		// les boutons(galerie de photos de la visite en diapo) et le bouton
		// quitter
		JPanel boutonBasBilk = new JPanel();
		GridBagLayout layoutBoutonBasBilk = new GridBagLayout();
		boutonBasBilk.setLayout(layoutBoutonBasBilk);
		boutonBasBilk.setBackground(Color.DARK_GRAY);
		boutonBasBilk.setForeground(Color.GRAY);
		boutonBas.add(boutonBasBilk);

		// BOUTON 1
		//button1.setEnabled(false);
		button1.setVisible(false);
		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 100, 5, 15);
		button1.setPreferredSize(new Dimension(70,70));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2prec", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasBilk.setConstraints(button1, contraintes);
		boutonBasBilk.add(button1);

		// BOUTON 2
		//button2.setEnabled(false);
		button2.setVisible(false);
		contraintes.gridx = 1;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 15, 5, 15);
		button2.setPreferredSize(new Dimension(70,70));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("prec", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasBilk.setConstraints(button2, contraintes);
		boutonBasBilk.add(button2);

		// BUTTON 3
		//button3.setEnabled(false);
		button3.setVisible(false);
		contraintes.gridx = 2;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 15, 5, 15);
		button3.setPreferredSize(new Dimension(70,70));
		layoutBoutonBasBilk.setConstraints(button3, contraintes);
		boutonBasBilk.add(button3);

		// BUTTON 4
		//button4.setEnabled(false);
		button4.setVisible(false);
		contraintes.gridx = 3;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 15, 5, 15);
		button4.setPreferredSize(new Dimension(70,70));
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasBilk.setConstraints(button4, contraintes);
		boutonBasBilk.add(button4);

		// BUTTON 5
		//button5.setEnabled(false);
		button5.setVisible(false);
		contraintes.gridx = 4;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 15, 5, 5);
		button5.setPreferredSize(new Dimension(70,70));
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2suiv", FenetreClean.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		layoutBoutonBasBilk.setConstraints(button5, contraintes);
		boutonBasBilk.add(button5);

		// BUTTON QUITTER
		//btnQuitter.setEnabled(false);
		btnQuitter.setVisible(false);
		contraintes.gridx = 5;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.anchor = GridBagConstraints.CENTER;
		//contraintes.fill = GridBagConstraints.BOTH;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		contraintes.insets = new Insets(5, 150, 5, 5);
		//buttonQuitter.setPreferredSize(new Dimension(70,70));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoutonsInterfaceDeplacementInvisible();
				btnOk.setEnabled(true);
				btnBilk.setEnabled(false);
				btnIn.setEnabled(false);
				btnOut.setEnabled(false);
				choixNbBilk.setEnabled(true);
				choixNbIn.setEnabled(true);
				choixNbOut.setEnabled(true);
			}
		});
		layoutBoutonBasBilk.setConstraints(btnQuitter, contraintes);
		boutonBasBilk.add(btnQuitter);
	}

	// ---------------------------------------------METHODE----------------------------------------//

	public void BoutonsInterfaceDeplacementInvisible() {
		btnSuivant.setVisible(false);
		btnPrecedent.setVisible(false);
		btnN.setVisible(false);
		btnNe.setVisible(false);
		btnNo.setVisible(false);
		btnS.setVisible(false);
		btnSe.setVisible(false);
		btnSo.setVisible(false);
		btnE.setVisible(false);
		btnO.setVisible(false);
		button1.setVisible(false);
		button2.setVisible(false);
		button3.setVisible(false);
		button4.setVisible(false);
		button5.setVisible(false);
		btnCentral.setVisible(false);
		btnQuitter.setVisible(false);
	}

	public void GestionBoutons() throws SQLException {

		if (visit1.getVisitState() == 1) {
			System.out.println("Longitude(x) = " + visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLongitudeP());
			System.out.println("Latitude(y) = " + visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLatitudeP());
			if ((visit1.colonne == 0) && (visit1.ligne == visit1.tabOutDoor.length - 1)) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(false);
				btnS.setVisible(false);
			} else if ((visit1.colonne == visit1.tabOutDoor.length - 1) && (visit1.ligne == 0)) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(false);
				btnNo.setVisible(false);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);

			} else if ((visit1.colonne == 0) && (visit1.ligne == 0)) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(false);
				btnNe.setVisible(false);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			} else if ((visit1.colonne == (visit1.tabOutDoor.length - 1)) && (visit1.ligne == (visit1.tabOutDoor.length - 1))) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(true);
				btnNo.setVisible(true);
				btnO.setVisible(true);
				btnSo.setVisible(false);
				btnS.setVisible(false);

			} else if (visit1.colonne == 0) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			} else if (visit1.colonne == (visit1.tabOutDoor.length - 1)) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(true);
				btnNo.setVisible(true);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);
			} else if (visit1.ligne == 0) {
				btnNe.setVisible(false);
				btnN.setVisible(false);
				btnNo.setVisible(false);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);
				btnSe.setVisible(true);
				btnE.setVisible(true);
			} else if (visit1.ligne == (visit1.tabOutDoor.length - 1)) {
				btnSe.setVisible(false);
				btnS.setVisible(false);
				btnSo.setVisible(false);
				btnO.setVisible(true);
				btnNo.setVisible(true);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
			} else {
				btnSo.setVisible(true);
				btnO.setVisible(true);
				btnNo.setVisible(true);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			}
		}
		if (visit1.getVisitState() == 2) {
			if ((visit1.colonne == 0) && (visit1.ligne == visit1.tabInDoor.length - 1)) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(false);
				btnS.setVisible(false);
			} else if ((visit1.colonne == visit1.tabInDoor.length - 1) && (visit1.ligne == 0)) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(false);
				btnNo.setVisible(false);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);

			} else if ((visit1.colonne == 0) && (visit1.ligne == 0)) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(false);
				btnNe.setVisible(false);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			} else if ((visit1.colonne == (visit1.tabInDoor.length - 1)) && (visit1.ligne == (visit1.tabInDoor.length - 1))) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(true);
				btnNo.setVisible(true);
				btnO.setVisible(true);
				btnSo.setVisible(false);
				btnS.setVisible(false);

			} else if (visit1.colonne == 0) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			}

			else if (visit1.colonne == (visit1.tabInDoor.length - 1)) {
				btnSe.setVisible(false);
				btnE.setVisible(false);
				btnNe.setVisible(false);
				btnN.setVisible(true);
				btnNo.setVisible(true);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);
			} else if (visit1.ligne == 0) {
				btnNe.setVisible(false);
				btnN.setVisible(false);
				btnNo.setVisible(false);
				btnO.setVisible(true);
				btnSo.setVisible(true);
				btnS.setVisible(true);
				btnSe.setVisible(true);
				btnE.setVisible(true);
			} else if (visit1.ligne == (visit1.tabInDoor.length - 1)) {
				btnSe.setVisible(false);
				btnS.setVisible(false);
				btnSo.setVisible(false);
				btnO.setVisible(true);
				btnNo.setVisible(true);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
			} else {
				btnSo.setVisible(true);
				btnO.setVisible(true);
				btnNo.setVisible(true);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnS.setVisible(true);
			}
		}

		if (visit1.getVisitState() == 0) {
			if (visit1.i == 0) {
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(true);
				btnPrecedent.setVisible(false);
				btnSuivant.setVisible(true);
			} else if (visit1.i == 1) {
				button1.setVisible(false);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(true);
				btnPrecedent.setVisible(true);
				btnSuivant.setVisible(true);
			} else if (visit1.i == visit1.tabBilk.length - 1) {
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(false);
				//button4.setBackground(Color.DARK_GRAY); 
				button5.setVisible(false);
				btnSuivant.setVisible(false);
				btnPrecedent.setVisible(true);
			} else if (visit1.i == visit1.tabBilk.length - 2) {
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(false);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
			} else {
				button1.setVisible(true);
				button2.setVisible(true);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(true);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
			}
		}

		affichageImage();
	}

	public void fillComboBox() throws SQLException {
		// Remplissage de la comboBox

		// Création d'un objet Statement
		Statement state = site.getConnection().createStatement();
		// L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery("SELECT nom FROM \"Site\";");
		// On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		// On affiche le nom des colonnes

		while (result.next()) {
			for (int i = 1; i <= resultMeta.getColumnCount(); i++) {
				String s = result.getObject(i).toString();
				comboBox.addItem(s);
			}

		}
		result.close();
		state.close();
	}

	public void affichageImage() {
		URL url1 = null;
//		int largeur=0;
//		int hauteur=0;
		try {
			if (visit1.getVisitState() == 0) {
				url1 = new URL(visit1.tabBilk[visit1.i].getLargeUrl());
//				hauteur=visit1.tabBilk[visit1.i].getPic(p1ou2).getHeight();
//				largeur=visit1.tabBilk[visit1.i].getPic(p1ou2).getWidht();
//				if (hauteur>600) {
//					hauteur=600;
//				}
//				if (largeur>900) {
//					largeur=900;
//				}
			} else if (visit1.getVisitState() == 1) {

				url1 = new URL(visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLargeUrl());
//				hauteur=visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getHeight();
//				largeur=visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getWidht();
//				if (hauteur>600) {
//					hauteur=600;
//				}
//				if (largeur>900) {
//					largeur=900;
//				}
			} else if (visit1.getVisitState() == 2) {

				url1 = new URL(visit1.tabInDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLargeUrl());
//				hauteur=visit1.tabInDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getHeight();
//				largeur=visit1.tabInDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getWidht();
//				if (hauteur>600) {
//					hauteur=600;
//				}
//				if (largeur>900) {
//					largeur=900;
//				}
			}
			
//			btnCentral.setPreferredSize(new Dimension(largeur,hauteur));
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}
		ImageIcon img = new ImageIcon(url1);
		btnCentral.setIcon(img);
		if (btnSe.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne + 1][visit1.colonne + 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne + 1][visit1.colonne + 1].getPic(1).getSmallUrl());

				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img1 = new ImageIcon(url1);
			btnSe.setIcon(img1);
		}

		if (btnS.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne + 1][visit1.colonne].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne + 1][visit1.colonne].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img2 = new ImageIcon(url1);
			btnS.setIcon(img2);
		}

		if (btnSo.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne + 1][visit1.colonne - 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne + 1][visit1.colonne - 1].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img3 = new ImageIcon(url1);
			btnSo.setIcon(img3);
		}

		if (btnO.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne][visit1.colonne - 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne][visit1.colonne - 1].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img4 = new ImageIcon(url1);
			btnO.setIcon(img4);
		}

		if (btnNo.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne - 1][visit1.colonne - 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne - 1][visit1.colonne - 1].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img5 = new ImageIcon(url1);
			btnNo.setIcon(img5);
		}

		if (btnN.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne - 1][visit1.colonne].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne - 1][visit1.colonne].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img6 = new ImageIcon(url1);
			btnN.setIcon(img6);
		}

		if (btnNe.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne - 1][visit1.colonne + 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne - 1][visit1.colonne + 1].getPic(1).getSmallUrl());
				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img7 = new ImageIcon(url1);
			btnNe.setIcon(img7);
		}

		if (btnE.isVisible()) {
			try {
				if (visit1.getVisitState() == 1) {
					url1 = new URL(visit1.tabOutDoor[visit1.ligne][visit1.colonne + 1].getPic(1).getSmallUrl());
				} else {
					url1 = new URL(visit1.tabInDoor[visit1.ligne][visit1.colonne + 1].getPic(1).getSmallUrl());

				}
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img8 = new ImageIcon(url1);
			btnE.setIcon(img8);
		}

		if (button1.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i - 2].getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button1.setIcon(img9);
		}

		if (button2.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i - 1].getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button2.setIcon(img9);
		}

		if (button3.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i].getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button3.setIcon(img9);
		}

		if (button4.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i + 1].getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button4.setIcon(img9);
		}

		if (button5.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i + 2].getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button5.setIcon(img9);
		}
	}
}
