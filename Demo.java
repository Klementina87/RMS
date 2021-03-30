package finalproject;

import java.util.Scanner;
import java.sql.*;
import java.sql.Statement;

public class Demo {
	public static Connection con = null;
	public static void main(String[] args) {
		
	//NewRestaurant r = new NewRestaurant();   
	//Employee em = new Employee();
	//Menu m = new Menu();
	//Customer c = new Customer();
	//MenuOrder mo = new MenuOrder();
	//Payment p = new Payment();
	//Make maKe = new Make();
		
	//r.readData1();                          Creating an object of each class to access readData method. 
	//em.readData2();
	//m.readData3();
	//c.readData4();
	//mo.readData5();
	//p.readData6();
	//maKe.readData7();
	int option;
		
try {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
	
	 do // below messages will be printed as long as the user enters a number from 1-10. If 0 is entered the loop will be terminated
	 {
		System.out.println("Please select a query from below list.");
	 	System.out.println("1-Display information about every restaurant in our database");
	 	System.out.println("2-Display employees by name is ascending order");
	 	System.out.println("3-Count the number of employees below the age of 35 working in Sonic Restaurant");
	 	System.out.println("4-Display employees working in 'Chick-fil-A' with salary smaller than the average for those working in 'Beef Villa'");
	 	System.out.println("5-Display all customers who made payments with either Visa or MasterCard");
	 	System.out.println("6-Display restaurants in CA");
	 	System.out.println("7-Count the number of customers who made payments via Visa card between 5/15/2017 and 6/12/2017");
	 	System.out.println("8-Display order numbers with price between $2.95 and $19.30");
	 	System.out.println("9-Display a list of product names sorted by ID in descending order");
	 	System.out.println("10-Find a restaurant that serves Sirloin of beef with stewed tomatoes");
	 	System.out.println("0-Exit");	
	 	 
	 System.out.print("Enter a number of your choice from 1-10 and press Enter: ");
	 Scanner kbd = new Scanner(System.in);
	 option = kbd.nextInt();
 
	if (option == 1) 
	{
		Statement stmt = con.createStatement();
		ResultSet rs1 = stmt.executeQuery("Select * From Employee");
		while(rs1.next())
		{
			System.out.println("ID: " + rs1.getInt(1) + ", " + "Name: " + rs1.getString(2) + ", "
					+ "Salary: " + rs1.getDouble(3) + ", " + "Age: " + rs1.getInt(4));
		}
	}
		
	else if (option == 2)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs2 = stmt.executeQuery("Select RestaurantName From NewRestaurant Order By RestaurantName ASC");
		while(rs2.next())
		{
			System.out.println(rs2.getString(1));
		}
	}
	
	else if (option == 3)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs3 = stmt.executeQuery("Select Count(E.EID) AS Employee_Counter, E.EmployeeName From Employee AS E,"
				+ " NewRestaurant as R  Where E.Rname=R.RestaurantName and E.Age <= '35' and E.Rname = 'SONIC Drive In'");
		while(rs3.next())
		{
			System.out.println("The number of employees below the age of 35 is: "+ rs3.getInt(1));
		}
	}
	
	else if (option == 4)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs4 = stmt.executeQuery("Select E.EmployeeName, E.EID From Employee AS E, NewRestaurant AS R Where E.Rname=R.RestaurantName "
				+ "and R.RestaurantName='Chick-fil-A' and E.salary < "
				+ "(Select AVG(E.salary) AS averageSalary From Employee AS E, NewRestaurant AS R Where E.Rname=R.RestaurantName "
				+ "and R.RestaurantName='Beef Villa')");
		while(rs4.next())
		{
			System.out.println("EmployeeName: " + rs4.getString(1) + " EID: " + rs4.getInt(2));
		}
	}
	
	else if (option == 5)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs5 = stmt.executeQuery("Select C.CustomerName From Customer AS C, Payment as P, Make as M Where M.pID=P.PaymentID "
				+ "and M.custID=C.CID and P.PaymentMethod = 'Visa' union "
				+ "(Select C.CustomerName From Customer AS C, Payment as P, Make as M "
				+ "Where M.pID=P.PaymentID and M.custID=C.CID and P.PaymentMethod = 'MasterCard-Eurocard')");
		 while(rs5.next())
		{
			System.out.println(rs5.getString(1));	
		}
	}
	
	else if (option == 6)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs6 = stmt.executeQuery("Select R.RestaurantName From NewRestaurant as R,"
				+ " Menu as M Where M.Rname=R.RestaurantName and R.State in"
				+ "(Select State From NewRestaurant Where State ='CA')");
		 while(rs6.next())
		{
			System.out.println(rs6.getString(1));
		}
	}
	else if (option == 7)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs7 = stmt.executeQuery("Select Count(CID) as Customer_Counter From Customer AS C, Payment as P,"
				+ " Make as M Where M.pID=P.PaymentID and M.custID=C.CID and"
				+ " M.OrderPaymentDate >= '5/15/2017'and M.OrderPaymentDate<= '6/12/2017' and P.PaymentMethod = 'Visa'");
				
		 while(rs7.next())
		{
			System.out.println("The number of customers who made payments via Visa card between 5/15/2017 and 6/12/2017 are: " + rs7.getString(1));	
		}
		 
	}
	
	else if (option == 8)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs8 = stmt.executeQuery("Select OrderNumber From MenuOrder Where Price in"
				+ "(Select Price From MenuOrder Where Price >= '2.95 'and Price <= '19.30')");
				
		 while(rs8.next())
		{
			System.out.println(rs8.getString(1));
		} 
	}
	
	else if (option == 9)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs9 = stmt.executeQuery("Select ProductName From Menu Order By ProductID DESC");
				
		 while(rs9.next())
		{
			System.out.println(rs9.getString(1));
		} 
	}
	
	else if (option == 10)	
	{
		Statement stmt = con.createStatement();
		ResultSet rs10 = stmt.executeQuery("Select R.RestaurantName From NewRestaurant as R, "
				+ "Menu as M Where R.RestaurantName = M.Rname and M.ProductName ='Sirloin of beef with stewed tomatoes'");
				
		 while(rs10.next())
		{
			System.out.println(rs10.getString(1));
		} 
	}
	else if(option == 0)
	System.out.println("Goodbye!");
	kbd.close();
	}while(option != 0);

	con.close(); //closing the connection to the database
	
	}catch (SQLException e) //if no connection to the database, Java will print error message on the console
	{
		e.printStackTrace();
	}
 }
}
			 
			 

				

