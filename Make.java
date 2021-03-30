package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Make{
	private String OrderPaymentDate;
	private int pID;
	private String PrID;
	private String custID;
	
		public void readData7() {
		try
		{  
	  
		BufferedReader br = new BufferedReader(new FileReader("Docs/Make.csv"));
		String line;
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
	    PreparedStatement prst = con.prepareStatement("INSERT INTO Make VALUES(?,?,?,?)"); 
		
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		
		OrderPaymentDate = result[0];
		pID = Integer.parseInt(result[1]);
		PrID = result[2];
		custID = result[3];	
		
		prst.setString(1,OrderPaymentDate);
		prst.setInt(2,pID);
		prst.setString(3,PrID);
		prst.setString(4,custID);
		prst.executeUpdate();
		
		}  
		br.close();
	}
		catch(Exception e) 
	{  
		System.out.println(e);
	} 
	
}
		
}










		
