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
	
	public RecuperationPhoto(Fenetre fenetre1, String texte){					//Constructeur qui prend en param�tre la fen�tre � associer � l'action et le nom du thread
		super(texte);															//constructeur de la classe sup�rieure
		this.fenetre = fenetre1;												
	}
	
	public void actionPerformed(ActionEvent e) { 								//action effectu�e quand on appuie sur le boutton OK!
		Thread t = new Thread() {												//Cr�ation d'un nouveau thread
			public void run() {
				fenetre.getLabel1().setVisible(false);							//On rend invisible les diff�rents �l�ments de l'interface
				fenetre.getTextField().setVisible(false);
				fenetre.getButton().setVisible(false);
				
				String tagDemande = fenetre.getTextField().getText();				//on r�cup�re le texte tap� par l'utilisateur
				
				Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");	//cr�ation d'un �l�ment de type Flickr
				PhotosInterface photosInterface = apiAccess.getPhotosInterface();	//cr�ation d'un �l�ment de type PhotoInterface
				PhotoList listePhoto1 = new PhotoList();	
		
				SearchParameters param = new SearchParameters();					//Cr�ation d'un param�tre de recherche
				String[] tabTags = {tagDemande};									//Definition du tag en fonction du texte tap�
				param.setTags(tabTags);												//On definit le param�tre avec ce tag
		
					try {
						listePhoto1 = photosInterface.search(param, 50, 0);			//R�cup�ration d'une liste de photos
					} 
					catch (FlickrException e3) {									//on l�ve toutes les exceptions possibles
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
						String urlString1 = photo.getLargeUrl();							//On r�cup�re l'url de la photo sous forme de string
						System.out.println(urlString1);
						
						try {
							url1 = new URL(urlString1);										//On essaye de transfomer urlString en type URL
						}																	
						catch ( MalformedURLException e1) {									//On l�ve l'erreur
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
