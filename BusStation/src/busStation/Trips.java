package busStation;

public class Trips extends BusStation{
	private int tripCode;
	private String type;
	private String start;
	private String end;
	private int vehicleNum;
	private int startTime;
	private int driverID;
	private int capacity;
	private int price;
	private int avilablePlaces;
	public Trips(int tripCode,String type, String start, String end, int vehicleNum, 
					int startTime, int driverID,int capacity,int price) {
		super();
		this.tripCode=tripCode;
		this.type = type;
		this.start = start;
		this.end = end;
		this.vehicleNum = vehicleNum;
		this.startTime = startTime;
		this.driverID = driverID;
		this.capacity=capacity;
		this.price=price;
		avilablePlaces=capacity;
	}
	public int getTripCode() {
		return tripCode;
	}
	public int getAvilablePlaces() {
		return avilablePlaces;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getPrice() {
		return price;
	}
	public String getType() {
		return type;
	}
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
	public int getVehicleNum() {
		return vehicleNum;
	}
	public int getStartTime() {
		return startTime;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setAvilablePlaces(int tickets) {
		this.avilablePlaces-= tickets;
	}
	
	

}
