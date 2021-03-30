package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Customer{
	private String CID;
	private String Phone;
	private String CustomerName;
	private String restaurantName;
	
		public void readData4() {
		try
		{  
		BufferedReader br = new BufferedReader(new FileReader("Docs/Customer.csv")); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
		PreparedStatement prst = con.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?)");
		
	    String line;
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		
		CID = result[0];
		Phone = result[1];
		CustomerName = result[2];
		restaurantName = result[3];
		
		prst.setString(1,CID);
		prst.setString(2,Phone);
		prst.setString(3,CustomerName);
		prst.setString(4,restaurantName);
		prst.executeUpdate();	
	
}  
br.close(); //close the BufferReader
}
catch(Exception e) 
{  
System.out.println(e.toString());

}

}
}















		

