import java.io.IOException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.FlickrException;

class ThreadRecup extends Thread {

	private FenetrePropre fenetre;
	private int nbPhotosOrdonne;
	private int nbPhotosBilk;

	public ThreadRecup(int nbPOrdonne, int nbPBilk, FenetrePropre f) {
		fenetre = f;
		nbPhotosOrdonne = nbPOrdonne;
		nbPhotosBilk = nbPBilk;
	}

	public void run() {
		// on récupère le texte tapé par l'utilisateur
		// String tagDemande = txtTapezVotreRecherche.getText();
		//fenetre.progressBar.setMaximum(nbPhotosOrdonne + nbPhotosBilk);
		try {
			fenetre.visit1.recupererPhotosVrac(nbPhotosBilk, fenetre);
			fenetre.visit1.retrievePictureOrdonnees(nbPhotosOrdonne, 78.040674, 27.171792, 78.043635, 27.174541, fenetre);
			//fenetre.visit1.retrievePictureOrdonnees(nbPhotosOrdonne, 27.171792, 78.040674, 27.174541, 78.043635, fenetre);

			// fenetre.recupererPhotosOrdonnees(16, 2.294, 48.853,2.297,48.858);
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
	}

}