import java.util.Date;


public class Visit {
	Integer idVisit;
	Integer visitState;
	String nameV;
	Date dateV;
	Picture[] tabBilk;
	Picture[][] tabInDoor;
	Picture[][] tabOutDoor;
	
	
	Visit(){}
	
	public String getNameV(){
		return nameV;
	}
	
	public Date getDateV(){
		return dateV;
	}
	
	public Integer getVisitState(){
		return visitState;
	}
	
	public void move(){
	}
	
	public void retrievePicture(){}
	
	public void setVisitState(Integer state){
		this.visitState = state;
	}
	
}
