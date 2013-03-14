import java.io.IOException;
import java.util.Date;
import org.xml.sax.SAXException;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.photos.GeoData;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.geo.GeoInterface;


public class Picture {
	public String largeUrl;
	public String mediumUrl;
	public String smallUrl;
	float longitudeP;
	float latitudeP;
	Integer inOut;
	String tagP;
	Date dateP = new Date();
	GeoData positionGeographique;
	
	Picture() {
	}
	
	Picture(Photo p, GeoInterface geo, String tag) throws IOException, SAXException, FlickrException{
		mediumUrl = p.getMediumUrl();
		largeUrl = p.getLargeUrl();
		smallUrl = p.getSmallUrl();
		positionGeographique = geo.getLocation(p.getId());
		longitudeP = positionGeographique.getLongitude();
		latitudeP = positionGeographique.getLatitude();
		tagP = tag;

	}
	
	Picture(String url){
		mediumUrl = url;
		largeUrl = url;
		smallUrl = url;
		longitudeP = 0;
		latitudeP = 0;
		tagP = "Pas de Photo";
	}
	
	public String getTag(){
		return tagP;
	}
	
	public float getLongitudeP(){
		return longitudeP;
	}
	
	public float getLatitudeP(){
		return latitudeP;
	}
	
	public Integer getInOut(){
		return inOut;
	}
	
	public Date getDateP(){
		return dateP;
	}
	
	public String getLargeUrl(){
		return largeUrl;
	}
	
	public String getSmallUrl(){
		return smallUrl;
	}
	
	public String getMediumUrl(){
		return mediumUrl;
	}
	

}
