import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
//import net.miginfocom.swing.MigLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.photos.geo.GeoInterface;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JProgressBar;

public class FenetreWB {

	private JFrame frmVisioscope;
	private JTextField txtTapezVotreRecherche;
	private JLabel lblNewLabel;
	private JButton btnPrec;
	private JButton btnOk;
	private JButton btnO;
	private JButton btnS;
	private JButton btnE;
	private JButton btnN;
	private JButton btnNO;
	private JButton btnNE;
	private JButton btnSuiv;
	JComboBox comboBox;
	URL url1; // url de la photo
	public Photo[] tabPhotosVrac; // tableau de photo
	public Photo[][] tabPhotosOrdonnees; // tableau de photo
	private GeoInterface geoInterface; // Interface qui permettra de géré les
										// géoDatas
	private GeoData positionGeographique; // géoData qui permettra de récupérer
											// la longitude et la latitude
	public Integer i = 0;
	int colonne = 0;
	int ligne = 0;
	private MonThread thread;
	private JProgressBar progressBar;
	int compteur = 0;
	int etat = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreWB window = new FenetreWB();
					window.frmVisioscope.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FenetreWB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVisioscope = new JFrame();
		frmVisioscope.getContentPane().setBackground(Color.DARK_GRAY);
		frmVisioscope.setFont(new Font("Arial", Font.BOLD, 12));
		frmVisioscope.setTitle("VisioScope");
		frmVisioscope.setSize(1000, 800);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frmVisioscope.getContentPane().setLayout(gridBagLayout);

		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 16;
		gbc_separator.gridy = 0;
		frmVisioscope.getContentPane().add(separator, gbc_separator);

		// Titre
		JLabel lblVisioscope = new JLabel("VisioScope");
		lblVisioscope.setForeground(Color.GREEN);
		lblVisioscope.setFont(new Font("Comic Sans MS", Font.BOLD, 35));
		GridBagConstraints gbc_lblVisioscope = new GridBagConstraints();
		gbc_lblVisioscope.gridheight = 2;
		gbc_lblVisioscope.gridwidth = 6;
		gbc_lblVisioscope.insets = new Insets(0, 0, 5, 5);
		gbc_lblVisioscope.gridx = 22;
		gbc_lblVisioscope.gridy = 1;
		frmVisioscope.getContentPane().add(lblVisioscope, gbc_lblVisioscope);

		// Emplacement de l'image
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(
				"/Users/thunderbzh/Desktop/roller.jpg"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		Dimension taille = new Dimension(1000, 650);
		lblNewLabel.setMinimumSize(new Dimension(1050, 650));
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridheight = 19;
		gbc_lblNewLabel.gridwidth = 29;
		gbc_lblNewLabel.gridx = 20;
		gbc_lblNewLabel.gridy = 4;
		frmVisioscope.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		// Champs qui permet à l'utilisateur de saisir sa recherche
		txtTapezVotreRecherche = new JTextField();
		txtTapezVotreRecherche.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e) {
		      //TODO : effacer le texte
				//if (txtTapezVotreRecherche.getText() == "Tapez votre recherche") {
					txtTapezVotreRecherche.setText("");
				//}
				
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				//if (txtTapezVotreRecherche.getText() == "Tapez votre recherche") {
//					txtTapezVotreRecherche.setText("");
//				//}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		   });
		txtTapezVotreRecherche.setText("Tapez votre recherche");
		GridBagConstraints gbc_txtTapezVotreRecherche = new GridBagConstraints();
		gbc_txtTapezVotreRecherche.gridwidth = 7;
		gbc_txtTapezVotreRecherche.insets = new Insets(0, 0, 5, 5);
		gbc_txtTapezVotreRecherche.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTapezVotreRecherche.gridx = 4;
		gbc_txtTapezVotreRecherche.gridy = 5;
		frmVisioscope.getContentPane().add(txtTapezVotreRecherche,
				gbc_txtTapezVotreRecherche);
		txtTapezVotreRecherche.setColumns(10);

		// comboBox qui permet de choisir le type de visite
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 7;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 6;
		frmVisioscope.getContentPane().add(comboBox, gbc_comboBox);
		comboBox.addItem("Visite intérieure");
		comboBox.addItem("Visite extérieure");
		comboBox.addItem("Photos aléatoires");

		progressBar = new JProgressBar();
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 5;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 6;
		gbc_progressBar.gridy = 7;
		frmVisioscope.getContentPane().add(progressBar, gbc_progressBar);

		// BOUTON OK!!!!!!!!!!!!!!!
		btnOk = new JButton("Ok!");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 2) {
					etat = 1;
					// on récupère le texte tapé par l'utilisateur
					// String tagDemande = txtTapezVotreRecherche.getText();
					thread = new MonThread(FenetreWB.this, 100);
					thread.start();

					
				}
				if (comboBox.getSelectedIndex() == 0) {
					etat = 2;
					thread = new MonThread(FenetreWB.this, 16);
					thread.start();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					affichageImage();
					btnOk.setVisible(true);
					btnO.setVisible(true);
					btnS.setVisible(true);
					btnE.setVisible(true);
					btnSuiv.setVisible(true);
					btnPrec.setVisible(true);
					btnN.setVisible(true);
					btnNO.setVisible(true);
					btnNE.setVisible(true);
					

				}
			}
		});
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 12;
		gbc_btnOk.gridy = 6;
		frmVisioscope.getContentPane().add(btnOk, gbc_btnOk);

		// Zone de texte expliquant l'application
		JTextArea txtrBienvenueSurVisioscope = new JTextArea();
		txtrBienvenueSurVisioscope
				.setFont(new Font("SansSerif", Font.PLAIN, 15));
		txtrBienvenueSurVisioscope
				.setText("Bienvenue sur VisioScope, \nune application vous\npermettant d'effectuer\ndes visites virtuelles\nde n'importe quel site\ntouristique, archéologique\nou bien de monuments.\nEt ce n'importe où sur la\nTerre!\nCommencez par taper votre\nrecherche ci-dessus,\nchoisissez quel type de visite\nvous souhaitez effectuer et\ncliquez enfin sur le bouton \n\"Ok!\"");
		txtrBienvenueSurVisioscope.setForeground(Color.GREEN);
		txtrBienvenueSurVisioscope.setEditable(false);
		txtrBienvenueSurVisioscope.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_txtrBienvenueSurVisioscope = new GridBagConstraints();
		gbc_txtrBienvenueSurVisioscope.gridheight = 11;
		gbc_txtrBienvenueSurVisioscope.gridwidth = 10;
		gbc_txtrBienvenueSurVisioscope.insets = new Insets(0, 0, 5, 5);
		gbc_txtrBienvenueSurVisioscope.fill = GridBagConstraints.BOTH;
		gbc_txtrBienvenueSurVisioscope.gridx = 6;
		gbc_txtrBienvenueSurVisioscope.gridy = 8;
		frmVisioscope.getContentPane().add(txtrBienvenueSurVisioscope,
				gbc_txtrBienvenueSurVisioscope);

		// BOUTON DE LE VISITE EN VRAC
		btnPrec = new JButton("Précédent");
		btnPrec.setVisible(false);
		btnPrec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i = i - 1;
				affichageImage();
			}
		});
		GridBagConstraints gbc_btnPrec = new GridBagConstraints();
		gbc_btnPrec.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrec.gridx = 19;
		gbc_btnPrec.gridy = 23;
		frmVisioscope.getContentPane().add(btnPrec, gbc_btnPrec);

		btnSuiv = new JButton(" Suivant ");
		btnSuiv.setVisible(false);
		btnSuiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i = i + 1;
				affichageImage();
			}
		});
		GridBagConstraints gbc_btnSuiv = new GridBagConstraints();
		gbc_btnSuiv.insets = new Insets(0, 0, 5, 5);
		gbc_btnSuiv.gridx = 47;
		gbc_btnSuiv.gridy = 23;
		frmVisioscope.getContentPane().add(btnSuiv, gbc_btnSuiv);

		// BOUTON DE LA VISITE DE OUF
		btnNO = new JButton("Haut Gauche");
		btnNO.setVisible(false);
		btnNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("NO");
			}
		});
		GridBagConstraints gbc_btnNO = new GridBagConstraints();
		gbc_btnNO.gridwidth = 3;
		gbc_btnNO.insets = new Insets(0, 0, 5, 5);
		gbc_btnNO.gridx = 28;
		gbc_btnNO.gridy = 23;
		frmVisioscope.getContentPane().add(btnNO, gbc_btnNO);

		btnN = new JButton("Haut");
		btnN.setVisible(false);
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("N");
			}
		});
		GridBagConstraints gbc_btnN = new GridBagConstraints();
		gbc_btnN.gridwidth = 3;
		gbc_btnN.insets = new Insets(0, 0, 5, 5);
		gbc_btnN.gridx = 31;
		gbc_btnN.gridy = 23;
		frmVisioscope.getContentPane().add(btnN, gbc_btnN);

		btnNE = new JButton("Haut Droite");
		btnNE.setVisible(false);
		btnNE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("NE");
			}
		});
		GridBagConstraints gbc_btnNE = new GridBagConstraints();
		gbc_btnNE.gridwidth = 3;
		gbc_btnNE.insets = new Insets(0, 0, 5, 5);
		gbc_btnNE.gridx = 34;
		gbc_btnNE.gridy = 23;
		frmVisioscope.getContentPane().add(btnNE, gbc_btnNE);

		btnO = new JButton("    Gauche    ");
		btnO.setVisible(false);
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("O");
			}
		});
		GridBagConstraints gbc_btnO = new GridBagConstraints();
		gbc_btnO.gridwidth = 3;
		gbc_btnO.insets = new Insets(0, 0, 5, 5);
		gbc_btnO.gridx = 28;
		gbc_btnO.gridy = 24;
		frmVisioscope.getContentPane().add(btnO, gbc_btnO);

		btnS = new JButton(" Bas ");
		btnS.setVisible(false);
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("S");
			}
		});
		GridBagConstraints gbc_btnS = new GridBagConstraints();
		gbc_btnS.gridwidth = 3;
		gbc_btnS.insets = new Insets(0, 0, 5, 5);
		gbc_btnS.gridx = 31;
		gbc_btnS.gridy = 24;
		frmVisioscope.getContentPane().add(btnS, gbc_btnS);

		btnE = new JButton("    Droite    ");
		btnE.setVisible(false);
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("E");
			}
		});
		GridBagConstraints gbc_btnE = new GridBagConstraints();
		gbc_btnE.gridwidth = 3;
		gbc_btnE.insets = new Insets(0, 0, 5, 5);
		gbc_btnE.gridx = 34;
		gbc_btnE.gridy = 24;
		frmVisioscope.getContentPane().add(btnE, gbc_btnE);
		frmVisioscope.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { separator,
						lblVisioscope, txtTapezVotreRecherche, lblNewLabel,
						comboBox, btnOk, txtrBienvenueSurVisioscope,
						btnPrec, btnSuiv, btnNO, btnN,
						btnNE, btnO, btnS,
						btnE }));

	}

	public void affichageImage() {

		try {
			// On essaye de transfomer urlString en type URL
			if (etat == 1) {
				url1 = new URL(tabPhotosVrac[i].getMediumUrl());
			}
			if (etat == 2) {
				url1 = new URL(
						tabPhotosOrdonnees[ligne][colonne].getMediumUrl());
			}
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}
		ImageIcon img = new ImageIcon(url1);
		lblNewLabel.setIcon(img);
		try {
			if (etat == 1) {
				positionGeographique = geoInterface
						.getLocation(tabPhotosVrac[i].getId());
			}
			if (etat == 2) {
				positionGeographique = geoInterface
						.getLocation(tabPhotosOrdonnees[ligne][colonne].getId());
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("yo c'est l'erreur 1");
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("yo c'est l'erreur 2");
		} catch (FlickrException e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			System.out.println("yo c'est l'erreur 3");
		}
		System.out.println(positionGeographique.getLatitude());
		System.out.println(positionGeographique.getLongitude());
		// System.out.println("ligne : " + ligne);
		// System.out.println("colonne : " + colonne);
	}

	// Fonction qui récupere les photos sur Flickr et qui les mets dans un
	// tableau
	public void recupererPhotosVrac(int nbPhotos) throws IOException,
			SAXException, FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		tabPhotosVrac = new Photo[nbPhotos];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags); // On definit le paramètre avec ce tag
		// param.setBBox("2.292", "48.856", "2.293", "48.857");

		try {
			// Récupération d'uneliste de photos
			listePhoto1 = photosInterface.search(param, nbPhotos, 0);
		} catch (FlickrException e3) {
			// System.out.println(e3.getErrorMessage());
			System.out.println("yo c'est l'erreur 1");
		} catch (IOException e1) {
			// System.out.println(e1);
			System.out.println("yo c'est l'erreur 2");
		} catch (SAXException e2) {
			// System.out.println(e2.getMessage());
			System.out.println("yo c'est l'erreur 3");
		}
		
		progressBar.setMaximum(nbPhotos);
		for (int compt1 = 0; compt1 <= nbPhotos - 1; compt1++) {
			compteur++;
			progressBar.setValue(compteur);
			tabPhotosVrac[compt1] = (Photo) listePhoto1.get(compt1);
			System.out.println(tabPhotosVrac[compt1].getUrl());
			System.out.println(compt1);
			System.out.println(compteur);
		}
		affichageImage();
		btnSuiv.setVisible(true);
		btnPrec.setVisible(true);
	}

	public void recupererPhotosOrdonnees(int nbPhotos, double longMin,
			double latMin, double longMax, double latMax) throws IOException,
			SAXException, FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		this.tabPhotosOrdonnees = new Photo[(int) Math.sqrt(nbPhotos)][(int) Math
				.sqrt(nbPhotos)];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags);
		// On definit le paramètre avec ce tag
		double xStep = ((longMax - longMin) / (int) Math.sqrt(nbPhotos));
		double yStep = ((latMax - latMin) / (int) Math.sqrt(nbPhotos));
		System.out.println(xStep);
		System.out.println(yStep);
		Double xMin = longMin;
		Double yMin = latMin;
		Double xMax = longMin + xStep;
		Double yMax = latMin + yStep;
		int l = 0;
		int c = 0;
		progressBar.setMaximum(nbPhotos);
		for (int compt2 = 0; compt2 <= nbPhotos - 1; compt2++) {
			System.out.println(xMin.toString());
			compteur++;
			progressBar.setValue(compteur);

			if (c < (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(xMin.toString(), yMin.toString(),
						xMax.toString(), yMax.toString());
				try {
					// Récupération d'uneliste de photos
					listePhoto1 = photosInterface.search(param, 1, 0);

				} catch (FlickrException e3) {
					// System.out.println(e3.getErrorMessage());
					System.out.println("yo c'est l'erreur 1");
				} catch (IOException e1) {
					// System.out.println(e1);
					System.out.println("yo c'est l'erreur 2");
				} catch (SAXException e2) {
					// System.out.println(e2.getMessage());
					System.out.println("yo c'est l'erreur 3");
				}
				this.tabPhotosOrdonnees[l][c] = (Photo) listePhoto1.get(0);

				c++;
				xMin = xMax;
				xMax = xMax + xStep;
			} else if (c == (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(String.valueOf(xMin), String.valueOf(yMin),
						String.valueOf(xMax), String.valueOf(yMax));
				try {
					// Récupération d'uneliste de photos
					listePhoto1 = photosInterface.search(param, 1, 0);
				} catch (FlickrException e3) {
					System.out.println(e3.getErrorMessage());
				} catch (IOException e1) {
					System.out.println(e1);
				} catch (SAXException e2) {
					System.out.println(e2.getMessage());
				}
				this.tabPhotosOrdonnees[l][c] = (Photo) listePhoto1.get(0);
				c = 0;
				l++;
				xMin = longMin;
				xMax = longMin + xStep;
				yMin = yMax;
				yMax = yMax + yStep;
			}

		}
	}

	public void seDeplacer(String direction) {
		switch (direction) {

		case "O":
			colonne--;
			break;

		case "NO":
			colonne--;
			ligne--;
			break;

		case "N":
			ligne--;
			break;

		case "NE":
			ligne--;
			colonne++;
			break;

		case "E":
			colonne++;
			break;

		case "S":
			ligne++;
			break;
		}
		affichageImage();
	}
}

class MonThread extends Thread {

	private FenetreWB fenetre;
	private int nbPhotos;

	public MonThread(FenetreWB f, int nbP) {
		fenetre = f;
		nbPhotos = nbP;
	}

	public void run() {
		// on récupère le texte tapé par l'utilisateur
		// String tagDemande = txtTapezVotreRecherche.getText();
		try {
			if (fenetre.etat == 2) {
				fenetre.recupererPhotosOrdonnees(nbPhotos, 78.040674, 27.171792,
						78.043635, 27.174541);
				//fenetre.recupererPhotosOrdonnees(16, 2.294, 48.853, 2.297,
				//48.858);
			}
			if (fenetre.etat == 1) {
				fenetre.recupererPhotosVrac(nbPhotos);
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SAXException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (FlickrException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		fenetre.compteur = 0;
	}

}
