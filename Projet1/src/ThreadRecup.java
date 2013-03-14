import java.io.IOException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.FlickrException;

class ThreadRecup extends Thread {

		private FenetrePropre fenetre;
		private int nbPhotos;

		public ThreadRecup(int nbP, FenetrePropre f) {
			fenetre = f;
			nbPhotos = nbP;
		}

		public void run() {
			// on récupère le texte tapé par l'utilisateur
			// String tagDemande = txtTapezVotreRecherche.getText();
			try {
				if (fenetre.visit1.getVisitState() == 2) {
					fenetre.visit1.retrievePictureOrdonnees(nbPhotos, 78.040674, 27.171792,
							78.043635, 27.174541, fenetre);
					// fenetre.recupererPhotosOrdonnees(16, 2.294, 48.853,
					// 2.297,
					// 48.858);
				}
				else if (fenetre.visit1.getVisitState() == 1) {
				 fenetre.visit1.recupererPhotosVrac(nbPhotos, fenetre);
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
			// fenetre.compteur = 0;
		}

	}