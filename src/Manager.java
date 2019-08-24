
import java.sql.SQLException;
import java.sql.Statement;


public class Manager extends Employee {
			
	public Manager ()
	{}
		

	public void Delete()
	{
		System.out.println("\nDelete");
		
		String table = "PERSONS";
						
		String id;
		
		System.out.print("Insert id : ");
		
		id = scan.nextLine();
		int isDelete = 0;
		try 
		{
			
			Statement SQLstm = getConnection().createStatement();
			
			isDelete = SQLstm.executeUpdate("DELETE FROM "+ table + " WHERE PERSON_ID = "+ id);
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
		if(isDelete != 0)
		System.out.println("id : "+id +" has been deleted");
		
		else
			System.out.println("User didnt exist");
	}


}

