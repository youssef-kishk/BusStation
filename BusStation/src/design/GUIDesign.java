package design;
import java.sql.SQLException;
import busStation.BusStation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
public class GUIDesign extends PanesAndNodes{
	Scene scene1=null;
	VBox firstWindowPane = new VBox();
	Stage stage = new Stage();
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException{
		scene1=null;
		scene1 = new Scene(firstWindowPane,500,270);
		primaryStage.setTitle("Bus Station");
		primaryStage.setScene(scene1);
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	public void welcomeWindow(){
	 	buttons.setSpacing(30);
		welcomeWindowDesign();
		buttons.getChildren().clear();
		buttons.getChildren().addAll(chooseEmployee,chooseCustomer,enter);
		firstWindowPane.getChildren().clear();
		firstWindowPane.getChildren().addAll(welcome,buttons);
	}
	
	String s="Customer Login";
	public void customerLoginPage(){
		name.clear();
		password.clear();
		login.setText(s);
		login.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
		//name text field design
		loginNodesDesign(name,enterLogin,"User Name");
		//password textField design
		loginNodesDesign(password,signup,"PASSWORD");
		EmployeeAndCustomerPane.setAlignment(Pos.CENTER);
		EmployeeAndCustomerPane.setSpacing(20);
		signupAndEnter.setSpacing(40);
		signupAndEnter.setAlignment(Pos.CENTER);
		signupAndEnter.getChildren().clear();
		signupAndEnter.getChildren().addAll(enterLogin,signup);
		EmployeeAndCustomerPane.getChildren().clear();
		EmployeeAndCustomerPane.getChildren().addAll(login,name,password,signupAndEnter);
		scene2=null;
		if (EmployeeAndCustomerPane.getScene() == null) {
		    Scene scene2 = new Scene(EmployeeAndCustomerPane,600,300);
		    stage.setScene(scene2);
		} else 
		    stage.setScene(EmployeeAndCustomerPane.getScene());
	}
	
	//if user chooser he's an customer from first screen
	public void employeelogininPage(){
		name.clear();
		password.clear();
		login.setText("Employee Login");
		login.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 30));
		loginNodesDesign(name,enterLogin,"ID");
		password.setPrefHeight(30);
		password.setMaxWidth(200);
		loginNodesDesign(password,new Button(),"PASSWORD");
		EmployeeAndCustomerPane.getChildren().clear();
		EmployeeAndCustomerPane.setAlignment(Pos.CENTER);
		EmployeeAndCustomerPane.setSpacing(15);
		signupAndEnter.getChildren().clear();
		signupAndEnter.getChildren().addAll(enterLogin);
		EmployeeAndCustomerPane.getChildren().addAll(login,name,password,signupAndEnter,enterLogin);
		if (EmployeeAndCustomerPane.getScene() == null) {
		    Scene scene2 = new Scene(EmployeeAndCustomerPane,500,270);
		    stage.setScene(scene2);
		} else 
		    stage.setScene(EmployeeAndCustomerPane.getScene());
	}
	 //signing up new customer
	public void CustomerSignUpPage(){
		signUpNodesDesign(firstName,"First Name");
		signUpNodesDesign(lastName,"Last Name");
		signUpNodesDesign(email,"Email");
		signUpNodesDesign(mobNum,"Mobile Number");
		signUpNodesDesign(userName,"User Name");
		signUpNodesDesign(signUpPass,"Password");
		signUpNodesDesign(validatePass,"Validate Password");
		signUpDone.setScaleX(1.3);
		signUpDone.setScaleY(1.3);
		SignUp.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
		newCustomerSignUpPane.setAlignment(Pos.CENTER);
		newCustomerSignUpPane.getChildren().addAll(SignUp,firstName,lastName,email,
				mobNum,userName,signUpPass,validatePass,signUpDone);
		newCustomerSignUpPane.setSpacing(15);
		Scene scene2 = new Scene(newCustomerSignUpPane,500,400); 
		stage.setScene(scene2);	
	}
	//Employee Account design
	HBox managerOption = new HBox();
	public void employeeAccount(int index){
		employeeAccountNodesDesign();
		welcomeEmployee.setText("<Welcome, "+BusStation.getEmployee().get(index).getFirstName()+" "+
								BusStation.getEmployee().get(index).getLastName()+">");
		completeInfo.setText("First Name: "+BusStation.getEmployee().get(index).getFirstName()+
							 "\nLast Name: "+BusStation.getEmployee().get(index).getLastName()+
							 "\nID: "+BusStation.getEmployee().get(index).getId()+
							 "\nSalary: "+BusStation.getEmployee().get(index).getSalary());
		if(BusStation.getEmployee().get(index).getPosition().equals("driver"))
			employeeBox.getChildren().addAll(welcomeEmployee,emplyeeInfo,completeInfo,showTrips,logout);
		else if(BusStation.getEmployee().get(index).getPosition().equals("manager")){
			managerOption.setSpacing(10);
			managerOption.getChildren().clear();
			managerOption.getChildren().addAll(showTripsManager,showVehiclesManager,showEmployeesManager);
			managerOption.setAlignment(Pos.CENTER);
			employeeBox.getChildren().clear();
			employeeBox.getChildren().addAll(welcomeEmployee,emplyeeInfo,completeInfo,managerOption,logout);
		}
		if (employeeBox.getScene() == null) {
		    Scene scene2 = new Scene(employeeBox,650,400);
		    stage.setScene(scene2);
		} else 
		    stage.setScene(employeeBox.getScene());
	}
	
	public void customerAccount(int index){
		customerAccountNodesDesign();
		welcomeCustomer.setText("<Welcome, "+BusStation.getCustomer().get(index).getFirstName()+" "+
				BusStation.getCustomer().get(index).getLastName()+">");
		completeInfo.setText("First Name: "+BusStation.getCustomer().get(index).getFirstName()+
			 "\nLast Name: "+BusStation.getCustomer().get(index).getLastName()+
			 "\nMobile Number: "+BusStation.getCustomer().get(index).getMobileNumber()+
			 "\nEmail: "+BusStation.getCustomer().get(index).getEmail()+
			 "\nuserName: "+BusStation.getCustomer().get(index).getUserName());
		customerOptions.getChildren().clear();
		customerOptions.getChildren().addAll(booking);
		customerBox.getChildren().clear();
		customerBox.getChildren().addAll(welcomeCustomer,customerInfo,completeInfo,customerOptions,logout);
		if (customerBox.getScene() == null) {
		    Scene scene2 = new Scene(customerBox,650,400);
		    stage.setScene(scene2);
		} else 
		    stage.setScene(customerBox.getScene());
	}
	
	int checkTripType=0;
	Text bookingDone = new Text();
	Button backToBockNewTickets = new Button("Back");
	public void customerBookTicket(){
		bookingDone.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		bookTicketDesign();
		bookingTicketVBox.getChildren().clear();
		bookingTicketVBox.getChildren().addAll(chooseTripType,tripTypeChoosingHBox,destinationHBox,showAvilableTrips,logout);
		choosecertainTripToBook.getChildren().clear();
		choosecertainTripToBook.getChildren().addAll(enterTripCode,numberOfTickets,doneEnterTripCode);
		choosecertainTripToBook.setSpacing(20);
		tripTypeChoosingHBox.getChildren().clear();
		tripTypeChoosingHBox.setAlignment(Pos.CENTER);
		tripTypeChoosingHBox.getChildren().addAll(internal,external);
		destinationHBox.setAlignment(Pos.CENTER);
		destinationHBox.getChildren().clear();
		destinationHBox.getChildren().addAll(from,to);
		if (bookingTicketVBox.getScene() == null) {
		    Scene scene2 = new Scene(bookingTicketVBox,650,400);
		    stage.setScene(scene2);
		} else 
		    stage.setScene(bookingTicketVBox.getScene());
	}
}