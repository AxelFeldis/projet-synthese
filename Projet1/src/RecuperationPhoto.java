import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;


public class RecuperationPhoto extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fenetre fenetre;
	private URL url1;
	
	public RecuperationPhoto(Fenetre fenetre1, String texte){					//Constructeur qui prend en paramètre la fenêtre à associer à l'action et le nom du thread
		super(texte);															//constructeur de la classe supérieure
		this.fenetre = fenetre1;												
	}
	
	public void actionPerformed(ActionEvent e) { 								//action effectuée quand on appuie sur le boutton OK!
		Thread t = new Thread() {												//Création d'un nouveau thread
			public void run() {
				fenetre.getLabel1().setVisible(false);							//On rend invisible les différents éléments de l'interface
				fenetre.getTextField().setVisible(false);
				fenetre.getButton().setVisible(false);
				
				String tagDemande = fenetre.getTextField().getText();				//on récupère le texte tapé par l'utilisateur
				
				Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");	//création d'un élément de type Flickr
				PhotosInterface photosInterface = apiAccess.getPhotosInterface();	//création d'un élément de type PhotoInterface
				PhotoList listePhoto1 = new PhotoList();	
		
				SearchParameters param = new SearchParameters();					//Création d'un paramètre de recherche
				String[] tabTags = {tagDemande};									//Definition du tag en fonction du texte tapé
				param.setTags(tabTags);												//On definit le paramètre avec ce tag
		
					try {
						listePhoto1 = photosInterface.search(param, 50, 0);			//Récupération d'une liste de photos
					} 
					catch (FlickrException e3) {									//on lève toutes les exceptions possibles
						System.out.println(e3.getErrorMessage());
					}
					catch (IOException e1) {
						System.out.println(e1);
					}
					catch (SAXException e2) {
						System.out.println(e2.getMessage());
					}
					
					//AFFICHAGE DES PHOTOS
					for (int i = 0; i <= 9; i++) {	
					
						Photo photo = (Photo)listePhoto1.get(i);						
						String urlString1 = photo.getLargeUrl();							//On récupère l'url de la photo sous forme de string
						System.out.println(urlString1);
						
						try {
							url1 = new URL(urlString1);										//On essaye de transfomer urlString en type URL
						}																	
						catch ( MalformedURLException e1) {									//On lève l'erreur
							System.out.println(e1.getMessage());
						}
						affichageImage(url1);
						try {																//on arrete 0.5 secondes
							Thread.sleep(500);
						}
						catch ( InterruptedException e1) {}
						System.out.print(i);
					}
			}	
		};
		t.start();
			
	} 
	public void affichageImage(URL url) {
			ImageIcon img = new ImageIcon(url);
			fenetre.getLabelPhoto().setIcon(img);
			fenetre.getPanel().add(fenetre.getLabelPhoto());
	}

}
