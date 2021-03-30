package finalproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Employee{
	private int EID;
	private String employeeName;
	private double Salary;
	private int Age;
	private String restaurantName;
		
		public void readData2() {
		try
		{  
	 
		BufferedReader br = new BufferedReader(new FileReader("Docs/Employee.csv")); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyRestaurant","root","Password");
		
		PreparedStatement prst = con.prepareStatement("INSERT INTO Employee VALUES(?,?,?,?,?)");
	    String line;
		while ((line = br.readLine()) != null)   
		{  
		String[] result = line.split(","); 
		
		EID = Integer.parseInt(result[0]);
		employeeName = result[1];
		Salary = Double.parseDouble(result[2]);
		Age = Integer.parseInt(result[3]);
		restaurantName = result[4];
		
		prst.setInt(1,EID);
		prst.setString(2,employeeName);
		prst.setDouble(3,Salary);
		prst.setInt(4,Age);
		prst.setString(5,restaurantName);
		
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
		