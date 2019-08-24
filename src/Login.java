import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login 
{
	private Connection SQLconnection;
	
	Scanner scan = new Scanner(System.in);
	
	public Login()
	{
		setConnection();
	}
	
	
	
	public Connection getConnection()
	{
		return SQLconnection;
	}
	
	private void setConnection()
	{
		 
		
		String URL = "jdbc:mysql://localhost:3306/gym1";
		
		String User = "root";
		
		String Password = "8520";
		try
		{
			 SQLconnection = DriverManager.getConnection(URL,User,Password);
		}
		
		catch (Exception ex)
		{
			System.out.println("Connection Erorr /n"+ex);
		}
	}
	
	public String Verify()
	{ 
		System.out.print("Your ID : ");
		
		String id = scan.nextLine();
		
		System.out.print("Your password :");
		
		String password = scan.nextLine() ;
		
		try 
		{
			
			Statement SQLstm = SQLconnection.createStatement();
		
			ResultSet SQLrs = SQLstm.executeQuery("SELECT PASSWORD FROM EMPLOYEES WHERE PERSON_ID = '" +id+"'");
	
			if(SQLrs.next())
			{
				
				if(password.equalsIgnoreCase(SQLrs.getString(1)))
				{
					SQLrs = SQLstm.executeQuery("SELECT FIRST_NAME FROM PERSONS WHERE PERSON_ID = '"+id+"'");
					if(SQLrs.next())
					System.out.println("Hello " + SQLrs.getString(1) );
					
					return id;
				}								
			}				
		} 
		
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Password or id incorrect");
		
		return Verify();
	}
			
	public String EmployeeType(String id)
	{
		Statement SQLstm;
		try {
			
			SQLstm = SQLconnection.createStatement();
			
			ResultSet SQLrs = SQLstm.executeQuery("SELECT ROLE FROM EMPLOYEES WHERE PERSON_ID = "+ id);
			
			if(SQLrs.next())		
			return SQLrs.getString("ROLE");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
}
