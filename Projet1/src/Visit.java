import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import org.xml.sax.SAXException;
import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.SearchParameters;
import com.aetrion.flickr.photos.geo.GeoInterface;

public class Visit {
	private Integer idVisit;
	private Integer visitState; // 0 pour bilk , 1 pour OutDoor, 2 pour InDoor
	private String nameV;
	private Date dateV;
	private static GeoInterface geoInterface;
	private static PhotosInterface photosInterface;
	private static PhotoList listePhoto1;
	private int compteur = 0;

	PairOfPictures[] tabBilk;
	PairOfPictures[][] tabInDoor;
	PairOfPictures[][] tabOutDoor;
	int colonne = 0;
	int ligne = 0;
	int i = 0;

	public Visit(String nom) {
		nameV = nom;
	}

	// ----------------------------------------------GETTERS--------------------------------------------------------
	public String getNameV() {
		return nameV;
	}

	public Date getDateV() {
		return dateV;
	}

	public Integer getVisitState() {
		return visitState;
	}

	public void setVisitState(Integer state) {
		this.visitState = state;
	}

	public void move(String direction, FenetrePropre f) {
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

		case "SE":
			ligne++;
			colonne++;
			break;

		case "SO":
			ligne++;
			colonne--;
			break;

		case "suiv":
			i++;
			break;

		case "prec":
			i--;
			break;

		case "2prec":
			i = i - 2;
			break;

		case "2suiv":
			i = i + 2;
			break;

		case "init":
			if (visitState == 0) {
				i = 0;
			} else if (visitState == 1) {
				colonne = 0;
				ligne = 0;
			} else if (visitState == 2) {
				colonne = 0;
				ligne = 0;
			}
		}

	}

	public void recupererPhotosBilk(int nbPhotos, FenetrePropre f) throws IOException, SAXException, FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		tabBilk = new PairOfPictures[nbPhotos];
		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String[] tabTags = { nameV };
		param.setHasGeo(true);
		param.setTags(tabTags); // On definit le paramètre avec ce tag

		// param.setBBox("2.292", "48.856", "2.293", "48.857");

		try {
			// Récupération d'uneliste de photos
			listePhoto1 = photosInterface.search(param, (2 * nbPhotos), 0);
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

		if (f.site.getHauteurMonument() != 0) {
			f.progressBar.setMaximum((Integer) f.choixNbIn.getSelectedItem() + (Integer) f.choixNbOut.getSelectedItem() + (listePhoto1.size() / 2));
		} else {
			f.progressBar.setMaximum((Integer) f.choixNbOut.getSelectedItem() + (listePhoto1.size() / 2));

		}
		for (int compt1 = 0, step = 0; compt1 <= tabBilk.length - 1; compt1++) {
			compteur++;
			f.progressBar.setValue(compteur);
			Photo pic = (Photo) listePhoto1.get(step);
			step++;
			Photo pic1 = (Photo) listePhoto1.get(step);
			step++;
			Picture pict = new Picture(pic, geoInterface, nameV);
			Picture pict1 = new Picture(pic1, geoInterface, nameV);
			PairOfPictures pair = new PairOfPictures(pict, pict1);
			tabBilk[compt1] = pair;
			System.out.println("/////////////////////////////PHOTO BILK//////////////////////////");
			System.out.println("Url de la photo : " + pict.getMediumUrl());
			System.out.println("tag de la photo : " + pict.getTag());
			System.out.println("Latitude : " + pict.getLatitudeP());
			System.out.println("Longitude : " + pict.getLongitudeP());
		}
		f.btnBilk.setVisible(true);
	}

	public void retrievePictureOutDoor(int nbPhotos, double longMin, double latMin, double longMax, double latMax, FenetrePropre f) throws IOException,
			SAXException, FlickrException {
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		listePhoto1 = new PhotoList();
		tabOutDoor = new PairOfPictures[(int) Math.sqrt(nbPhotos)][(int) Math.sqrt(nbPhotos)];
		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		// String tagDemande = f.getJText().getText();
		String[] tabTags = { nameV };
		param.setHasGeo(true);
		param.setTags(tabTags);
		// On definit le paramètre avec ce tag
		double xStep = ((longMax - longMin) / (int) Math.sqrt(nbPhotos));
		double yStep = ((latMax - latMin) / (int) Math.sqrt(nbPhotos));
		Double xMin = longMin;
		Double yMin = latMax - yStep;
		Double xMax = longMin + xStep;
		Double yMax = latMax;
		int l = 0;
		int c = 0;

		for (int compt2 = 0; compt2 <= nbPhotos - 1; compt2++) {
			compteur++;
			f.progressBar.setValue(compteur);
			if (c < (Math.sqrt(nbPhotos) - 1)) {

				param.setBBox(xMin.toString(), yMin.toString(), xMax.toString(), yMax.toString());
				try {
					// Récupération d'uneliste de photos
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);
					PairOfPictures pair = new PairOfPictures();
					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						Picture pict = new Picture(pic, geoInterface, nameV);
						pair.setPic1(pict);
						System.out.println("/////////////////////////////PHOTO OUTDOOR 1//////////////////////////");
						System.out.println("Url de la photo : " + pict.getMediumUrl());
						System.out.println("tag de la photo : " + pict.getTag());
						System.out.println("Latitude : " + pict.getLatitudeP());
						System.out.println("Longitude : " + pict.getLongitudeP());
					} else {
						System.out.println("pas de Photo1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					listePhoto1 = photosInterface.search(param, 10, 5);
					if (!listePhoto1.isEmpty()) {
						Photo pic1 = (Photo) listePhoto1.get(0);
						Picture pict1 = new Picture(pic1, geoInterface, nameV);
						pair.setPic2(pict1);
						System.out.println("/////////////////////////////PHOTO OUTDOOR 2//////////////////////////");
						System.out.println("Url de la photo : " + pict1.getMediumUrl());
						System.out.println("tag de la photo : " + pict1.getTag());
						System.out.println("Latitude : " + pict1.getLatitudeP());
						System.out.println("Longitude : " + pict1.getLongitudeP());
					} else {
						System.out.println("pas de Photo2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					this.tabOutDoor[l][c] = pair;
				} catch (FlickrException e3) {
					// System.out.println(e3.getErrorMessage());
					System.out.println("yo c'est l'erreur 1");
				} catch (IOException e1) {
					// System.out.println(e1);
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				} catch (SAXException e2) {
					// System.out.println(e2.getMessage());
					System.out.println("yo c'est l'erreur 3");
				}
				c++;
				xMin = xMax;
				xMax = xMax + xStep;
			} else if (c == (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(String.valueOf(xMin), String.valueOf(yMin), String.valueOf(xMax), String.valueOf(yMax));
				try {
					// Récupération d'uneliste de photos
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);
					PairOfPictures pair = new PairOfPictures();
					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						Picture pict = new Picture(pic, geoInterface, nameV);
						pair.setPic1(pict);
						System.out.println("/////////////////////////////PHOTO OUTDOOR 1//////////////////////////");
						System.out.println("Url de la photo : " + pict.getMediumUrl());
						System.out.println("tag de la photo : " + pict.getTag());
						System.out.println("Latitude : " + pict.getLatitudeP());
						System.out.println("Longitude : " + pict.getLongitudeP());
					} else {
						System.out.println("pas de Photo1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					listePhoto1 = photosInterface.search(param, 10, 5);
					if (!listePhoto1.isEmpty()) {
						Photo pic1 = (Photo) listePhoto1.get(0);
						Picture pict1 = new Picture(pic1, geoInterface, nameV);
						pair.setPic2(pict1);
						System.out.println("/////////////////////////////PHOTO OUTDOOR 2//////////////////////////");
						System.out.println("Url de la photo : " + pict1.getMediumUrl());
						System.out.println("tag de la photo : " + pict1.getTag());
						System.out.println("Latitude : " + pict1.getLatitudeP());
						System.out.println("Longitude : " + pict1.getLongitudeP());
					} else {
						System.out.println("pas de Photo2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					// this.tabOrdonne[l][c] = pair;
					// System.out.println("Url de la photo : " +
					// pict1.getMediumUrl());
					// System.out.println("tag de la photo : " +
					// pict1.getTag());
					// System.out.println("Latitude : " + pict1.getLatitudeP());
					// System.out.println("Longitude : " +
					// pict1.getLongitudeP());
					this.tabOutDoor[l][c] = pair;
				} catch (FlickrException e3) {
					System.out.println(e3.getErrorMessage());
				} catch (IOException e1) {
					System.out.println(e1);
				} catch (SAXException e2) {
					System.out.println(e2.getMessage());
				}
				c = 0;
				l++;
				xMin = longMin;
				xMax = longMin + xStep;
				yMax = yMin;
				yMin = yMin - yStep;
			}
		}
		if (f.site.getHauteurMonument() == 0) {
			f.progressBar.setVisible(false);
			compteur = 0;
		}
		f.btnOut.setVisible(true);
	}

	public void retrievePictureIndoor(int nbPhotos, double longMin, double latMin, double longMax, double latMax, FenetrePropre f) throws IOException,
			SAXException, FlickrException {
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		listePhoto1 = new PhotoList();
		this.tabInDoor = new PairOfPictures[(int) Math.sqrt(nbPhotos)][(int) Math.sqrt(nbPhotos)];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		// String tagDemande = f.getJText().getText();
		String[] tabTags = { nameV };
		param.setHasGeo(true);
		param.setTags(tabTags);
		// On definit le paramètre avec ce tag
		double xStep = ((longMax - longMin) / (int) Math.sqrt(nbPhotos));
		double yStep = ((latMax - latMin) / (int) Math.sqrt(nbPhotos));
		Double xMin = longMin;
		Double yMin = latMax - yStep;
		Double xMax = longMin + xStep;
		Double yMax = latMax;
		int l = 0;
		int c = 0;
		for (int compt2 = 0; compt2 <= nbPhotos - 1; compt2++) {
			compteur++;
			f.progressBar.setValue(compteur);
			if (c < (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(xMin.toString(), yMin.toString(), xMax.toString(), yMax.toString());
				try {
					// Récupération d'uneliste de photos
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);
					PairOfPictures pair = new PairOfPictures();
					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						Picture pict = new Picture(pic, geoInterface, nameV);
						pair.setPic1(pict);
						System.out.println("/////////////////////////////PHOTO INDOOR 1//////////////////////////");
						System.out.println("Url de la photo : " + pict.getMediumUrl());
						System.out.println("tag de la photo : " + pict.getTag());
						System.out.println("Latitude : " + pict.getLatitudeP());
						System.out.println("Longitude : " + pict.getLongitudeP());
					} else {
						System.out.println("pas de Photo1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					listePhoto1 = photosInterface.search(param, 10, 5);
					if (!listePhoto1.isEmpty()) {
						Photo pic1 = (Photo) listePhoto1.get(0);
						Picture pict1 = new Picture(pic1, geoInterface, nameV);
						pair.setPic2(pict1);
						System.out.println("/////////////////////////////PHOTO INDOOR 2//////////////////////////");
						System.out.println("Url de la photo : " + pict1.getMediumUrl());
						System.out.println("tag de la photo : " + pict1.getTag());
						System.out.println("Latitude : " + pict1.getLatitudeP());
						System.out.println("Longitude : " + pict1.getLongitudeP());
					} else {
						System.out.println("pas de Photo2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					// this.tabOrdonne[l][c] = pair;
					// System.out.println("Url de la photo : " +
					// pict1.getMediumUrl());
					// System.out.println("tag de la photo : " +
					// pict1.getTag());
					// System.out.println("Latitude : " + pict1.getLatitudeP());
					// System.out.println("Longitude : " +
					// pict1.getLongitudeP());
					this.tabInDoor[l][c] = pair;
				} catch (FlickrException e3) {
					// System.out.println(e3.getErrorMessage());
					System.out.println("yo c'est l'erreur 1");
				} catch (IOException e1) {
					// System.out.println(e1);
					System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				} catch (SAXException e2) {
					// System.out.println(e2.getMessage());
					System.out.println("yo c'est l'erreur 3");
				}

				c++;
				xMin = xMax;
				xMax = xMax + xStep;
			} else if (c == (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(String.valueOf(xMin), String.valueOf(yMin), String.valueOf(xMax), String.valueOf(yMax));
				try {
					// Récupération d'uneliste de photos
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);
					PairOfPictures pair = new PairOfPictures();
					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						Picture pict = new Picture(pic, geoInterface, nameV);
						pair.setPic1(pict);
						System.out.println("/////////////////////////////PHOTO INDOOR 1//////////////////////////");
						System.out.println("Url de la photo : " + pict.getMediumUrl());
						System.out.println("tag de la photo : " + pict.getTag());
						System.out.println("Latitude : " + pict.getLatitudeP());
						System.out.println("Longitude : " + pict.getLongitudeP());
					} else {
						System.out.println("pas de Photo1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					listePhoto1 = photosInterface.search(param, 10, 5);
					if (!listePhoto1.isEmpty()) {
						Photo pic1 = (Photo) listePhoto1.get(0);
						Picture pict1 = new Picture(pic1, geoInterface, nameV);
						pair.setPic2(pict1);
						System.out.println("/////////////////////////////PHOTO INDOOR 2//////////////////////////");
						System.out.println("Url de la photo : " + pict1.getMediumUrl());
						System.out.println("tag de la photo : " + pict1.getTag());
						System.out.println("Latitude : " + pict1.getLatitudeP());
						System.out.println("Longitude : " + pict1.getLongitudeP());
					} else {
						System.out.println("pas de Photo2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					}
					this.tabInDoor[l][c] = pair;
				} catch (FlickrException e3) {
					System.out.println(e3.getErrorMessage());
				} catch (IOException e1) {
					System.out.println(e1);
				} catch (SAXException e2) {
					System.out.println(e2.getMessage());
				}
				c = 0;
				l++;
				xMin = longMin;
				xMax = longMin + xStep;
				yMax = yMin;
				yMin = yMin - yStep;
			}
		}
		f.btnIn.setVisible(true);
		f.progressBar.setVisible(false);
		compteur = 0;
	}

}
