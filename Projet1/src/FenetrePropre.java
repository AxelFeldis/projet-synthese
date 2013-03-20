import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
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
	public JTextField txtTapezVotreRecherche = new JTextField();
	JLabel lblTitre = new JLabel("VisioScope");
	public JLabel lblPhotoCentrale = new JLabel("");
	JTextArea txtrBienvenueSurVisioscope = new JTextArea();
	public JButton btnPrecedent = new JButton("Precedent");
	public JButton btnOk = new JButton("Ok");
	public JButton btnO = new JButton("O");
	public JButton btnS = new JButton("S");
	public JButton btnE = new JButton("E");
	public JButton btnN = new JButton("N");
	public JButton btnSo = new JButton("SO");
	public JButton btnSe = new JButton("SE");
	public JButton btnNo = new JButton("NO");
	public JButton btnNe = new JButton("NE");
	public JButton btnSuivant = new JButton("Suivant");
	public JButton button1 = new JButton("1");
	public JButton button2 = new JButton("2");
	public JButton button3 = new JButton("3");
	public JButton button4 = new JButton("4");
	public JButton button5 = new JButton("5");
	public JButton btnQuitter = new JButton("Quitter");
	public JButton btnIn = new JButton("In");
	public JButton btnOut = new JButton("Out");;
	public JButton btnBilk = new JButton("Bilk");
	public URL url1; // url de la photo
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

		lblPhotoCentrale.setBounds(478, 168, 644, 435);
		lblPhotoCentrale
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblPhotoCentrale
				.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		getContentPane().add(lblPhotoCentrale);

		btnPrecedent.setBounds(309, 365, 89, 31);
		btnPrecedent.setVisible(false);
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("prec", FenetrePropre.this);
			}
		});
		getContentPane().add(btnPrecedent);

		btnSo.setBounds(333, 566, 135, 122);
		btnSo.setVisible(false);
		btnSo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("SO", FenetrePropre.this);
			}
		});
		getContentPane().add(btnSo);

		btnNo.setBounds(333, 88, 135, 122);
		btnNo.setVisible(false);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("NO", FenetrePropre.this);
			}
		});
		getContentPane().add(btnNo);

		btnNe.setBounds(1132, 88, 121, 122);
		btnNe.setVisible(false);
		btnNe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("NE", FenetrePropre.this);
			}
		});
		getContentPane().add(btnNe);

		btnS.setBounds(760, 614, 121, 122);
		btnS.setVisible(false);
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("S", FenetrePropre.this);
			}
		});
		getContentPane().add(btnS);

		btnSe.setBounds(1132, 566, 121, 122);

		btnSe.setVisible(false);
		btnSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("SE", FenetrePropre.this);
			}
		});
		getContentPane().add(btnSe);

		btnE.setBounds(1132, 318, 121, 125);
		btnE.setVisible(false);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("E", FenetrePropre.this);
			}
		});
		getContentPane().add(btnE);

		btnSuivant.setBounds(1185, 365, 89, 31);
		btnSuivant.setVisible(false);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetrePropre.this);
			}
		});
		getContentPane().add(btnSuivant);

		btnN.setBounds(760, 35, 135, 122);

		btnN.setVisible(false);
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("N", FenetrePropre.this);
			}
		});
		getContentPane().add(btnN);

		btnO.setBounds(333, 318, 135, 125);
		btnO.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("O", FenetrePropre.this);
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
				visit1.move("prec", FenetrePropre.this);
			}
		});
		getContentPane().add(button2);

		button4.setBounds(908, 670, 89, 81);
		button4.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetrePropre.this);
			}
		});
		getContentPane().add(button4);

		button5.setBounds(1026, 670, 89, 81);
		button5.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2suiv", FenetrePropre.this);
			}
		});
		getContentPane().add(button5);

		button1.setBounds(530, 670, 89, 81);
		button1.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2prec", FenetrePropre.this);
			}
		});
		getContentPane().add(button1);

		btnQuitter.setBounds(1164, 699, 89, 23);
		btnQuitter.setVisible(false);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				btnQuitter.setVisible(false);
				btnOk.setVisible(true);
				btnBilk.setVisible(false);
				btnIn.setVisible(false);
				btnOut.setVisible(false);
				choixNbBilk.setVisible(true);
				choixNbOrdonne.setVisible(true);
				lblPhotoCentrale.setVisible(false);
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
				visit1.setVisitState(0);
				btnSuivant.setVisible(true);
				btnPrecedent.setVisible(true);
				btnN.setVisible(false);
				btnNe.setVisible(false);
				btnNo.setVisible(false);
				btnS.setVisible(false);
				btnSe.setVisible(false);
				btnSo.setVisible(false);
				btnE.setVisible(false);
				btnO.setVisible(false);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetrePropre.this);
			}
		});
		getContentPane().add(btnBilk);

		btnIn.setBounds(92, 178, 52, 23);
		btnIn.setVisible(false);
		btnIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(1);
				btnS.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnSuivant.setVisible(false);
				btnPrecedent.setVisible(false);
				button1.setVisible(false);
				button2.setVisible(false);
				button3.setVisible(false);
				button4.setVisible(false);
				button5.setVisible(false);
				btnQuitter.setVisible(true);
				visit1.move("init", FenetrePropre.this);
			}
		});
		getContentPane().add(btnIn);

		btnOut.setBounds(164, 178, 52, 23);
		btnOut.setVisible(false);
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				visit1.setVisitState(1);
				btnS.setVisible(true);
				btnE.setVisible(true);
				btnSe.setVisible(true);
				btnSuivant.setVisible(false);
				btnPrecedent.setVisible(false);
				visit1.move("init", FenetrePropre.this);
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

		comboBox = new JComboBox<String>();
		comboBox.setBounds(23, 56, 193, 27);
		this.fillComboBox();
		getContentPane().add(comboBox);
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

		try {
			// On essaye de transfomer urlString en type URL
			if (visit1.getVisitState() == 0) {
				url1 = new URL(visit1.tabBilk[visit1.i].getMediumUrl());
			} else if (visit1.getVisitState() == 1) {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne][visit1.colonne]
								.getMediumUrl());
			}
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}
		ImageIcon img = new ImageIcon(url1);
		lblPhotoCentrale.setIcon(img);
		if (btnSe.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne + 1]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img1 = new ImageIcon(url1);
			btnSe.setIcon(img1);
		}

		if (btnS.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img2 = new ImageIcon(url1);
			btnS.setIcon(img2);
		}

		if (btnSo.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne + 1][visit1.colonne - 1]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img3 = new ImageIcon(url1);
			btnSo.setIcon(img3);
		}

		if (btnO.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne][visit1.colonne - 1]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img4 = new ImageIcon(url1);
			btnO.setIcon(img4);
		}

		if (btnNo.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne - 1]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img5 = new ImageIcon(url1);
			btnNo.setIcon(img5);
		}

		if (btnN.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img6 = new ImageIcon(url1);
			btnN.setIcon(img6);
		}

		if (btnNe.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne - 1][visit1.colonne + 1]
								.getSmallUrl());
			} catch (MalformedURLException e1) {
				System.out.println(e1.getMessage());
			}
			ImageIcon img7 = new ImageIcon(url1);
			btnNe.setIcon(img7);
		}

		if (btnE.isVisible()) {
			try {
				url1 = new URL(
						visit1.tabOrdonne[visit1.ligne][visit1.colonne + 1]
								.getSmallUrl());
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