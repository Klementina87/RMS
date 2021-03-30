package finalproject;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.BufferedReader;
import java.io.FileReader;

public class NewRestaurant{
	private String restaurantName;
	private String address;
	private String city;
	private String State;
	private int zipCode;
		
		public void readData1() {
		
		try   
		{  
		BufferedReader br = new BufferedReader(new FileReader("Docs/Restaurant.csv")); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
		
		PreparedStatement prst = con.prepareStatement("INSERT INTO NewRestaurant VALUES(?,?,?,?,?)");
				
	    String line;
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		restaurantName = result[0];
		address = result[1];
		city = result[2];
		State = result[3];
		zipCode = Integer.parseInt(result[4]);
				
		prst.setString(1,restaurantName);
		prst.setString(2,address);
		prst.setString(3,city);
		prst.setString(4,State);
		prst.setInt(5,zipCode);
		prst.executeUpdate();

		}  
		br.close(); 
	}
		catch(Exception e) 
	{  
		System.out.println(e.toString());
	
	}
		
	}
}
		
	
		
		
		


		
					 
					 

						

