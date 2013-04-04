import java.io.IOException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.FlickrException;

class ThreadRecup extends Thread {

	private FenetreClean fenetre;
	private int nbPhotosOut;
	private int nbPhotosBilk;
	private int nbPhotosIn;

	public ThreadRecup(int nbPOut, int nbPBilk, int nbPIn, FenetreClean f) {
		fenetre = f;
		nbPhotosIn = nbPIn;
		nbPhotosOut = nbPOut;
		nbPhotosBilk = nbPBilk;
	}

	public void run() {
		try {
			fenetre.visit1.recupererPhotosBilk(nbPhotosBilk, fenetre);

			fenetre.visit1.retrievePictureOutDoor(nbPhotosOut, (fenetre.site.getLongitudeZone() - (fenetre.site.getLargeurZone() / 2)),
					(fenetre.site.getLatitudeZone() - (fenetre.site.getHauteurZone() / 2)),
					(fenetre.site.getLongitudeZone() + (fenetre.site.getLargeurZone() / 2)),
					(fenetre.site.getLatitudeZone() + (fenetre.site.getHauteurZone() / 2)), fenetre);
			if (fenetre.site.getHauteurMonument() != 0) {
				fenetre.visit1.retrievePictureIndoor(nbPhotosIn, (fenetre.site.getLongitudeMonument() - (fenetre.site.getLargeurMonument() / 2)),
						(fenetre.site.getLatitudeMonument() - (fenetre.site.getHauteurMonument() / 2)),
						(fenetre.site.getLongitudeMonument() + (fenetre.site.getLargeurMonument() / 2)),
						(fenetre.site.getLatitudeMonument() + (fenetre.site.getHauteurMonument() / 2)), fenetre);
			}
			// System.out.println(fenetre.site.getLongitudeZone());

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