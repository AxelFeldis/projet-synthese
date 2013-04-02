import java.io.IOException;
import java.util.Date;
import org.xml.sax.SAXException;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.geo.GeoInterface;

public class Picture {
	private String largeUrl = "http://ec.comps.canstockphoto.com/can-stock-photo_csp2974897.jpg";
	private String mediumUrl = "http://ec.comps.canstockphoto.com/can-stock-photo_csp2974897.jpg";
	private String smallUrl = "http://ec.comps.canstockphoto.com/can-stock-photo_csp2974897.jpg";
	private float longitudeP = 0;
	private float latitudeP = 0;
	private Integer inOut;
	private String tagP = "Pas de Photos";
	private Date dateP = new Date();
	private GeoData positionGeographique;

	Picture(Photo p, GeoInterface geo, String tag) throws IOException, SAXException, FlickrException {
		mediumUrl = p.getMediumUrl();
		largeUrl = p.getLargeUrl();
		smallUrl = p.getSmallUrl();
		positionGeographique = geo.getLocation(p.getId());
		longitudeP = positionGeographique.getLongitude();
		latitudeP = positionGeographique.getLatitude();
		tagP = tag;

	}

	Picture() {
	}

	public String getTag() {
		return tagP;
	}

	public float getLongitudeP() {
		return longitudeP;
	}

	public float getLatitudeP() {
		return latitudeP;
	}

	public Integer getInOut() {
		return inOut;
	}

	public Date getDateP() {
		return dateP;
	}

	public String getLargeUrl() {
		return largeUrl;
	}

	public String getSmallUrl() {
		return smallUrl;
	}

	public String getMediumUrl() {
		return mediumUrl;
	}

}
