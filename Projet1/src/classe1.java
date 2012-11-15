import	com.aetrion.flickr.*;
import	com.aetrion.flickr.photos.*;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
//import java.util.*;
//import com.aetrion.flickr.tags.*;
//import org.jinstagram.*;
//import org.jinstagram.auth.model.Verifier;
//import org.jinstagram.auth.oauth.*;
//import org.jinstagram.auth.*;
//import org.scribe.*;
//import org.scribe.model.Token;

public class classe1 {
	//private static final Token token = null;

	/**
	 * @param args
	 */
	//private static final Token EMPTY_TOKEN = null;
	public static void main(String[] args) {
		//Token token = new Token(null, null);
		
		Fenetre fenetre1 = new Fenetre("ApplicationJava", 1000, 1000);		//Creation d'une fenetre
		
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");	//creation d'un élément de type Flickr
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();	//creation d'un élément de type PhotoInterface
		PhotoList listePhoto1 = new PhotoList();	
		//fenetre1.setVisible(true);											//Creation d'un élement de type Photolist
		
		SearchParameters param = new SearchParameters();
		String[] tabTags = {"Muraille de Chine"};									//Definition des tags
		param.setTags(tabTags);
		
		
		
		//Récupération d'une liste de photos
		try {
			listePhoto1 = photosInterface.search(param, 10, -1000);
		} 
		catch (FlickrException e) {										//on leve toute les exception possible
			System.out.println(e.getErrorMessage());
		}
		catch (IOException e) {
			System.out.println(2);
		}
		catch (SAXException e) {
			System.out.println(e.getMessage());
		}
		
		fenetre1.setVisible(true);		
		
		//Affichage des photos
		
		for (int i = 0; i <= 9; i++) {											//on affiche les photos
			
			Photo photo = (Photo)listePhoto1.get(i);						
			String urlString1 = photo.getLargeUrl();							//On recupere l'url de la photo sous forme de string
			System.out.println(urlString1);
			//Collection<> collection = photo.getTags();
			//String[] tabTagsPhoto = collection.toArray();
			//String tag1 = tabTagsPhoto[0].toString();
			//System.out.println(tag1);
			
			try {
				URL url1 = new URL(urlString1);									//On essaye de transfomer urlString en type URL
				fenetre1.affichageImage(url1);									//On affiche l'image !
			}																	
			catch ( MalformedURLException e) {									//On leve l'erreur
				System.out.println(e.getMessage());
			}
			try {																//on arrete 1secondes
				Thread.sleep(100);
			}
			catch ( InterruptedException e) {}
			System.out.print(i);
			
		}
		
		//Tentative avec instagram
		//InstagramService service = new InstagramAuthService()
										//.apiKey("0c5416aac7cc4d3a98a8b7cce2baf7c5")
										//.apiSecret("a3c80dff43d741ed8f47dcd96751abf8")
										//.callback("http://google.com")
										//.build();
		//String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
		//Verifier verifier = new Verifier("verifier you get from the user");
		//Token accessToken = service.getAccessToken(token, verifier);
	    
	    fenetre1.dispose();														//On ferme la fenetre
	    
	    //System.out.print(photo1.getId().concat("\n"));						//On affiche l'id de le photo
	    //System.out.print(urlString1.concat("\n"));							//on l'affiche l'url
	             
	}

}
