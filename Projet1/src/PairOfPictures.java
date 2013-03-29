
public class PairOfPictures {
	private Picture pic1 = new Picture();
	private Picture pic2 = new Picture();
	
	public PairOfPictures (Picture p1, Picture p2) {
		pic1 = p1;
		pic2 = p2;
	}
	public PairOfPictures() {}
	
	public Picture getPic (int nb) {
		if (nb == 1) {
		return pic1;
		} else {
		return pic2;
		}
	}
	
	public void setPic1(Picture p) {
		pic1 =p;
	}
	
	public void setPic2(Picture p) {
		pic2 =p;
	}
	
}
