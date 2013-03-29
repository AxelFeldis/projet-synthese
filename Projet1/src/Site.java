
public class Site {
	private String nameS;
	private float longitudeS;
	private float latitudeS;
	
	Site(String n, float lo, float la){
		nameS = new String(n);
		longitudeS = lo;
		latitudeS = la;
	}
	
	public String getNameS(){
		return nameS;
	}
	
	public float getLatitudeS(){
		return latitudeS;
	}
	
	public float getLongitudeS(){
		return longitudeS;
	}
	
	public void retriveGeoSite(){}

}
