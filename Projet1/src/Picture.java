import java.util.Date;


public class Picture {
	String URL;
	float longitudeP;
	float latitudeP;
	Integer inOut;
	String tag;
	Date dateP = new Date();
	
	Picture(String url, float lo, float la, Integer io, String t, Date d){
		URL = new String(url);
		longitudeP = lo;
		latitudeP = la;
		inOut = io;
		tag = new String(t);
		dateP = d;
	}
	
	public String getTag(){
		return tag;
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
	
	public String getUrl(){
		return URL;
	}

}
