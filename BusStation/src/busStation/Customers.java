package busStation;

public class Customers extends BusStation{
	private String firstName;
	private String lastName;
	private int mobileNumber;
	private String email;
	private String userName;
	private String password;
	public Customers(String firstName, String lastName, int mobileNumber, String email, String userName,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getMobileNumber() {
		return mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
}
