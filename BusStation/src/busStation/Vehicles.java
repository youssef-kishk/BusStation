package busStation;

public class Vehicles extends BusStation{
	private String type;
	private int num;
	private String tripType;
	public Vehicles(String type,int num,String tripType){
		this.type=type;
		this.num=num;
		this.tripType=tripType;
	}
	public String getType() {
		return type;
	}
	public int getNum() {
		return num;
	}
	public String getTripType() {
		return tripType;
	}
}
