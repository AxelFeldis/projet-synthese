import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.photos.geo.GeoInterface;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class RecuperationPhoto extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	private URL url1;
	private URI uri1;
	private Photo[] tabPhotos;
	private GeoInterface geoInterface;

	public RecuperationPhoto(Fenetre fenetre1, String texte) { // Constructeur
																// qui prend en
																// paramètre la
																// fenêtre à
																// associer à
																// l'action et
																// le nom du
																// thread
		super(texte); // constructeur de la classe supérieure
		this.fenetre = fenetre1;
	}

	public void actionPerformed(ActionEvent e) { 								//action effectuée quand on appuie sur le boutton OK!
		Thread t = new Thread() {												//Création d'un nouveau thread
			public void run() {
				//fenetre.getLabel1().setVisible(false);							//On rend invisible les différents éléments de l'interface
				//fenetre.getTextField().setVisible(false);
				//fenetre.getButton().setVisible(false);
				
				String tagDemande = fenetre.getTextField().getText();				//on récupère le texte tapé par l'utilisateur
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
				for (int i = 0; i<= 49; i++) {
					try {
						GeoData positionGeographique = geoInterface.getLocation(tabPhotos[i].getId());
						System.out.println(positionGeographique.getLatitude());
						System.out.println(positionGeographique.getLongitude());
						
					} catch (IOException | SAXException | FlickrException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
					System.out.println(tabPhotos[i].hasGeoData());
					if (tabPhotos[i].hasGeoData()) {
						GeoData positionGeographique = tabPhotos[i].getGeoData();
						System.out.println(positionGeographique.getLatitude());
					}
					System.out.println(i);
					String urlString1 = tabPhotos[i].getLargeUrl();							//On récupère l'url de la photo sous forme de string
					System.out.println(urlString1);
					
					try {
						url1 = new URL(urlString1);										//On essaye de transfomer urlString en type URL
					}																	
					catch ( MalformedURLException e1) {									//On lève l'erreur
						System.out.println(e1.getMessage());
					}
					affichageImage(url1);
					try {																//on arrete 0.5 secondes
						Thread.sleep(100);
					}
					catch ( InterruptedException e1) {}
					//if (positionGeographique != null) {
						//System.out.println(positionGeographique.getLatitude());
					//}
				}
					//AFFICHAGE DES PHOTOS
					/*for (int i = 0; i <= 9; i++) {	
					
						//Photo photo = (Photo)listePhoto1.get(i);						
						String urlString1 = tabPhotos[i].getLargeUrl();							//On récupère l'url de la photo sous forme de string
						System.out.println(urlString1);
						
						try {
							url1 = new URL(urlString1);										//On essaye de transfomer urlString en type URL
						}																	
						catch ( MalformedURLException e1) {									//On lève l'erreur
							System.out.println(e1.getMessage());
						}
						try {
							uri1 = new URI(urlString1);
						} catch (URISyntaxException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						affichageImage(url1);
						
						//GESTION DE EXIF
						File jpegFile = new File(uri1);
						try {
							Metadata metadata;
							try {
								metadata = ImageMetadataReader.readMetadata(jpegFile);
								for (Directory directory : metadata.getDirectories()) {
								    for (Tag tag : directory.getTags()) {
								        System.out.println(tag);
								    }
								}
							} catch (ImageProcessingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							/*for (Directory directory : metadata.getDirectories()) {
							    for (Tag tag : directory.getTags()) {
							        System.out.println(tag);
							    }
							}
						} catch (ImageProcessingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {																//on arrete 0.5 secondes
							Thread.sleep(500);
						}
						catch ( InterruptedException e1) {}
						System.out.print(i);
					}
					*/
			}	
		};
		t.start();
			
	}	public void affichageImage(URL url) {
		ImageIcon img = new ImageIcon(url);
		fenetre.getLabelPhoto().setIcon(img);
		fenetre.getPanel().add(fenetre.getLabelPhoto());
	}

	public Photo[] recupererPhoto(String tagUtilisateur) throws IOException, SAXException, FlickrException {
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
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		Photo[] tab = new Photo[50];

		SearchParameters param = new SearchParameters(); // Création d'un
															// paramètre de
															// recherche
		String[] tabTags = { tagUtilisateur }; // Definition du tag en fonction
		param.setHasGeo(true);										// du texte tapé
		param.setTags(tabTags); // On definit le paramètre avec ce tag
		//param.setMaxUploadDate(new Date(new Long(0))); 
		//param.setMinUploadDate(new Date(System.currentTimeMillis()));
		//System.out.println(param.getHasGeo());
		
 
		try {
			listePhoto1 = photosInterface.search(param, 50, 0); // Récupération
																// d'une liste
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

		for (int i = 0; i <= 49; i++) {
			//if  (new Float(48.0) < geoInterface.getLocation(tabPhotos[i].getId()).getLatitude())
				//(geoInterface.getLocation(tabPhotos[i].getId()).getLatitude() > new Float(49)) &&
				//(new Float(2) < geoInterface.getLocation(tabPhotos[i].getId()).getLongitude()) &&
				//(geoInterface.getLocation(tabPhotos[i].getId()).getLongitude() > new Float(3))) 
					//{	
					tab[i] = (Photo) listePhoto1.get(i);
					//j++;
			//}
		}
		return tab;
	}

}
