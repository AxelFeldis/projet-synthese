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

public class FenetreWB {

	private JFrame frmVisioscope;
	private JTextField txtTapezVotreRecherche;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JComboBox comboBox;
	private URL url1; // url de la photo
	public Photo[] tabPhotosVrac; // tableau de photo
	public Photo[][] tabPhotosOrdonnees; // tableau de photo
	private GeoInterface geoInterface; // Interface qui permettra de géré les
										// géoDatas
	private GeoData positionGeographique; // géoData qui permettra de récupérer
											// la longitude et la latitude
	public Integer i = 0;
	private int colonne = 0;
	private int ligne = 0;

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
		frmVisioscope.setSize(1500, 800);

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
		// lblNewLabel.setMaximumSize(taille);
		// lblNewLabel.setPreferredSize(taille);
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridheight = 19;
		gbc_lblNewLabel.gridwidth = 29;
		gbc_lblNewLabel.gridx = 20;
		gbc_lblNewLabel.gridy = 4;
		frmVisioscope.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		// Champs qui permet à l'utilisateur de saisir sa recherche
		txtTapezVotreRecherche = new JTextField();
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

		// BOUTON OK!!!!!!!!!!!!!!!
		JButton btnNewButton_2 = new JButton("Ok!");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 2) {
					// on récupère le texte tapé par l'utilisateur
					// String tagDemande = txtTapezVotreRecherche.getText();
					try {
						tabPhotosVrac = recupererPhotosVrac();
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
					// On récupère l'url de la photo sous forme de string
					String urlString1 = tabPhotosVrac[i].getMediumUrl();
					System.out.println(urlString1);

					try {
						// On essaye de transfomer urlString en type URL
						url1 = new URL(urlString1);
					} catch (MalformedURLException e1) {
						System.out.println(e1.getMessage());
					}

					affichageImage(url1);

					btnNewButton.setVisible(true);
					btnNewButton_1.setVisible(true);
				}
				if (comboBox.getSelectedIndex() == 0) {
					// on récupère le texte tapé par l'utilisateur
					// String tagDemande = txtTapezVotreRecherche.getText();
					try {
						tabPhotosOrdonnees = recupererPhotosOrdonnees(25, 78.040674, 27.171792,  78.043635, 27.174541);
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
					// On récupère l'url de la photo sous forme de string
					String urlString1 = tabPhotosOrdonnees[ligne][colonne]
							.getMediumUrl();
					System.out.println(urlString1);

					try {
						// On essaye de transfomer urlString en type URL
						url1 = new URL(urlString1);
					} catch (MalformedURLException e1) {
						System.out.println(e1.getMessage());
					}

					affichageImage(url1);

				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 12;
		gbc_btnNewButton_2.gridy = 6;
		frmVisioscope.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);

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
		btnNewButton_1 = new JButton("Précédent");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i = i - 1;
				// On récupère l'url de la photo sous forme de string
				String urlString1 = tabPhotosVrac[i].getMediumUrl();
				System.out.println(urlString1);

				try {
					// On essaye de transfomer urlString en type URL
					url1 = new URL(urlString1);
				} catch (MalformedURLException e1) {
					System.out.println(e1.getMessage());
				}

				affichageImage(url1);
				try {
					positionGeographique = geoInterface
							.getLocation(tabPhotosVrac[i].getId());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FlickrException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(positionGeographique.getLatitude());
				System.out.println(positionGeographique.getLongitude());
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 19;
		gbc_btnNewButton_1.gridy = 23;
		frmVisioscope.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);

		btnNewButton = new JButton(" Suivant ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				i = i + 1;
				// On récupère l'url de la photo sous forme de string
				String urlString1 = tabPhotosVrac[i].getMediumUrl();
				System.out.println(urlString1);

				try {
					// On essaye de transfomer urlString en type URL
					url1 = new URL(urlString1);
				} catch (MalformedURLException e1) {
					System.out.println(e1.getMessage());
				}

				affichageImage(url1);
				try {
					positionGeographique = geoInterface
							.getLocation(tabPhotosVrac[i].getId());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FlickrException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(positionGeographique.getLatitude());
				System.out.println(positionGeographique.getLongitude());
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 47;
		gbc_btnNewButton.gridy = 23;
		frmVisioscope.getContentPane().add(btnNewButton, gbc_btnNewButton);

		// BOUTON DE LA VISITE DE OUF
		JButton btnHautGauche = new JButton("Haut Gauche");
		btnHautGauche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("NO");
			}
		});
		GridBagConstraints gbc_btnHautGauche = new GridBagConstraints();
		gbc_btnHautGauche.gridwidth = 3;
		gbc_btnHautGauche.insets = new Insets(0, 0, 5, 5);
		gbc_btnHautGauche.gridx = 28;
		gbc_btnHautGauche.gridy = 23;
		frmVisioscope.getContentPane().add(btnHautGauche, gbc_btnHautGauche);

		JButton btnHaut = new JButton("Haut");
		btnHaut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("N");
			}
		});
		GridBagConstraints gbc_btnHaut = new GridBagConstraints();
		gbc_btnHaut.gridwidth = 3;
		gbc_btnHaut.insets = new Insets(0, 0, 5, 5);
		gbc_btnHaut.gridx = 31;
		gbc_btnHaut.gridy = 23;
		frmVisioscope.getContentPane().add(btnHaut, gbc_btnHaut);

		JButton btnHautDroite = new JButton("Haut Droite");
		btnHautDroite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("NE");
			}
		});
		GridBagConstraints gbc_btnHautDroite = new GridBagConstraints();
		gbc_btnHautDroite.gridwidth = 3;
		gbc_btnHautDroite.insets = new Insets(0, 0, 5, 5);
		gbc_btnHautDroite.gridx = 34;
		gbc_btnHautDroite.gridy = 23;
		frmVisioscope.getContentPane().add(btnHautDroite, gbc_btnHautDroite);

		JButton btnNewButton_3 = new JButton("    Gauche    ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("O");
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.gridwidth = 3;
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 28;
		gbc_btnNewButton_3.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnNewButton_4 = new JButton(" Bas ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("S");
			}
		});
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.gridwidth = 3;
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 31;
		gbc_btnNewButton_4.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_4, gbc_btnNewButton_4);

		JButton btnNewButton_5 = new JButton("    Droite    ");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seDeplacer("E");
			}
		});
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.gridwidth = 3;
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 34;
		gbc_btnNewButton_5.gridy = 24;
		frmVisioscope.getContentPane().add(btnNewButton_5, gbc_btnNewButton_5);

	}

	public void affichageImage(URL url) {
		ImageIcon img = new ImageIcon(url);
		lblNewLabel.setIcon(img);
	}

	// Fonction qui récupere les photos sur Flickr et qui les mets dans un
	// tableau
	public Photo[] recupererPhotosVrac() throws IOException, SAXException,
			FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		Photo[] tab = new Photo[1];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags); // On definit le paramètre avec ce tag
		param.setBBox("2.292", "48.856", "2.293", "48.857");
		// param.setMaxUploadDate(new Date(new Long(0)));
		// param.setMinUploadDate(new Date(System.currentTimeMillis()));
		// System.out.println(param.getHasGeo());

		try {
			// Récupération d'uneliste de photos
			listePhoto1 = photosInterface.search(param, 1, 0);
		} catch (FlickrException e3) {
			//System.out.println(e3.getErrorMessage());
			System.out.println("yo c'est l'erreur 1");
		} catch (IOException e1) {
			//System.out.println(e1);
			System.out.println("yo c'est l'erreur 2");
		} catch (SAXException e2) {
			//System.out.println(e2.getMessage());
			System.out.println("yo c'est l'erreur 3");
		}

		// ON MET LES PHOTOS DANS UN TABLEAU

		// for (int i = 0; i <= 1; i++) {
		tab[0] = (Photo) listePhoto1.get(0);
		// }
		return tab;
	}

	public Photo[][] recupererPhotosOrdonnees(int nbPhotos, double longMin, double latMin, double longMax, double latMax) throws IOException,
			SAXException, FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		Photo[][] tab = new Photo[(int) Math.sqrt(nbPhotos)][(int) Math.sqrt(nbPhotos)];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags);
		// On definit le paramètre avec ce tag
		double xStep = ((longMax - longMin)/(int) Math.sqrt(nbPhotos));
		double yStep = ((latMax - latMin)/(int) Math.sqrt(nbPhotos));
		System.out.println(xStep);
		System.out.println(yStep);
		Double xMin = longMin;
		Double yMin = latMin;
		Double xMax = longMin + xStep;
		Double yMax = latMin + yStep;
		//double step = 0.001;
		int l = 0;
		int c = 0;
		for (i = 0; i <= nbPhotos-1; i++) {
			System.out.println(xMin.toString());

			if (c < (Math.sqrt(nbPhotos)-1)) {
				param.setBBox(xMin.toString(), yMin.toString(),
						xMax.toString(), yMax.toString());
				try {
					// Récupération d'uneliste de photos
					System.out.println("yo yo yo");
					listePhoto1 = photosInterface.search(param, 1, 0);
					
				} catch (FlickrException e3) {
					//System.out.println(e3.getErrorMessage());
					System.out.println("yo c'est l'erreur 1");
				} catch (IOException e1) {
					//System.out.println(e1);
					System.out.println("yo c'est l'erreur 2");
				} catch (SAXException e2) {
					//System.out.println(e2.getMessage());
					System.out.println("yo c'est l'erreur 3");
				}
//					tab[l][c] = 
//				} else {
				System.out.println("yo yo yo");
					tab[l][c] = (Photo) listePhoto1.get(0);
				
				c++;
				xMin = xMax;
				xMax = xMax + xStep;
			} else if (c == (Math.sqrt(nbPhotos)-1)) {
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
				tab[l][c] = (Photo) listePhoto1.get(0);
				c = 0;
				l++;
				xMin = longMin;
				xMax = longMin + xStep;
				yMin = yMax;
				yMax = yMax + yStep;
			}

		}
		return tab;
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
		// On récupère l'url de la photo sous forme de string
		String urlString1 = tabPhotosOrdonnees[ligne][colonne].getMediumUrl();
		System.out.println(urlString1);

		try {
			// On essaye de transfomer urlString en type URL
			url1 = new URL(urlString1);
		} catch (MalformedURLException e1) {
			System.out.println(e1.getMessage());
		}

		affichageImage(url1);
		try {
			positionGeographique = geoInterface
					.getLocation(tabPhotosOrdonnees[ligne][colonne].getId());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("yo c'est l'erreur 1");
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("yo c'est l'erreur 2");
		} catch (FlickrException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("yo c'est l'erreur 3");
		}
		System.out.println(positionGeographique.getLatitude());
		System.out.println(positionGeographique.getLongitude());
		System.out.println("ligne : " + ligne);
		System.out.println("colonne : " + colonne);
	}
}
