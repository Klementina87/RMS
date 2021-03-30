package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//Each table from my schema-MyRestaurant is represented by a class. Each attribute is represented by a private variable.
public class Payment{
	private int PaymentID;//in order to preserve encapsulation, each variable is private
	private String PaymentMethod;
	
	
		public void readData6() { //each class will be able to read and store data. 
		try
		{  
	  
		BufferedReader br = new BufferedReader(new FileReader("Docs/Payment.csv")); //BufferReader object is created to read info from csv file
	
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");//creating connection to the database
		
		PreparedStatement prst = con.prepareStatement("INSERT INTO Payment VALUES(?,?)");// I used PreparedStatement because it allows dynamic queries 
		String line;																				//with parameter inputs and prevents SQL injection attacks.
		while ((line = br.readLine()) != null) //this loop reads the records line by line
		{  
		String[] result = line.split(","); //record from each line are saved into an array and split by comma
		
		PaymentID = Integer.parseInt(result[0]);//parsing is needed because PaymentID is of type int that could not be hold into result of type String 
		PaymentMethod = result[1];
		
		prst.setInt(1,PaymentID); //set column 1 to store data about PaymentID
		prst.setString(2,PaymentMethod);
		prst.executeUpdate();
		//System.out.println(PaymentID  +  "," + PaymentMethod);     Used only to check if data prints correctly
		
		}  
		br.close(); //close the BufferReader
	}
		catch(Exception e) 
	{  
		System.out.println(e.toString());
	
	}
		
	}
}
	

		

















		
