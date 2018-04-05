package design;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import busStation.BusStation;
import busStation.DbConnection;
import busStation.Employees;
import busStation.Trips;
import busStation.Vehicles;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class Actions extends GUIDesign{
	public  static void main(String[]args){
		try{
		Application.launch(args);
		}
		catch(RuntimeException ex){
			System.out.println("Please Run the MySQL Script attached in project folder first");
		}
	}
	public void start(Stage finalStage) throws ClassNotFoundException, SQLException {
		
		BusStation.readDataFromDB();
		// calling parent method body
		super.start(stage);
		welcomeWindowActions();
		customerSignup();
		loggingInToAccount();
		
	}
	int check=0;
	public void welcomeWindowActions(){	
		welcomeWindow();
		enter.setOnAction(e->{
			if(chooseEmployee.isSelected()){
				employeelogininPage();
				check=1;
			}
			else if (chooseCustomer.isSelected()){
				customerLoginPage();
				check=2;
			}
			});
	}
	//new customer
	public void customerSignup(){
		signup.setOnAction(e->{
			CustomerSignUpPage();
		});
		signUpDone.setOnAction(e->{
			int check=0;
			//first name validation
			for(int i=0;i<firstName.getLength();i++){
				if(!Character.isLetter(firstName.getText().charAt(i))){
					firstName.clear();
					check=1;
					break;
				}
			}
			//last name validation
			for(int i=0;i<lastName.getLength();i++){
				if(!Character.isLetter(lastName.getText().charAt(i))){
					lastName.clear();
					check=1;
					break;
				}	
			}
			//email validation
			Pattern pattern = Pattern.compile("^.+@.+\\..+$");
			Matcher matcher = pattern.matcher(email.getText());
			if(!matcher.matches()){
				email.clear();
				check=1;
			}
			//mobile number validation
			for(int i=0;i<mobNum.getLength();i++){
				if(!Character.isDigit(mobNum.getText().charAt(i))){
					mobNum.clear();
					check=1;
					break;
				}	
			}
			//user name validation
			if (userName.getText().contains(" ")){
				userName.clear();
				check=1;
			}
			//password validation
			if (signUpPass.getText().contains(" ")||validatePass.getText().contains(" ")
					||signUpPass.getText().equals(validatePass.getText())==false){
				signUpPass.clear();
				validatePass.clear();
				check=1;
			}
			if(check!=1){
				//add new customer method from bus Station class
				BusStation.addNewCustomer(firstName,lastName,mobNum,email,userName,signUpPass);
				s="Signed Up,Now Sign in";
				customerLoginPage();
			}
		});	
	}
	//log in to account
	public void loggingInToAccount(){
		enterLogin.setOnAction(e->{
			int notFound = 0;
			int id = 0;
			//check selected radio button,check = 1 employee,check =2 customer
			if(check==1){
				try{
				id=Integer.parseInt(name.getText());
				}
				catch(Exception ex){name.clear();password.clear();}
				for(int i=0;i<BusStation.getEmployee().size();i++){
					if(BusStation.getEmployee().get(i).getId()==id
							&&BusStation.getEmployee().get(i).getPassword().equals(password.getText())){
						name.clear();
						password.clear();
						employeeAcountActions(i);
						break;
					}
					notFound=i;
				}
			}
				else if(check==2){
					for(int i=0;i<BusStation.getCustomer().size();i++){
						if(BusStation.getCustomer().get(i).getUserName().equals(name.getText())
								&&BusStation.getCustomer().get(i).getPassword().equals(password.getText())){
							customerAcountActions(i);
							break;
						}
						notFound=i;
					}
				}
			if(notFound==BusStation.getEmployee().size()-1||notFound==BusStation.getCustomer().size()-1){
				name.clear();password.clear();
			}
		});
	}
	//employee's account options,either driver or manager
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void employeeAcountActions(int index){
		employeeAccount(index);
		//show certain driver trips
		showTrips.setOnAction(e->{
			emplyeeInfo.setText("Your Trips");
			//read Certain driver trips from DB
			try {
				String ss = "select * from trip where driverId=? and busNum=?";
				BusStation.con =DbConnection.connectToDB();
				PreparedStatement ps = BusStation.con.prepareStatement(ss);
				ps.setInt(1,BusStation.getEmployee().get(index).getId());
				ps.setInt(2,BusStation.getEmployee().get(index).getVehicleNum());
				BusStation.rs=ps.executeQuery();
				String trips="";
				while(BusStation.rs.next()){
					trips+="\nType:"+BusStation.rs.getString(1)+",Start: "+BusStation.rs.getString(2)+
					",End: "+BusStation.rs.getString(3)+",Vehicle: "+BusStation.rs.getString(4)+
					",Starts At: "+BusStation.rs.getString(5);
				}
				//show trips
				completeInfo.setText(trips);
			} catch (Exception e1) {}
		});
		//logout of employee account
		logout.setOnAction(e->{
			stage.setScene(scene1);
			welcomeWindowActions();
		});
		//Manager option of showing all trips in station
		showTripsManager.setOnAction(e->{
				TableView table = new TableView();
				ObservableList<Trips>data= FXCollections.observableArrayList(BusStation.getTrip());
				TableColumn<Trips, String> tripCodeCol = new TableColumn<Trips, String>("Trip Code");
				tablesDesign(tripCodeCol,"tripCode");
				TableColumn<Trips, String> typeCol = new TableColumn<Trips, String>("Trip Type");
				tablesDesign(typeCol,"type");
				TableColumn <Trips, String>startCol = new TableColumn<Trips, String>("From");
				tablesDesign(startCol,"start");
		        TableColumn <Trips, String>endCol = new TableColumn<Trips, String>("To");
		        tablesDesign(endCol,"end");
		        TableColumn <Trips, String>vechicleCol = new TableColumn<Trips, String>("Vehicle ID");
		        tablesDesign(vechicleCol,"vehicleNum");
		        TableColumn<Trips, String> timeCol = new TableColumn<Trips, String>("Departure Timing");
		        tablesDesign(timeCol,"startTime");
		        TableColumn<Trips, String> driverCol = new TableColumn<Trips, String>("Driver ID");
		        tablesDesign(driverCol,"driverID");
		        TableColumn<Trips, String> capacityCol = new TableColumn<Trips, String>("Capacity");
		        tablesDesign(capacityCol,"capacity");
		        TableColumn<Trips, String> priceCol = new TableColumn<Trips, String>("Price");
		        tablesDesign(priceCol,"price");
		        TableColumn<Trips, String> avilableplacesCol = new TableColumn<Trips, String>("Avilable Places");
		        tablesDesign(avilableplacesCol,"avilablePlaces");
		        table.setItems(data);
		        table.getColumns().addAll(tripCodeCol,typeCol, startCol,endCol,vechicleCol,timeCol,driverCol,
		        		priceCol,capacityCol,avilableplacesCol);
				completeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 15));
				emplyeeInfo.setText("All trips");
				employeeBox.getChildren().clear();
				employeeBox.getChildren().addAll(emplyeeInfo,table,managerOption,logout);
		});
		//Manager option of showing all Vehicles in Station
		showVehiclesManager.setOnAction(e->{
				TableView table = new TableView();
				ObservableList<Vehicles>data= FXCollections.observableArrayList(BusStation.getVehicle());
				TableColumn<Vehicles, String> typeCol = new TableColumn<Vehicles, String>("Type");
				tablesDesign(typeCol,"type");
				typeCol.setMinWidth(220);
				TableColumn <Vehicles, String>IdCol = new TableColumn<Vehicles, String>("Vehicle ID");
				tablesDesign(IdCol,"num");
				IdCol.setMinWidth(220);
		        TableColumn <Vehicles, String>tripTypeCol = new TableColumn<Vehicles, String>("Vehicle's Trip Type");
		        tablesDesign(tripTypeCol,"tripType");
		        tripTypeCol.setMinWidth(220);
		        table.setItems(data);
		        table.getColumns().addAll(typeCol,IdCol,tripTypeCol);
				completeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 18));
				emplyeeInfo.setText("All Vehicles");
				employeeBox.getChildren().clear();
				employeeBox.getChildren().addAll(emplyeeInfo,table,managerOption,logout);
		});
		//Manager Option of showing all Employees
		showEmployeesManager.setOnAction(e->{
				TableView table = new TableView();
				ObservableList<Employees>	data= FXCollections.observableArrayList(BusStation.getEmployee());
				TableColumn<Employees, String> firstNameCol = new TableColumn<Employees, String>("First Name");
				tablesDesign(firstNameCol,"firstName");
				firstNameCol.setMinWidth(130);
				TableColumn <Employees, String>lastNameCol = new TableColumn<Employees, String>("Last Name");
				tablesDesign(lastNameCol,"lastName");
				lastNameCol.setMinWidth(130);
		        TableColumn <Employees, String>IdCol = new TableColumn<Employees, String>("ID");
		        tablesDesign(IdCol,"id");
		        IdCol.setMinWidth(130);
		        TableColumn <Employees, String>vechicleCol = new TableColumn<Employees, String>("Vehicle");
		        tablesDesign(vechicleCol,"vehicleNum");
		        vechicleCol.setMinWidth(130);
		        TableColumn<Employees, String> positionCol = new TableColumn<Employees, String>("Position");
		        tablesDesign(positionCol,"position");
		        positionCol.setMinWidth(130);
		        TableColumn<Employees, String> salaryCol = new TableColumn<Employees, String>("Salary");
		        tablesDesign(salaryCol,"salary");
		        salaryCol.setMinWidth(130);
		        table.setItems(data);
		        table.getColumns().addAll(firstNameCol, lastNameCol,IdCol,vechicleCol,salaryCol);
				completeInfo.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16));
				emplyeeInfo.setText("All Employees");
				employeeBox.getChildren().clear();
				employeeBox.getChildren().addAll(emplyeeInfo,table,managerOption,logout);
		});
	}
	public void customerAcountActions(int index){
		customerAccount(index);
		logout.setOnAction(e->{
			stage.setScene(scene1);
			welcomeWindowActions();
		});
		booking.setOnAction(e->{
			customerBookTicketActions();
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void customerBookTicketActions(){
		
		customerBookTicket();
		tripStarting.setValue("from");
		internal.setOnMouseClicked(e->{
			internalTrip.setFill(Color.BLUE);
			externalTrip.setFill(Color.TRANSPARENT);
			checkTripType=0;
			tripStarting.getItems().clear();
			tripEnding.getItems().clear();
				tripStarting.getItems().addAll("tahrir","6th of october");
				tripEnding.getItems().addAll("6th of october","tahrir");
		
		});
		tripEnding.setValue("To");
		external.setOnMouseClicked(e->{
			externalTrip.setFill(Color.BLUE);
			internalTrip.setFill(Color.TRANSPARENT);
			checkTripType=1;
			tripStarting.getItems().clear();
			tripEnding.getItems().clear();
			tripStarting.getItems().addAll("cairo","alex","sharm");
			tripEnding.getItems().addAll("cairo","alex","sharm");
		});
		ArrayList <Trips> choosenTrips = new ArrayList <Trips>();
		showAvilableTrips.setOnAction(e->{
			if(!tripStarting.getValue().equals("from")&&!tripEnding.getValue().equals("To")&&
					tripStarting.getValue()!=tripEnding.getValue()&&
					!tripStarting.getValue().isEmpty()&&!tripEnding.getValue().isEmpty()){
				try {
					String ss = "select * from trip where tripStart=? and tripEnd=?";
					BusStation.con =DbConnection.connectToDB();
					PreparedStatement ps = BusStation.con.prepareStatement(ss);
					ps.setString(1,tripStarting.getValue());
					ps.setString(2,tripEnding.getValue());
					BusStation.rs=ps.executeQuery();
					
					String trips="";
					while(BusStation.rs.next()){
						for(int i=0;i<BusStation.getTrip().size();i++){
							if(BusStation.getTrip().get(i).getTripCode()==BusStation.rs.getInt(1)){
								choosenTrips.add(BusStation.getTrip().get(i));
								break;
							}
						}
					}
					if(choosenTrips.size()!=0){
					TableView table = new TableView();
					table.setMaxHeight(170);
					ObservableList<Trips>	data= FXCollections.observableArrayList(choosenTrips);
					TableColumn<Trips, String> tripCode = new TableColumn<Trips, String>("trip code");
					bookingTripsTableDesign(tripCode,"tripCode");
					TableColumn<Trips, String> startDestination = new TableColumn<Trips, String>("from");
					bookingTripsTableDesign(startDestination,"start");
					TableColumn<Trips, String> endDestination = new TableColumn<Trips, String>("To");
					bookingTripsTableDesign(endDestination,"end");
					TableColumn<Trips, String> timing = new TableColumn<Trips, String>("time");
					bookingTripsTableDesign(timing,"startTime");
					 table.setItems(data);
				     table.getColumns().addAll(tripCode, startDestination,endDestination,timing);
				     bookingTicketVBox.getChildren().clear();
				     bookingTicketVBox.getChildren().addAll(avilableTripsText,table,choosecertainTripToBook
				    		 								,backToBockNewTickets,logout);
				     
					}
					else{
						tripStarting.setValue("");
						tripEnding.setValue("");
					}
				} catch (Exception e1) {}
			}
			else{
				tripStarting.setValue("");
				tripEnding.setValue("");
			}
		});
		doneEnterTripCode.setOnAction(e->{
			for(int i=0;i<choosenTrips.size();i++){
				try{
				if(choosenTrips.get(i).getTripCode()==Integer.parseInt(enterTripCode.getText())){
					if(choosenTrips.get(i).getAvilablePlaces()>0&&
							Integer.parseInt(numberOfTickets.getText())<=choosenTrips.get(i).getAvilablePlaces()){
						choosenTrips.get(i).setAvilablePlaces(Integer.parseInt(numberOfTickets.getText()));
					bookingDone.setText("Done You Booked "+numberOfTickets.getText()+" Tickets "+
											" From "+choosenTrips.get(i).getStart()+
											" To "+choosenTrips.get(i).getEnd()+
											" Tomorrow At "+choosenTrips.get(i).getStartTime());
					enterTripCode.clear();
					numberOfTickets.clear();
					bookingTicketVBox.getChildren().add(bookingDone);
					break;
					}
					else{
						bookingDone.setText("Sorry No Avilable Places");
						enterTripCode.clear();
						numberOfTickets.clear();
						bookingTicketVBox.getChildren().add(bookingDone);
						break;
					}
					
				}
				else{
					bookingDone.setText("");
				}
			}
				catch(Exception ex){
					enterTripCode.clear();
					numberOfTickets.clear();
					}
				}
			enterTripCode.clear();
			numberOfTickets.clear();
		});
		backToBockNewTickets.setOnAction(e->{
			choosenTrips.clear();
			customerBookTicket();
		});
	}

}
