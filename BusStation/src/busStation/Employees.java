package busStation;

public class Employees extends BusStation{
	private String firstName;
	private String lastName;
	private int id;
	private int vehicleNum;
	private int salary;
	private String position;
	private String password;
	public Employees(String firstName, String lastName, int id, int vehicleNum, int salary, String position,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.vehicleNum = vehicleNum;
		this.salary = salary;
		this.position = position;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getId() {
		return id;
	}
	public int getVehicleNum() {
		return vehicleNum;
	}
	public int getSalary() {
		return salary;
	}
	public String getPosition() {
		return position;
	}
	public String getPassword() {
		return password;
	}
	
}
