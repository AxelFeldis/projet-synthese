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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import com.aetrion.flickr.photos.PhotosInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	private JLabel choixMonument = new JLabel("Choisissez un monument");
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
	private JButton btnCentral = new JButton("btnCentral");
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

	public FenetreClean() throws SQLException {
		site = new Site();
		setTitle("VisioScope");
		setVisible(true);
		setSize(1300, 800);
		setResizable(true);

		JPanel gauche = new JPanel();
		BorderLayout layoutGauche = new BorderLayout();
		gauche.setLayout(layoutGauche);

		JPanel droite = new JPanel();
		BorderLayout layoutDroite = new BorderLayout();
		droite.setLayout(layoutDroite);

		// Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gauche, droite);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		// Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(100, 50);
		gauche.setMinimumSize(minimumSize);
		droite.setMinimumSize(minimumSize);
		getContentPane().add(splitPane);

		JPanel partieValidation = new JPanel();
		GridBagLayout layoutPartieValidation = new GridBagLayout();
		partieValidation.setLayout(layoutPartieValidation);

		gauche.add(partieValidation, BorderLayout.NORTH);
		GridBagConstraints contraintes = new GridBagConstraints();

		splitPane.setBackground(Color.DARK_GRAY);
		splitPane.setForeground(Color.GRAY);

		lblTitre.setForeground(Color.GRAY);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 28));

		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 11;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixMonument, contraintes);
		partieValidation.add(choixMonument);

		contraintes.gridx = 2;
		contraintes.gridy = 3;
		contraintes.gridwidth = 8;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		comboBox = new JComboBox<String>();
		this.fillComboBox();
		layoutPartieValidation.setConstraints(comboBox, contraintes);
		partieValidation.add(comboBox);

		contraintes.gridx = 1;
		contraintes.gridy = 5;
		contraintes.gridwidth = 11;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbPhotos, contraintes);
		partieValidation.add(nbPhotos);

		contraintes.gridx = 1;
		contraintes.gridy = 7;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbBilk, contraintes);
		partieValidation.add(nbBilk);

		contraintes.gridx = 5;
		contraintes.gridy = 7;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbOut, contraintes);
		partieValidation.add(nbOut);

		contraintes.gridx = 9;
		contraintes.gridy = 7;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(nbIn, contraintes);
		partieValidation.add(nbIn);

		contraintes.gridx = 1;
		contraintes.gridy = 9;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbBilk, contraintes);
		partieValidation.add(choixNbBilk);

		contraintes.gridx = 5;
		contraintes.gridy = 9;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbOut, contraintes);
		partieValidation.add(choixNbOut);

		contraintes.gridx = 9;
		contraintes.gridy = 9;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		layoutPartieValidation.setConstraints(choixNbIn, contraintes);
		partieValidation.add(choixNbIn);

		contraintes.gridx = 4;
		contraintes.gridy = 11;
		contraintes.gridwidth = 4;
		contraintes.gridheight = 2;
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
//				btnOk.setVisible(false);
//				choixNbBilk.setVisible(false);
//				choixNbOut.setVisible(false);
//				choixNbIn.setVisible(false);

				visit1 = new Visit(site.getNameS());
				thread = new ThreadRecup((Integer) choixNbOut.getSelectedItem(), (Integer) choixNbBilk.getSelectedItem(),
						(Integer) choixNbIn.getSelectedItem(), FenetreClean.this);
				thread.start();
			}
		});
		layoutPartieValidation.setConstraints(btnOk, contraintes);
		partieValidation.add(btnOk);

		contraintes.gridx = 1;
		contraintes.gridy = 14;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 2;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		//btnBilk.setVisible(false);
		btnBilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (progressBar.getValue() != progressBar.getMaximum()) {
//					progressBar.setBounds(23, 240, 146, 14);
//				}
				visit1.setVisitState(0);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
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
		layoutPartieValidation.setConstraints(btnBilk, contraintes);
		partieValidation.add(btnBilk);

		contraintes.gridx = 5;
		contraintes.gridy = 14;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 2;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		//btnOut.setVisible(false);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (progressBar.getValue() != progressBar.getMaximum()) {
//					progressBar.setBounds(23, 240, 146, 14);
//				}
				visit1.setVisitState(1);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
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

		contraintes.gridx = 9;
		contraintes.gridy = 14;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 2;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		//btnIn.setVisible(false);
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(2);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
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
		
		contraintes.gridx = 3;
		contraintes.gridy = 17;
		contraintes.gridwidth = 7;
		contraintes.gridheight = 1;
		contraintes.fill = GridBagConstraints.HORIZONTAL;
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		progressBar.setBounds(749, 365, 146, 14);
		//progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		layoutPartieValidation.setConstraints(progressBar, contraintes);
		partieValidation.add(progressBar);

		// contraintes.gridx = 1;
		// contraintes.gridy = 19;
		// contraintes.gridwidth = 10; contraintes.gridheight = 9;
		txtrBienvenueSurVisioscope
				.setText("Bienvenue sur VisioScope, \nune application vous\npermettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!");
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		txtrBienvenueSurVisioscope.setForeground(Color.GRAY);
		// contraintes.fill = GridBagConstraints.HORIZONTAL ;
		// contraintes.weightx = 0;
		// contraintes.weighty = 0;
		// layoutGauche.setConstraints(txtrBienvenueSurVisioscope, contraintes);
		gauche.add(txtrBienvenueSurVisioscope);
		gauche.add(txtrBienvenueSurVisioscope, BorderLayout.CENTER);

		JPanel partieHaute = new JPanel();
		GridLayout layoutPartieHaute = new GridLayout(2, 1, 1, 1);
		partieHaute.setLayout(layoutPartieHaute);

		droite.add(partieHaute, BorderLayout.NORTH);

		JPanel panelTitre = new JPanel();
		FlowLayout layoutPanelTitre = new FlowLayout(FlowLayout.CENTER, 1, 1);
		panelTitre.setLayout(layoutPanelTitre);

		JPanel boutonHaut = new JPanel();
		GridLayout layoutBoutonHaut = new GridLayout(1, 3, 200, 1);
		boutonHaut.setLayout(layoutBoutonHaut);

		panelTitre.add(lblTitre);
		partieHaute.add(panelTitre);
		partieHaute.add(boutonHaut);
		
//		JLabel partieGauche = new JLabel();
//		FlowLayout layoutPartieGauche = new FlowLayout();
//		partieGauche.setLayout(layoutPartieGauche);
//		partieGauche.add(btnSuivant);
//		partieGauche.add(btnO);
//		droite.add(partieGauche, BorderLayout.WEST);

//		contraintes.gridx = 0;
//		contraintes.gridy = 0;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.fill = GridBagConstraints.BOTH;
//		contraintes.anchor = GridBagConstraints.WEST;
//		contraintes.weightx = 50;
//		contraintes.weighty = 50;
//		layoutBoutonHaut.setConstraints(btnNo, contraintes);
		//btnNo.setVisible(false);
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
		boutonHaut.add(btnNo);

		// contraintes.gridx = 14;
		// contraintes.gridy = 11;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 4;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnO, contraintes);
		//btnO.setVisible(false);
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
		droite.add(btnO, BorderLayout.WEST);
//		partieGauche.add(btnSuivant);
//		partieGauche.add(btnO);
		
	

//		contraintes.gridx = 1;
//		contraintes.gridy = 0;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.fill = GridBagConstraints.BOTH;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.weightx = 50;
//		contraintes.weighty = 50;
//		layoutBoutonHaut.setConstraints(btnN, contraintes);
		//btnN.setVisible(false);

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
		boutonHaut.add(btnN);

//		contraintes.gridx = 2;
//		contraintes.gridy = 0;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.fill = GridBagConstraints.BOTH;
//		contraintes.anchor = GridBagConstraints.EAST;
//		contraintes.weightx = 50;
//		contraintes.weighty = 50;
//		layoutBoutonHaut.setConstraints(btnNe, contraintes);
		//btnNe.setVisible(false);
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
		boutonHaut.add(btnNe);

		// contraintes.gridx = 34;
		// contraintes.gridy = 11;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 4;
		// contraintes.fill = GridBagConstraints.NONE;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnE, contraintes);
		//btnE.setVisible(false);
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
		droite.add(btnE, BorderLayout.EAST);

		// contraintes.gridx = 35;
		// contraintes.gridy = 12;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 2;
		// contraintes.fill = GridBagConstraints.NONE;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnPrecedent, contraintes);
		// droite.add(btnPrecedent);

		JPanel boutonBas = new JPanel();
		GridLayout layoutBoutonBas = new GridLayout(2, 1, 1, 1);
		boutonBas.setLayout(layoutBoutonBas);
		droite.add(boutonBas, BorderLayout.SOUTH);

		JPanel boutonBasIn = new JPanel();
		GridLayout layoutBoutonBasIn = new GridLayout(1, 3, 200, 1);
		boutonBasIn.setLayout(layoutBoutonBasIn);

		boutonBas.add(boutonBasIn);

		JPanel boutonBasBilk = new JPanel();
		FlowLayout layoutBoutonBasBilk = new FlowLayout(FlowLayout.CENTER, 1, 1);
		boutonBasBilk.setLayout(layoutBoutonBasBilk);

		boutonBas.add(boutonBasBilk);

		// contraintes.gridx = 14;
		// contraintes.gridy = 21;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 4;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnSo, contraintes);
		//btnSo.setVisible(false);
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
		boutonBasIn.add(btnSo);

		// contraintes.gridx = 24;
		// contraintes.gridy = 21;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 4;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnS, contraintes);
		//btnS.setVisible(false);
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
		boutonBasIn.add(btnS);

		// contraintes.gridx = 34;
		// contraintes.gridy = 21;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 4;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 50;
		// contraintes.weighty = 50;
		// layoutDroite.setConstraints(btnSe, contraintes);
		//btnSe.setVisible(false);
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
		boutonBasIn.add(btnSe);

		// contraintes.gridx = 19;
		// contraintes.gridy = 6;
		// contraintes.gridwidth = 14;
		// contraintes.gridheight = 14;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(btnCentral, contraintes);
		droite.add(btnCentral, BorderLayout.CENTER);

		// contraintes.gridx = 15;
		// contraintes.gridy = 35;
		// contraintes.gridwidth = 3;
		// contraintes.gridheight = 3;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(button1, contraintes);
		//button1.setVisible(false);
		btnO.addActionListener(new ActionListener() {
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
		boutonBasBilk.add(button1);

		// contraintes.gridx = 19;
		// contraintes.gridy = 35;
		// contraintes.gridwidth = 3;
		// contraintes.gridheight = 3;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(button2, contraintes);
		//button2.setVisible(false);
		btnO.addActionListener(new ActionListener() {
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
		boutonBasBilk.add(button2);

		// contraintes.gridx = 23;
		// contraintes.gridy = 35;
		// contraintes.gridwidth = 3;
		// contraintes.gridheight = 3;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(button3, contraintes);
		//button3.setVisible(false);
		boutonBasBilk.add(button3);

		// contraintes.gridx = 27;
		// contraintes.gridy = 35;
		// contraintes.gridwidth = 3;
		// contraintes.gridheight = 3;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(button4, contraintes);
		//button4.setVisible(false);
		btnO.addActionListener(new ActionListener() {
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
		boutonBasBilk.add(button4);

		// contraintes.gridx = 31;
		// contraintes.gridy = 35;
		// contraintes.gridwidth = 3;
		// contraintes.gridheight = 3;
		// contraintes.fill = GridBagConstraints.BOTH;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(button5, contraintes);
		//button5.setVisible(false);
		btnO.addActionListener(new ActionListener() {
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
		boutonBasBilk.add(button5);

		// contraintes.gridx = 35;
		// contraintes.gridy = 26;
		// contraintes.gridwidth = 4;
		// contraintes.gridheight = 2;
		// contraintes.fill = GridBagConstraints.NONE;
		// contraintes.weightx = 100;
		// contraintes.weighty = 100;
		// layoutDroite.setConstraints(btnQuitter, contraintes);
		//btnQuitter.setVisible(false);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoutonsInterfaceDeplacementInvisible();
				btnOk.setVisible(true);
				btnBilk.setVisible(false);
				btnIn.setVisible(false);
				btnOut.setVisible(false);
				choixNbBilk.setVisible(true);
				choixNbIn.setVisible(true);
				choixNbOut.setVisible(true);
			}
		});
		boutonBasBilk.add(btnQuitter);
	}
	
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
		}
		if (visit1.getVisitState() == 1) {
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

		try {
			if (visit1.getVisitState() == 0) {
				url1 = new URL(visit1.tabBilk[visit1.i].getPic(p1ou2).getLargeUrl());
			} else if (visit1.getVisitState() == 1) {

				url1 = new URL(visit1.tabOutDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLargeUrl());
			} else if (visit1.getVisitState() == 2) {

				url1 = new URL(visit1.tabInDoor[visit1.ligne][visit1.colonne].getPic(p1ou2).getLargeUrl());
			}
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
				url1 = new URL(visit1.tabBilk[visit1.i - 2].getPic(1).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button1.setIcon(img9);
		}

		if (button2.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i - 1].getPic(1).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button2.setIcon(img9);
		}

		if (button3.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i].getPic(p1ou2).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button3.setIcon(img9);
		}

		if (button4.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i + 1].getPic(1).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button4.setIcon(img9);
		}

		if (button5.isVisible()) {
			try {
				url1 = new URL(visit1.tabBilk[visit1.i + 2].getPic(1).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img9 = new ImageIcon(url1);
			button5.setIcon(img9);
		}
	}
}
