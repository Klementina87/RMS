package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Menu{
	private String ProductID;
	private String ProductName;
	private String restaurantName;
		
		public void readData3() {
		try
		{  
	 
		BufferedReader br = new BufferedReader(new FileReader("Docs/Menu.csv")); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
		PreparedStatement prst = con.prepareStatement("INSERT INTO Menu VALUES(?,?,?)");
		
	    String line;
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		
		ProductID = result[0];
		ProductName = result[1];
		restaurantName = result[2];
		
		prst.setString(1,ProductID);
		prst.setString(2,ProductName);
		prst.setString(3,restaurantName);
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

