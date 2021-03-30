package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MenuOrder{
	private String OrderNumber;
	private double Price;
	private String PrID;
	
		public void readData5() {
		try
		{   
		BufferedReader br = new BufferedReader(new FileReader("Docs/MenuOrder.csv")); 
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
	    PreparedStatement prst = con.prepareStatement("INSERT INTO MenuOrder VALUES(?,?,?)");
	    
	    String line;
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		
		OrderNumber= result[0];
		Price = Double.parseDouble(result[1]);
		PrID = result[2];
		
		prst.setString(1,OrderNumber);
		prst.setDouble(2,Price);
		prst.setString(3,PrID);
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
