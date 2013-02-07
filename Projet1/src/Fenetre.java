import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.photos.geo.GeoInterface;

public class Fenetre extends JFrame {

	// private JPanel Panel; //C'est ce qui contiendra tous les �l�ments de
	// notre fen�tre
	private JLabel labelTitre;
	private JLabel label1; // Label fixe : "Veuillez saisir un monument :"
	private JTextField motCherche; // El�ment qui permet � l'utilisateur de
									// saisir sa recherche
	private JButton ok; // Bouton de validation
	private JButton visAl;
	private JButton visIn;
	private JButton visOut;
	private JTextArea resume;
	private JButton S;
	private JButton W;
	private JButton NW;
	private JButton N;
	private JButton NE;
	private JButton E;
	private JButton quitter;
	
	private JButton precedent;
	private JButton suivant;
	private JLabel labelImage; // Label qui contiendra les images

	private URL url1; // url de la photo
	public Photo[] tabPhotos; // tableau de photo
	private GeoInterface geoInterface; // Interface qui permettra de géré les
										// géoDatas
	private GeoData positionGeographique; // géoData qui permettra de récupérer
											// la longitude et la latitude
	public Integer i = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// /**
	// * @param args
	// */

	Fenetre() { // Constructeur qui fait simplement appel
		super(); // au constructeur de la classe sup�rieure
		// build(); //et a la m�thode build definit ci-dessous
		// }

		// private void build() {
		setTitle("VisioScope"); // Nom
		setSize(1000, 800); // Taille
		setResizable(true); // Redimentionnable ou non, ici non
		setLocationRelativeTo(null); // Centrer la fen�tre sur l'�cran
		// setContentPane(buildContentPanel()); //On lui associe un Panel
		// directement cr�� par la m�thode buildContentPanel definit ci-dessous
		setDefaultCloseOperation(this.EXIT_ON_CLOSE); // La fen�tre se ferme
														// lors du clic sur la
														// croix
		// pack();

		// definition des noms d'objets d'interface
		labelTitre = new JLabel("VisioScope");
		label1 = new JLabel("Veuillez saisir un monument");
		motCherche = new JTextField("");
		motCherche.setColumns(10);
		ok = new JButton("Let's go!");
		ok.addActionListener(new Visiter());
		
		visAl = new JButton("Aléatoire");
		visIn = new JButton("Intérieure");
		visOut = new JButton("Extérieure");
		
		resume = new JTextArea("Bienvenue sur VisioScope, ");
		
		S = new JButton("S");
		W = new JButton("W");
		NW = new JButton("NW");
		N = new JButton("N");
		NE = new JButton("NE");
		E = new JButton("E");
		
		quitter = new JButton("Quitter");
		
		suivant = new JButton("Suivant");
		suivant.addActionListener(new Suivant());
		suivant.setVisible(false);
		precedent = new JButton("Precedent");
		precedent.setVisible(false);
		precedent.addActionListener(new Precedent());
		labelImage = new JLabel("Image");
		labelImage.setSize(new Dimension(400, 300));

		// Definition des objets utilisés pour placer l'interface
		GridBagLayout placeur = new GridBagLayout();
		GridBagConstraints contraintes = new GridBagConstraints();
		getContentPane().setLayout(placeur);

		// placement des objets
		
		JPanel panelGauche = new JPanel();
		GridBagLayout placeurGauche = new GridBagLayout();
		panelGauche.setLayout(placeurGauche);
		

		contraintes.gridx = 0;
		contraintes.gridy = 0;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.WEST;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 100;
		contraintes.weighty = 100;
		placeur.setConstraints(panelGauche, contraintes);
		getContentPane().add(panelGauche);
		
		JPanel panelDroit = new JPanel();
		GridBagLayout placeurDroit = new GridBagLayout();
		panelDroit.setLayout(placeurDroit);
		
		contraintes.gridx = 1;
		contraintes.gridy = 0;
		contraintes.gridwidth = 2;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 100;
		contraintes.weighty = 100;
		placeur.setConstraints(panelDroit, contraintes);
		getContentPane().add(panelDroit);

		contraintes.gridx = 0;
		contraintes.gridy = 1;
		contraintes.gridwidth = 2;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.NORTH;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurGauche.setConstraints(motCherche, contraintes);
		panelGauche.add(motCherche);
		
		contraintes.gridx = 2;
		contraintes.gridy = 1;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurGauche.setConstraints(ok, contraintes);
		panelGauche.add(ok); 
		
		contraintes.gridx = 0;
		contraintes.gridy = 2;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurGauche.setConstraints(visAl, contraintes);
		panelGauche.add(visAl);
		
		
		contraintes.gridx = 1;
		contraintes.gridy = 2;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurGauche.setConstraints(visIn, contraintes);
		panelGauche.add(visIn);
		
		
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurGauche.setConstraints(visOut, contraintes);
		panelGauche.add(visOut);
		
		contraintes.gridx = 0;
		contraintes.gridy = 3;
		contraintes.gridwidth = 3;
		contraintes.gridheight = 2;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.NONE;
		contraintes.anchor = GridBagConstraints.SOUTH;
		contraintes.insets = new Insets(2, 2, 2, 2);
		//contraintes.weightx = 0;
		//contraintes.weighty = 0;
		placeurGauche.setConstraints(resume, contraintes);
		panelGauche.add(resume);
		
//		panelDroit.add(precedent, BorderLayout.WEST);
//		panelDroit.add(suivant, BorderLayout.EAST);
//		panelDroit.add(labelImage, BorderLayout.CENTER);
//		panelDroit.add(labelTitre, BorderLayout.NORTH);
		
		contraintes.gridx = 1;
		contraintes.gridy = 0;
		contraintes.gridwidth = 4;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(labelTitre, contraintes);
		panelDroit.add(labelTitre);
		
		contraintes.gridx = 2;
		contraintes.gridy = 2;
		contraintes.gridwidth = 2;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(N, contraintes);
		panelDroit.add(N);
		
		contraintes.gridx = 0;
		contraintes.gridy = 3;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(NW, contraintes);
		panelDroit.add(NW);
		
		contraintes.gridx = 5;
		contraintes.gridy = 3;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(NE, contraintes);
		panelDroit.add(NE);
		
		contraintes.gridx = 0;
		contraintes.gridy = 4;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(W, contraintes);
		panelDroit.add(W);
		
		contraintes.gridx = 5;
		contraintes.gridy = 4;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(E, contraintes);
		panelDroit.add(E);
		
		contraintes.gridx = 2;
		contraintes.gridy = 6;
		contraintes.gridwidth = 2;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(S, contraintes);
		panelDroit.add(S);
		
		contraintes.gridx = 1;
		contraintes.gridy = 3;
		contraintes.gridwidth = 4;
		contraintes.gridheight = 3;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(labelImage, contraintes);
		panelDroit.add(labelImage);
		
		contraintes.gridx = 5;
		contraintes.gridy = 8;
		contraintes.gridwidth = 1;
		contraintes.gridheight = 1;
		contraintes.ipadx = 2;
		contraintes.ipady = 2;
		contraintes.fill = GridBagConstraints.BOTH;
		contraintes.anchor = GridBagConstraints.CENTER;
		contraintes.insets = new Insets(2, 2, 2, 2);
		contraintes.weightx = 0;
		contraintes.weighty = 0;
		placeurDroit.setConstraints(quitter, contraintes);
		panelDroit.add(quitter);
		
//		contraintes.gridx = 1;
//		contraintes.gridy = 2;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
//		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.insets = new Insets(2, 2, 2, 2);
//		contraintes.weightx = 0;
//		contraintes.weighty = 0;
//		placeurDroit.setConstraints(precedent, contraintes);
//		panelDroit.add(precedent);
//		
//		contraintes.gridx = 4;
//		contraintes.gridy = 2;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
//		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.insets = new Insets(2, 2, 2, 2);
//		contraintes.weightx = 0;
//		contraintes.weighty = 0;
//		placeurDroit.setConstraints(suivant, contraintes);
//		panelDroit.add(suivant);
		
		JPanel panelBas = new JPanel();
		GridBagLayout placeurBas = new GridBagLayout();
		panelBas.setLayout(placeurBas);
		
		

//		contraintes.gridx = 1;
//		contraintes.gridy = 1;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
//		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.insets = new Insets(2, 2, 2, 2);
//		contraintes.weightx = 0;
//		contraintes.weighty = 0;
//		placeur.setConstraints(precedent, contraintes);
//		getContentPane().add(precedent);
//
//		contraintes.gridx = 3;
//		contraintes.gridy = 1;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
//		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.insets = new Insets(2, 2, 2, 2);
//		contraintes.weightx = 0;
//		contraintes.weighty = 0;
//		placeur.setConstraints(labelImage, contraintes);
//		getContentPane().add(labelImage);
//
//		contraintes.gridx = 4;
//		contraintes.gridy = 1;
//		contraintes.gridwidth = 1;
//		contraintes.gridheight = 1;
//		contraintes.ipadx = 2;
//		contraintes.ipady = 2;
//		contraintes.fill = GridBagConstraints.NONE;
//		contraintes.anchor = GridBagConstraints.CENTER;
//		contraintes.insets = new Insets(2, 2, 2, 2);
//		contraintes.weightx = 0;
//		contraintes.weighty = 0;
//		placeur.setConstraints(suivant, contraintes);
//		getContentPane().add(suivant);

		

	}

	// private JPanel buildContentPanel() {
	// Panel = new JPanel(); //Cr�ation du Panel
	// Panel.setLayout(new FlowLayout()); //On choisit le layout, ici FlowLayout
	// qui place les diff�rents �l�ments les uns � la suite des autres et les
	// centre.
	// Panel.setBackground(Color.white); //Fond blanc
	//
	// Label1 = new JLabel(); //Cr�ation et ajout du jlabel1
	// Label1.setText("Veuillez saisir un monument :");
	// Panel.add(Label1);
	//
	// motCherche = new JTextField(); //Cr�ation et ajout de motCherche
	// motCherche.setColumns(10);
	// Panel.add(motCherche);
	//
	// ok = new JButton(new Visiter(this, "OK!")); //Cr�ation et ajout du bouton
	// ok
	// Panel.add(ok);
	//
	// labelImage = new JLabel(); //Cr�ation et ajout du label1
	// Panel.add(labelImage);
	//
	//
	// return Panel;
	//
	// }
	//
	// OBSERVATEURS

	public JLabel getLabel1() {
		return label1;
	}

	public JTextField getTextField() {
		return motCherche;
	}

	public JLabel getLabelPhoto() {
		return labelImage;
	}

	public JButton getButton() {
		return ok;
	}

	public class Suivant implements ActionListener {
		public synchronized void actionPerformed(ActionEvent e) {
			i = i + 1;
			
			String urlString1 = tabPhotos[i].getMediumUrl(); // On récupère
			// l'url de
			// la photo
			// sous
			// forme de
			// string
			System.out.println(urlString1);

			try {
				url1 = new URL(urlString1); // On essaye de transfomer
				// urlString en type URL
			} catch (MalformedURLException e1) { // On lève l'erreur
				System.out.println(e1.getMessage());
			}

			affichageImage(url1);
			try {
				positionGeographique = geoInterface.getLocation(tabPhotos[i].getId());
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
	}

	public class Precedent implements ActionListener {
		public synchronized void actionPerformed(ActionEvent e) {
			i = i - 1;
			
			String urlString1 = tabPhotos[i].getMediumUrl(); // On récupère
			// l'url de
			// la photo
			// sous
			// forme de
			// string
			System.out.println(urlString1);

			try {
				url1 = new URL(urlString1); // On essaye de transfomer
				// urlString en type URL
			} catch (MalformedURLException e1) { // On lève l'erreur
				System.out.println(e1.getMessage());
			}

			affichageImage(url1);
			try {
				positionGeographique = geoInterface.getLocation(tabPhotos[i].getId());
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
	}

	public class Visiter implements ActionListener {
		public synchronized void actionPerformed(ActionEvent e) {
			String tagDemande = motCherche.getText(); // on
			// récupère
			// le
			// texte
			// tapé
			// par
			// l'utilisateur
			try {
				tabPhotos = recupererPhoto(tagDemande);
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

			String urlString1 = tabPhotos[i].getMediumUrl(); // On récupère
			// l'url de
			// la photo
			// sous
			// forme de
			// string
			System.out.println(urlString1);

			try {
				url1 = new URL(urlString1); // On essaye de transfomer
				// urlString en type URL
			} catch (MalformedURLException e1) { // On lève l'erreur
				System.out.println(e1.getMessage());
			}

			affichageImage(url1);
			
			suivant.setVisible(true);
			precedent.setVisible(true);

		}

//		public void affichageImage(URL url) {
//			ImageIcon img = new ImageIcon(url);
//			labelImage.setIcon(img);
//			// fenetre.getContentPane().add(fenetre.getLabelPhoto());
//		}

//		public Photo[] recupererPhoto(String tagUtilisateur)
//				throws IOException, SAXException, FlickrException {
//			Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122"); // création
//			// d'un
//			// élément
//			// de
//			// type
//			// Flickr
//			PhotosInterface photosInterface = apiAccess.getPhotosInterface(); // création
//			// d'un
//			// élément
//			// de
//			// type
//			// PhotoInterface
//			geoInterface = apiAccess.getGeoInterface(); // creation d'un élément
//														// de
//			// type GeoInterface
//			PhotoList listePhoto1 = new PhotoList();
//			Photo[] tab = new Photo[100];
//
//			SearchParameters param = new SearchParameters(); // Création d'un
//			// paramètre de
//			// recherche
//			String[] tabTags = { tagUtilisateur }; // Definition du tag en
//													// fonction
//			param.setHasGeo(true); // du texte tapé
//			param.setTags(tabTags); // On definit le paramètre avec ce tag
//			param.setBBox("2", "48", "3", "49");
//			//param.setMaxUploadDate(new Date(new Long(0)));
//			// param.setMinUploadDate(new Date(System.currentTimeMillis()));
//			// System.out.println(param.getHasGeo());
//
//			try {
//				listePhoto1 = photosInterface.search(param, 100, 0); // Récupération
//				// d'une
//				// liste
//				// de photos
//			} catch (FlickrException e3) { // on lève toutes les exceptions
//				// possibles
//				System.out.println(e3.getErrorMessage());
//			} catch (IOException e1) {
//				System.out.println(e1);
//			} catch (SAXException e2) {
//				System.out.println(e2.getMessage());
//			}
//
//			// ON MET LES PHOTOS DANS UN TABLEAU
//
//			for (int i = 0; i <= 99; i++) {
//				// if (new Float(48.0) <
//				// geoInterface.getLocation(tabPhotos[i].getId()).getLatitude())
//				// (geoInterface.getLocation(tabPhotos[i].getId()).getLatitude()
//				// >
//				// new Float(49)) &&
//				// (new Float(2) <
//				// geoInterface.getLocation(tabPhotos[i].getId()).getLongitude())
//				// &&
//				// (geoInterface.getLocation(tabPhotos[i].getId()).getLongitude()
//				// >
//				// new Float(3)))
//				// {
//				tab[i] = (Photo) listePhoto1.get(i);
//				// j++;
//				// }
//			}
//			return tab;
//		}
	}
	
	public void affichageImage(URL url) {
		ImageIcon img = new ImageIcon(url);
		labelImage.setIcon(img);
		// fenetre.getContentPane().add(fenetre.getLabelPhoto());
	}
	
	public Photo[] recupererPhoto(String tagUtilisateur)
			throws IOException, SAXException, FlickrException {
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122"); // création
		// d'un
		// élément
		// de
		// type
		// Flickr
		PhotosInterface photosInterface = apiAccess.getPhotosInterface(); // création
		// d'un
		// élément
		// de
		// type
		// PhotoInterface
		geoInterface = apiAccess.getGeoInterface(); // creation d'un élément
													// de
		// type GeoInterface
		PhotoList listePhoto1 = new PhotoList();
		Photo[] tab = new Photo[100];

		SearchParameters param = new SearchParameters(); // Création d'un
		// paramètre de
		// recherche
		String[] tabTags = { tagUtilisateur }; // Definition du tag en
												// fonction
		param.setHasGeo(true); // du texte tapé
		param.setTags(tabTags); // On definit le paramètre avec ce tag
		param.setBBox("2", "48", "3", "49");
		//param.setMaxUploadDate(new Date(new Long(0)));
		// param.setMinUploadDate(new Date(System.currentTimeMillis()));
		// System.out.println(param.getHasGeo());

		try {
			listePhoto1 = photosInterface.search(param, 100, 0); // Récupération
			// d'une
			// liste
			// de photos
		} catch (FlickrException e3) { // on lève toutes les exceptions
			// possibles
			System.out.println(e3.getErrorMessage());
		} catch (IOException e1) {
			System.out.println(e1);
		} catch (SAXException e2) {
			System.out.println(e2.getMessage());
		}

		// ON MET LES PHOTOS DANS UN TABLEAU

		for (int i = 0; i <= 99; i++) {
			// if (new Float(48.0) <
			// geoInterface.getLocation(tabPhotos[i].getId()).getLatitude())
			// (geoInterface.getLocation(tabPhotos[i].getId()).getLatitude()
			// >
			// new Float(49)) &&
			// (new Float(2) <
			// geoInterface.getLocation(tabPhotos[i].getId()).getLongitude())
			// &&
			// (geoInterface.getLocation(tabPhotos[i].getId()).getLongitude()
			// >
			// new Float(3)))
			// {
			tab[i] = (Photo) listePhoto1.get(i);
			// j++;
			// }
		}
		return tab;
	}

}