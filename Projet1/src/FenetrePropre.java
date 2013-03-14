import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
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

public class FenetrePropre extends JFrame {

	private static final long serialVersionUID = 1L;
	public JTextField txtTapezVotreRecherche;
	public JLabel lblPhotoCentrale;
	public JButton btnPrecedent;
	public JButton btnOk;
	public JButton btnO;
	public JButton btnS;
	public JButton btnE;
	public JButton btnN;
	public JButton btnSo;
	public JButton btnSe;
	public JButton btnNo;
	public JButton btnNe;
	public JButton btnSuivant;
	public JButton button1;
	public JButton button2;
	public JButton button3;
	public JButton button4;
	public JButton button5;
	public JComboBox<String> comboBox;
	public URL url1; // url de la photo
	private ThreadRecup thread;
	public JProgressBar progressBar;
	int compteur = 0;
	static PhotosInterface photosInterface;
	public Visit visit1;

	public FenetrePropre() {
		setTitle("VisioScope");
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.GRAY);

		JLabel lblTitre = new JLabel("VisioScope");
		lblTitre.setBounds(530, 24, 152, 59);
		getContentPane().add(lblTitre);
		lblTitre.setForeground(Color.GRAY);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 28));

		txtTapezVotreRecherche = new JTextField();
		txtTapezVotreRecherche.setBounds(23, 147, 100, 20);
		getContentPane().add(txtTapezVotreRecherche);
		txtTapezVotreRecherche.setColumns(10);

		JTextArea txtrBienvenueSurVisioscope = new JTextArea();
		txtrBienvenueSurVisioscope
				.setText("Bienvenue sur VisioScope, \nune application vous\npermettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!");
		txtrBienvenueSurVisioscope.setBounds(23, 348, 249, 285);
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		getContentPane().add(txtrBienvenueSurVisioscope);
		txtrBienvenueSurVisioscope.setForeground(Color.GRAY);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(23, 190, 100, 20);
		getContentPane().add(comboBox);
		comboBox.addItem("Visite intérieure");
		comboBox.addItem("Visite extérieure");
		comboBox.addItem("Photos aléatoires");

		lblPhotoCentrale = new JLabel("");
		lblPhotoCentrale.setBounds(478, 168, 644, 435);
		lblPhotoCentrale
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblPhotoCentrale
				.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
		getContentPane().add(lblPhotoCentrale);

		btnPrecedent = new JButton("Precedent");
		btnPrecedent.setBounds(309, 365, 89, 31);
		getContentPane().add(btnPrecedent);
		btnPrecedent.setVisible(false);
		btnPrecedent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("prec", FenetrePropre.this);
			}
		});

		btnSo = new JButton("SO");
		btnSo.setBounds(333, 566, 135, 122);
		getContentPane().add(btnSo);
		btnSo.setVisible(false);
		btnSo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("SO", FenetrePropre.this);
			}
		});

		btnNo = new JButton("NO");
		btnNo.setBounds(333, 88, 135, 122);
		getContentPane().add(btnNo);
		btnNo.setVisible(false);
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("NO", FenetrePropre.this);
			}
		});

		btnNe = new JButton("NE");
		btnNe.setBounds(1132, 88, 121, 122);
		getContentPane().add(btnNe);
		btnNe.setVisible(false);
		btnNe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("NE", FenetrePropre.this);
			}
		});

		btnS = new JButton("S");
		btnS.setBounds(760, 614, 121, 122);
		getContentPane().add(btnS);
		btnS.setVisible(false);
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("S", FenetrePropre.this);
			}
		});

		btnSe = new JButton("SE");
		btnSe.setBounds(1132, 566, 121, 122);
		getContentPane().add(btnSe);
		btnSe.setVisible(false);
		btnSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("SE", FenetrePropre.this);
			}
		});

		btnE = new JButton("E");
		btnE.setBounds(1132, 318, 121, 125);
		getContentPane().add(btnE);
		btnE.setVisible(false);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("E", FenetrePropre.this);
			}
		});

		btnSuivant = new JButton("Suivant");
		btnSuivant.setBounds(1185, 365, 89, 31);
		getContentPane().add(btnSuivant);
		btnSuivant.setVisible(false);
		btnSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetrePropre.this);
			}
		});

		btnN = new JButton("N");
		btnN.setBounds(760, 35, 135, 122);
		getContentPane().add(btnN);
		btnN.setVisible(false);
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("N", FenetrePropre.this);
			}
		});

		btnO = new JButton("O");
		btnO.setBounds(333, 318, 135, 125);
		getContentPane().add(btnO);
		btnO.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("O", FenetrePropre.this);
			}
		});

		button3 = new JButton("3");
		button3.setBounds(770, 670, 89, 81);
		getContentPane().add(button3);
		button3.setVisible(false);

		button2 = new JButton("2");
		button2.setBounds(661, 670, 89, 81);
		getContentPane().add(button2);
		button2.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("prec", FenetrePropre.this);
			}
		});

		button4 = new JButton("4");
		button4.setBounds(908, 670, 89, 81);
		getContentPane().add(button4);
		button4.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("suiv", FenetrePropre.this);
			}
		});

		button5 = new JButton("5");
		button5.setBounds(1026, 670, 89, 81);
		getContentPane().add(button5);
		button5.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2suiv", FenetrePropre.this);
			}
		});

		button1 = new JButton("1");
		button1.setBounds(530, 670, 89, 81);
		getContentPane().add(button1);
		button1.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visit1.move("2prec", FenetrePropre.this);
			}
		});

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuitter.setBounds(1164, 699, 89, 23);
		getContentPane().add(btnQuitter);
		btnQuitter.setVisible(false);

		progressBar = new JProgressBar();
		progressBar.setBounds(35, 80, 146, 14);
		getContentPane().add(progressBar);

		btnOk = new JButton("Ok");
		btnOk.setBounds(154, 146, 89, 23);
		getContentPane().add(btnOk);
		setSize(1300, 800);
		btnOk.setVisible(true);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 2) {
					visit1 = new Visit(txtTapezVotreRecherche.getText());
					visit1.setVisitState(1);
					thread = new ThreadRecup(10, FenetrePropre.this);
					thread.start();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
					visit1.move("init", FenetrePropre.this);
				}
				if (comboBox.getSelectedIndex() == 0) {
					visit1 = new Visit(txtTapezVotreRecherche.getText());
					visit1.setVisitState(2);
					thread = new ThreadRecup(81, FenetrePropre.this);
					thread.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					btnS.setVisible(true);
					btnE.setVisible(true);
					btnSe.setVisible(true);
					btnSuivant.setVisible(false);
					btnPrecedent.setVisible(false);
					visit1.move("init", FenetrePropre.this);
				}
			}
		});

		setVisible(true);
	}

	public void affichageImage() {

		try {
			// On essaye de transfomer urlString en type URL
			if (visit1.getVisitState() == 1) {
				url1 = new URL(visit1.tabBilk[visit1.i].getMediumUrl());
			} else if (visit1.getVisitState() == 2) {
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