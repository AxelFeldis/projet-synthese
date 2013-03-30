import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.aetrion.flickr.photos.PhotosInterface;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FenetrePropre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtTapezVotreRecherche = new JTextField();
	private JLabel lblTitre = new JLabel("VisioScope");
	private JLabel lblPhotoCentrale = new JLabel("");
	JTextArea txtrBienvenueSurVisioscope = new JTextArea();

	private JButton btnPrecedent = new JButton("Precedent");
	private JButton btnOk  = new JButton("Ok");
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
	public JButton btnIn = new JButton("In");
	public JButton btnOut = new JButton("Out");;
	public JButton btnBilk = new JButton("Bilk");
	private JButton btnCentral = new JButton("btnCentral");
	//public URL url1; // url de la photo
	private ThreadRecup thread;
	public Integer[] tabNbOrdonne = { 4, 9, 16, 25, 36, 49, 64, 81 };
	public Integer[] tabNbBilk = { 10, 20, 30, 40, 50, 60, 70, 80, 2000 };
	public JComboBox<Integer> choixNbBilk = new JComboBox(tabNbBilk);
	public JComboBox<Integer> choixNbOrdonne = new JComboBox(tabNbOrdonne);
	public JComboBox<String> comboBox = new JComboBox();
	public JProgressBar progressBar = new JProgressBar();
	int compteur = 0;
	static PhotosInterface photosInterface;
	public Visit visit1;
	private int p1ou2 = 1;
	Site site;

	public FenetrePropre() throws SQLException {
		site = new Site();
		setTitle("VisioScope");
		setVisible(true);
		setSize(1300, 800);
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.GRAY);

		lblTitre.setBounds(530, 24, 152, 59);
		lblTitre.setForeground(Color.GRAY);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 28));
		getContentPane().add(lblTitre);

		choixNbBilk.setBounds(23, 240, 68, 20);
		getContentPane().add(choixNbBilk);

		choixNbOrdonne.setBounds(103, 240, 68, 20);
		getContentPane().add(choixNbOrdonne);

		txtTapezVotreRecherche.setBounds(23, 147, 131, 20);
		txtTapezVotreRecherche.setColumns(10);
		getContentPane().add(txtTapezVotreRecherche);

		txtrBienvenueSurVisioscope
				.setText("Bienvenue sur VisioScope, \nune application vous\npermettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!");
		txtrBienvenueSurVisioscope.setBounds(23, 348, 249, 285);
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		txtrBienvenueSurVisioscope.setForeground(Color.GRAY);
		getContentPane().add(txtrBienvenueSurVisioscope);



		btnPrecedent.setBounds(309, 365, 89, 31);
		btnPrecedent.setVisible(false);
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("prec", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(p1ou2);

			}
		});
		getContentPane().add(btnPrecedent);

		btnSo.setBounds(333, 566, 135, 122);
		btnSo.setVisible(false);
		btnSo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("SO", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



			}
		});
		getContentPane().add(btnSo);

		btnNo.setBounds(333, 88, 135, 122);
		btnNo.setVisible(false);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("NO", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnNo);

		btnNe.setBounds(1132, 88, 121, 122);
		btnNe.setVisible(false);
		btnNe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("NE", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnNe);

		btnS.setBounds(760, 614, 121, 122);
		btnS.setVisible(false);
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("S", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnS);

		btnSe.setBounds(1132, 566, 121, 122);

		btnSe.setVisible(false);
		btnSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("SE", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnSe);

		btnE.setBounds(1132, 318, 121, 125);
		btnE.setVisible(false);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("E", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnE);

		btnSuivant.setBounds(1185, 365, 89, 31);
		btnSuivant.setVisible(false);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				p1ou2 = 1;
				visit1.move("suiv", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(p1ou2);

			}
		});
		getContentPane().add(btnSuivant);

		btnN.setBounds(760, 35, 135, 122);

		btnN.setVisible(false);
		
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("N", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnN);

		btnO.setBounds(333, 318, 135, 125);
		btnO.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("O", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(btnO);

		button3.setBounds(770, 670, 89, 81);
		button3.setVisible(false);
		getContentPane().add(button3);

		button2.setBounds(661, 670, 89, 81);
		button2.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = 1;
				visit1.move("prec", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(button2);

		button4.setBounds(908, 670, 89, 81);
		button4.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(button4);

		button5.setBounds(1026, 670, 89, 81);
		button5.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2suiv", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(button5);

		button1.setBounds(530, 670, 89, 81);
		button1.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2prec", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		getContentPane().add(button1);

		btnQuitter.setBounds(1164, 699, 89, 23);
		btnQuitter.setVisible(false);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BoutonsInterfaceDeplacementInvisible();
				btnOk.setVisible(true);
				btnBilk.setVisible(false);
				btnIn.setVisible(false);
				btnOut.setVisible(false);
				choixNbBilk.setVisible(true);
				choixNbOrdonne.setVisible(true);
			}
		});
		getContentPane().add(btnQuitter);

		progressBar.setBounds(749, 365, 146, 14);
		progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		getContentPane().add(progressBar);

		btnBilk.setBounds(23, 178, 52, 23);
		btnBilk.setVisible(false);
		btnBilk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (progressBar.getValue() != progressBar.getMaximum()) {
					progressBar.setBounds(23, 240, 146, 14);
				}
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				visit1.setVisitState(0);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		getContentPane().add(btnBilk);

		btnIn.setBounds(92, 178, 52, 23);
		btnIn.setVisible(false);
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(1);
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		getContentPane().add(btnIn);

		btnOut.setBounds(164, 178, 52, 23);
		btnOut.setVisible(false);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(1)	;			
				BoutonsInterfaceDeplacementInvisible();
				btnCentral.setVisible(true);
				btnS.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);

				visit1.move("init", FenetrePropre.this);
				try {
					GestionBoutons();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(btnOut);

		btnOk.setBounds(164, 146, 52, 23);
		btnOk.setVisible(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { //
					String st = comboBox.getSelectedItem().toString();
					site.retrieveGeoSite(st);
					System.out.println(site.getLongitudeS());
					System.out.println(site.getLatitudeS());
				} catch (SQLException e1) { // //// On appelle la méthode
											// retrieveGeoSite() qui fait
					// TODO Auto-generated catch block ////// la recherche dans
					// la BDD du nom de site choisi
					e1.printStackTrace(); //
				} //
				progressBar.setVisible(true);
				btnOk.setVisible(false);
				choixNbBilk.setVisible(false);
				choixNbOrdonne.setVisible(false);
				visit1 = new Visit(txtTapezVotreRecherche.getText());
				thread = new ThreadRecup((Integer) choixNbOrdonne
						.getSelectedItem(), (Integer) choixNbBilk
						.getSelectedItem(), FenetrePropre.this);
				thread.start();
			}
		});
		getContentPane().add(btnOk);
		
		btnCentral.setBounds(593, 212, 457, 359);
		btnCentral.setVisible(false);
		btnCentral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1ou2 = (p1ou2==1) ? 2 : 1;
				affichageImage();
				System.out.println(p1ou2);
			}
		});
		btnCentral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		btnCentral.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		getContentPane().add(btnCentral);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(23, 56, 193, 27);
		this.fillComboBox();
		getContentPane().add(comboBox);
		
	}
	
	public JTextField getJText(){
		return txtTapezVotreRecherche;
	}
	
	public void BoutonsInterfaceDeplacementInvisible(){
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
		
		// System.out.println(ligne);
		// System.out.println(colonne);
		if (visit1.getVisitState() == 1) {
			if ((visit1.colonne == 0) && (visit1.ligne == visit1.tabOrdonne.length - 1)) {
				btnSo.setVisible(false);
				btnO.setVisible(false);
				btnNo.setVisible(false);
				btnN.setVisible(true);
				btnNe.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(false);
				btnS.setVisible(false);
			} else if ((visit1.colonne == visit1.tabOrdonne.length - 1) && (visit1.ligne == 0)) {
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
			} else if ((visit1.colonne == (visit1.tabOrdonne.length - 1)) && (visit1.ligne == (visit1.tabOrdonne.length - 1))) {
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

			else if (visit1.colonne == (visit1.tabOrdonne.length - 1)) {
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
			} else if (visit1.ligne == (visit1.tabOrdonne.length - 1)) {
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
			}

			else if (visit1.i == 1) {
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
	
	public void fillComboBox() throws SQLException{
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
						System.out.println(s);
						comboBox.addItem(s);
					}

				}
				System.out.println(comboBox.getItemCount());
				result.close();
				state.close();
	}

	public void affichageImage() {
		URL url1 = null;

		try {
			//URL url1;
			// On essaye de transfomer urlString en type URL
			if (visit1.getVisitState() == 0) {
				url1 = new URL(visit1.tabBilk[visit1.i].getPic(p1ou2).getLargeUrl());
			} else if (visit1.getVisitState() == 1) {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne][visit1.colonne].getPic(p1ou2).getLargeUrl());
			}
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}
		ImageIcon img = new ImageIcon(url1);
		btnCentral.setIcon(img);
		if (btnSe.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne + 1].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img1 = new ImageIcon(url1);
			btnSe.setIcon(img1);
		}

		if (btnS.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img2 = new ImageIcon(url1);
			btnS.setIcon(img2);
		}

		if (btnSo.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne - 1].getPic(1).getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img3 = new ImageIcon(url1);
			btnSo.setIcon(img3);
		}

		if (btnO.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne][visit1.colonne - 1].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img4 = new ImageIcon(url1);
			btnO.setIcon(img4);
		}

		if (btnNo.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne - 1].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img5 = new ImageIcon(url1);
			btnNo.setIcon(img5);
		}

		if (btnN.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img6 = new ImageIcon(url1);
			btnN.setIcon(img6);
		}

		if (btnNe.isVisible()) {
			try {
				url1 = new URL(visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne + 1].getPic(1).getSmallUrl());

			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img7 = new ImageIcon(url1);
			btnNe.setIcon(img7);
		}

		if (btnE.isVisible()) {
			try {

				url1 = new URL(visit1.tabOrdonne[visit1.ligne][visit1.colonne + 1].getPic(1).getSmallUrl());

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