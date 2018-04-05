package busStation;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public abstract class BusStation{
	private static ArrayList<Vehicles> vehicle= new ArrayList<Vehicles>();
	private static  ArrayList<Employees> employee= new ArrayList<Employees>();
	private static ArrayList<Customers> customer= new ArrayList<Customers>();	 
	private static ArrayList<Trips> trip= new ArrayList<Trips>();	
	public static Connection con;
	public static ResultSet rs;
	public static ArrayList<Vehicles> getVehicle() {
		return vehicle;
	}
	public static ArrayList<Employees> getEmployee() {
		return employee;
	}
	public static ArrayList<Customers> getCustomer() {
		return customer;
	}
	public static ArrayList<Trips> getTrip() {
		return trip;
	}
	public static void readDataFromDB() throws SQLException, ClassNotFoundException{
		con =DbConnection.connectToDB();
		Statement s = con.createStatement();
		rs = s.executeQuery("Select * from vehicles");
		//read vehicles data
		while(rs.next())
			vehicle.add(new Vehicles(rs.getString(1),rs.getInt(2),rs.getString(3)));
		//read employees data
		rs = s.executeQuery("Select * from employees");
		while(rs.next())
			employee.add(new Employees(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)));
		//read customers data
		rs = s.executeQuery("Select * from customers");
		while(rs.next())
			customer.add(new Customers(rs.getString(1),rs.getString(2),rs.getInt(3),
					rs.getString(4),rs.getString(5),rs.getString(6)));
		rs = s.executeQuery("Select * from trip");
		while(rs.next())
			trip.add(new Trips(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9)));
	}
	//add New Customer to database
	public static void addNewCustomer(TextField firstName,TextField lastName,TextField mobNum,
							   TextField email,TextField userName,TextField pass){
		customer.add(new Customers(firstName.getText(),lastName.getText(),Integer.parseInt(mobNum.getText()),
									email.getText(),userName.getText(),pass.getText()));
		try{
			rs=null;
			Connection con=DbConnection.connectToDB();
			String sql = "insert  into customers (firstName,lastName,mobileNum,email,userName,customerPassword)"
					+ "values (?,?,?,?,?,?);";
			PreparedStatement s = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			s.setString(1, firstName.getText());
			s.setString(2, lastName.getText());
			s.setString(3, mobNum.getText());
			s.setString(4, email.getText());
			s.setString(5, userName.getText());
			s.setString(6, pass.getText());
			s.executeUpdate();
			rs=s.getGeneratedKeys();
			}
			catch(Exception ex){ex.printStackTrace();}
	}
}