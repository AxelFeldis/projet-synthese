import java.io.IOException;
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
	Integer idVisit;
	Integer visitState;
	String nameV;
	Date dateV;
	Picture[] tabBilk;
	// Picture[][] tabInDoor;
	// Picture[][] tabOutDoor;
	Picture[][] tabOrdonne;
	int colonne = 0;
	int ligne = 0;
	int i = 0;
	Picture photoPasDePhoto = new Picture(
			"http://ec.comps.canstockphoto.com/can-stock-photo_csp2974897.jpg");
	public GeoData positionGeographique;
	public static GeoInterface geoInterface;
	static PhotosInterface photosInterface;
	static PhotoList listePhoto1;

	public Visit(String nom) {
		nameV = nom;
	}

	public String getNameV() {
		return nameV;
	}

	public Date getDateV() {
		return dateV;
	}

	public Integer getVisitState() {
		return visitState;
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
			if (visitState == 2) {
				colonne = 0;
				ligne = 0;
			} else if (visitState == 1) {
				i = 0;
			}
		}

		// System.out.println(ligne);
		// System.out.println(colonne);
		if (visitState == 2) {
			if ((colonne == 0) && (ligne == tabOrdonne.length - 1)) {
				f.btnSo.setVisible(false);
				f.btnO.setVisible(false);
				f.btnNo.setVisible(false);
				f.btnN.setVisible(true);
				f.btnNe.setVisible(true);
				f.btnE.setVisible(true);
				f.btnSe.setVisible(false);
				f.btnS.setVisible(false);
			} else if ((colonne == tabOrdonne.length - 1) && (ligne == 0)) {
				f.btnSe.setVisible(false);
				f.btnE.setVisible(false);
				f.btnNe.setVisible(false);
				f.btnN.setVisible(false);
				f.btnNo.setVisible(false);
				f.btnO.setVisible(true);
				f.btnSo.setVisible(true);
				f.btnS.setVisible(true);

			} else if ((colonne == 0) && (ligne == 0)) {
				f.btnSo.setVisible(false);
				f.btnO.setVisible(false);
				f.btnNo.setVisible(false);
				f.btnN.setVisible(false);
				f.btnNe.setVisible(false);
				f.btnE.setVisible(true);
				f.btnSe.setVisible(true);
				f.btnS.setVisible(true);
			} else if ((colonne == (tabOrdonne.length - 1))
					&& (ligne == (tabOrdonne.length - 1))) {
				f.btnSe.setVisible(false);
				f.btnE.setVisible(false);
				f.btnNe.setVisible(false);
				f.btnN.setVisible(true);
				f.btnNo.setVisible(true);
				f.btnO.setVisible(true);
				f.btnSo.setVisible(false);
				f.btnS.setVisible(false);

			} else if (colonne == 0) {
				f.btnSo.setVisible(false);
				f.btnO.setVisible(false);
				f.btnNo.setVisible(false);
				f.btnN.setVisible(true);
				f.btnNe.setVisible(true);
				f.btnE.setVisible(true);
				f.btnSe.setVisible(true);
				f.btnS.setVisible(true);
			}

			else if (colonne == (tabOrdonne.length - 1)) {
				f.btnSe.setVisible(false);
				f.btnE.setVisible(false);
				f.btnNe.setVisible(false);
				f.btnN.setVisible(true);
				f.btnNo.setVisible(true);
				f.btnO.setVisible(true);
				f.btnSo.setVisible(true);
				f.btnS.setVisible(true);
			} else if (ligne == 0) {
				f.btnNe.setVisible(false);
				f.btnN.setVisible(false);
				f.btnNo.setVisible(false);
				f.btnO.setVisible(true);
				f.btnSo.setVisible(true);
				f.btnS.setVisible(true);
				f.btnSe.setVisible(true);
				f.btnE.setVisible(true);
			} else if (ligne == (tabOrdonne.length - 1)) {
				f.btnSe.setVisible(false);
				f.btnS.setVisible(false);
				f.btnSo.setVisible(false);
				f.btnO.setVisible(true);
				f.btnNo.setVisible(true);
				f.btnN.setVisible(true);
				f.btnNe.setVisible(true);
				f.btnE.setVisible(true);
			} else {
				f.btnSo.setVisible(true);
				f.btnO.setVisible(true);
				f.btnNo.setVisible(true);
				f.btnN.setVisible(true);
				f.btnNe.setVisible(true);
				f.btnE.setVisible(true);
				f.btnSe.setVisible(true);
				f.btnS.setVisible(true);
			}
		}

		if (visitState == 1) {
			if (i == 0) {
				f.button1.setVisible(false);
				f.button2.setVisible(false);
				f.button3.setVisible(true);
				f.button4.setVisible(true);
				f.button5.setVisible(true);
				f.btnPrecedent.setVisible(false);
				f.btnSuivant.setVisible(true);
			}

			else if (i == 1) {
				f.button1.setVisible(false);
				f.button2.setVisible(true);
				f.button3.setVisible(true);
				f.button4.setVisible(true);
				f.button5.setVisible(true);
				f.btnPrecedent.setVisible(true);
				f.btnSuivant.setVisible(true);
			} else if (i == tabBilk.length - 1) {
				f.button1.setVisible(true);
				f.button2.setVisible(true);
				f.button3.setVisible(true);
				f.button4.setVisible(false);
				f.button5.setVisible(false);
				f.btnSuivant.setVisible(false);
				f.btnPrecedent.setVisible(true);
			} else if (i == tabBilk.length - 2) {
				f.button1.setVisible(true);
				f.button2.setVisible(true);
				f.button3.setVisible(true);
				f.button4.setVisible(true);
				f.button5.setVisible(false);
				f.btnSuivant.setVisible(true);
				f.btnPrecedent.setVisible(true);
			} else {
				f.button1.setVisible(true);
				f.button2.setVisible(true);
				f.button3.setVisible(true);
				f.button4.setVisible(true);
				f.button5.setVisible(true);
				f.btnSuivant.setVisible(true);
				f.btnPrecedent.setVisible(true);
			}
		}

		f.affichageImage();
	}

	public void recupererPhotosVrac(int nbPhotos, FenetrePropre f)
			throws IOException, SAXException, FlickrException {
		// création d'un élément de type Flickr
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		PhotosInterface photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		PhotoList listePhoto1 = new PhotoList();
		tabBilk = new Picture[nbPhotos];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = f.txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags); // On definit le paramètre avec ce tag
		// param.setBBox("2.292", "48.856", "2.293", "48.857");

		try {
			// Récupération d'uneliste de photos
			listePhoto1 = photosInterface.search(param, nbPhotos, 0);
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

		// f.progressBar.setMaximum(nbPhotos);
		for (int compt1 = 0; compt1 <= nbPhotos - 1; compt1++) {
			// f.compteur++;
			// f.progressBar.setValue(f.compteur);
			Photo pic = (Photo) listePhoto1.get(compt1);
			Picture pict = new Picture(pic, geoInterface, nameV);
			tabBilk[compt1] = pict;
			System.out.println("Url de la photo : " + pict.getMediumUrl());
			System.out.println("tag de la photo : " + pict.getTag());
			System.out.println("Latitude : " + pict.getLatitudeP());
			System.out.println("Longitude : " + pict.getLongitudeP());
		}
	}

	public void retrievePictureOrdonnees(int nbPhotos, double longMin,
			double latMin, double longMax, double latMax, FenetrePropre f)
			throws IOException, SAXException, FlickrException {
		Flickr apiAccess = new Flickr("f5c2fede0f18c646f5e997586dc9c122");
		// création d'un élément de type PhotoInterface
		photosInterface = apiAccess.getPhotosInterface();
		// creation d'un élément de type GeoInterface
		geoInterface = apiAccess.getGeoInterface();
		listePhoto1 = new PhotoList();
		this.tabOrdonne = new Picture[(int) Math.sqrt(nbPhotos)][(int) Math
				.sqrt(nbPhotos)];

		// Création d'un paramètre de recherche
		SearchParameters param = new SearchParameters();
		String tagDemande = f.txtTapezVotreRecherche.getText();
		String[] tabTags = { tagDemande };
		param.setHasGeo(true);
		param.setTags(tabTags);
		// On definit le paramètre avec ce tag
		double xStep = ((longMax - longMin) / (int) Math.sqrt(nbPhotos));
		double yStep = ((latMax - latMin) / (int) Math.sqrt(nbPhotos));
		Double xMin = longMin;
		Double yMin = latMin;
		Double xMax = longMin + xStep;
		Double yMax = latMin + yStep;
		int l = 0;
		int c = 0;
		// f.progressBar.setMaximum(nbPhotos);
		for (int compt2 = 0; compt2 <= nbPhotos - 1; compt2++) {
			// compteur++;

			if (c < (Math.sqrt(nbPhotos) - 1)) {
				this.tabOrdonne[l][c] = photoPasDePhoto;
				c++;
			} else if (c == (Math.sqrt(nbPhotos) - 1)) {
				this.tabOrdonne[l][c] = photoPasDePhoto;
				c = 0;
				l++;
			}
		}

		c = 0;
		l = 0;
		for (int compt2 = 0; compt2 <= nbPhotos - 1; compt2++) {
			// compteur++;
			// progressBar.setValue(compteur);
			f.affichageImage();

			if (c < (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(xMin.toString(), yMin.toString(),
						xMax.toString(), yMax.toString());
				try {
					// Récupération d'uneliste de photos
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);

					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						Picture pic1 = new Picture(pic, geoInterface, nameV);
						this.tabOrdonne[l][c] = pic1;
						System.out.println("Url de la photo : "
								+ pic1.getMediumUrl());
						System.out
								.println("tag de la photo : " + pic1.getTag());
						System.out.println("Latitude : " + pic1.getLatitudeP());
						System.out.println("Longitude : "
								+ pic1.getLongitudeP());

					}

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
				c++;
				xMin = xMax;
				xMax = xMax + xStep;
			} else if (c == (Math.sqrt(nbPhotos) - 1)) {
				param.setBBox(String.valueOf(xMin), String.valueOf(yMin),
						String.valueOf(xMax), String.valueOf(yMax));
				try {
					PhotoList listePhoto1 = new PhotoList();
					listePhoto1 = photosInterface.search(param, 1, 0);

					if (!listePhoto1.isEmpty()) {
						Photo pic = (Photo) listePhoto1.get(0);
						positionGeographique = geoInterface.getLocation(pic
								.getId());
						Picture pic1 = new Picture(pic, geoInterface, nameV);
						this.tabOrdonne[l][c] = pic1;
						System.out.println("Url de la photo : "
								+ pic1.getMediumUrl());
						System.out
								.println("tag de la photo : " + pic1.getTag());
						System.out.println("Latitude : " + pic1.getLatitudeP());
						System.out.println("Longitude : "
								+ pic1.getLongitudeP());
					}

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
				yMin = yMax;
				yMax = yMax + yStep;
			}

		}
	}

	public void setVisitState(Integer state) {
		this.visitState = state;
	}

}
