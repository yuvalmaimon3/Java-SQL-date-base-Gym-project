import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public abstract class Employee extends Person{
	
	private Connection SQLconnection;
	
	public Employee()
	{
		setConnection();
	}
	
	public Connection getConnection()
	{
		return SQLconnection;
	}
	
	public void setConnection()
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
	
	public void Delete(){}
	
	public void Add(boolean callAdd)
	{
	// INSERT INTO TABLE (COLUMN) VALUE (VARIABLE);
		
		String table;
		
		if(callAdd)
		{
		System.out.println("\nAdd new member \n");	
		table = "Persons";
		}
		else
		{
			System.out.println("This member belong to employees or customers ?");
			
			table = scan.nextLine();
			
		}
	
		String Columns = PrepareColumns(table);							
						
		String newValues="";
		
		newValues = PrepareValues(table);
		
		Statement SQLstm;
		
		try {
			
				SQLstm = getConnection().createStatement();
			
				SQLstm.executeUpdate("INSERT INTO " + table +" ("+ Columns + ") VALUES " +
				"("+ newValues +" )");				
				
				if(callAdd)
				{
				
				Add(false);
				
				}
		}
		
		 catch (SQLException e) 
		{
			e.printStackTrace();
		}	
		
		
						
	}
	
	public void UpdatePerson()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nUpdate \n");
		
		String value, id, isExist = "";
		
		System.out.println("What is the id of the member would you like to change");
		
		id = input.nextLine();
		
		String [] columnAndTable  = new String [2];
		
		columnAndTable = ReturnApproperiateTable();
			
		System.out.println("Insert new value : ");
		 
		value = "'"+input.nextLine()+"'";
				
		try 
		{	
			
			Statement SQLstm = getConnection().createStatement();
			
			SQLstm.executeUpdate("UPDATE " +columnAndTable[1]+
					" SET " + columnAndTable[0] + " = " +value+
					" WHERE PERSON_ID = " + id);
			
			ResultSet SQLrs= SQLstm.executeQuery("SELECT "+ columnAndTable[0] +" FROM "
					+ columnAndTable[1]+" where person_id = " +id);
			
			if(SQLrs.next())
			isExist = SQLrs.getString(columnAndTable[0]);
			
			if(!isExist.equals(""))
			System.out.println("Update save.");
			
			else
				System.out.println("Update fail");
			
	
		} 
		
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		} 
		
	}
		
	public void Display()
	{
		System.out.println("\nInformation\n");
		
		String id;
		
		System.out.print("Member id : ");
		
		id = scan.nextLine();
	
		System.out.println("");
		
		try 
		{
			
			Statement SQLstm = SQLconnection.createStatement();
			
			ResultSet SQLrs = SQLstm.executeQuery("SELECT PERSON_ID FROM CUSTOMERS WHERE PERSON_ID  = "+id);
									
			String isExistInCustomers = "";
			
			PrepareDisplay("PERSONS",id);
			
			if(SQLrs.next())
				isExistInCustomers = SQLrs.getString(1);
			
				if(!isExistInCustomers.equals(""))
				{	
				FinishDate(id);
				
				PrepareDisplay("CUSTOMERS", id);
				}
				
				else
				PrepareDisplay("EMPLOYEES", id);
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}				
	}
	
	public void DisplayAll()
	{

		Statement SQLstm;
		
		try 
		{
		SQLstm = SQLconnection.createStatement();

		ResultSet  SQLrs = SQLstm.executeQuery("SELECT * FROM PERSONS");
		ResultSetMetaData SQLmt = SQLrs.getMetaData();
		while(SQLrs.next())
		{
		
			for(int j =1; j < 7; j++)
			{
			System.out.print(SQLmt.getColumnName(j)+": ");
			
			System.out.print(SQLrs.getString(j)+".  ");
			}
			System.out.println("\n");
			
			
		}
		
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	private String PrepareColumns(String table)
	{
		if(table.equalsIgnoreCase("Persons"))
		{
			return "FIRST_NAME,LAST_NAME,GENDER,GYM_ID,REGISTRATION_DATE";
		}
		
		else if(table.equalsIgnoreCase("Employees"))
		{
			return "PERSON_ID,PASSWORD,ROLE,PER_HOUR,HOURS";
		}
		
		else if(table.equalsIgnoreCase("Customers"))
		{
			return "PERSON_ID,MONTHLY_PAYMENT,MONTH";
		}
		return null;
	}
	
	private String PrepareValues(String table)
	{
		StringBuilder collectNewValues = new StringBuilder();
			
		int count = 0;
		
		boolean isNum = false;
		
		ResultSet SQLrs;
		
		int i;
		
		try {
			
			Statement SQLstm = SQLconnection.createStatement();
			
			if(!table.equalsIgnoreCase("PERSONS")) 
			{
			SQLrs = SQLstm.executeQuery("SELECT PERSON_ID FROM PERSONS ORDER BY PERSON_ID DESC LIMIT 1");
			
			if(SQLrs.next())
				
			collectNewValues.append("'"+SQLrs.getString(1)+"',");
			
			System.out.println("The id of the new Member is : "+SQLrs.getString(1));
			}
			 SQLrs = SQLstm.executeQuery("SELECT * FROM "+ table);
			
			ResultSetMetaData SQLmt = SQLrs.getMetaData();
			
			count = SQLmt.getColumnCount();		
					
			if(table.equals("Persons"))
			{
				count = count - 1;
				i = 1;
			}
			else
				i = 2;
			
				for(; i <= count; i++)
				{
					if(SQLmt.getColumnTypeName(i).equalsIgnoreCase("int unsigned") ||
							SQLmt.getColumnTypeName(i).equalsIgnoreCase("float") )
						
							isNum = true;
					
					System.out.print(SQLmt.getColumnName(i) + " : ");
					
						if(!isNum)
							collectNewValues.append("'");
					
					collectNewValues.append(scan.nextLine());
					
						if(!isNum)
							collectNewValues.append("'");
					
					if(i != count)
					collectNewValues.append(",");
					
					isNum = false;
					
				}
				
			return collectNewValues.toString();
			}
		
		catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return null;
	}
		
	private String[] ReturnApproperiateTable()
	{
		String [] columnAndTable = new String [2];
		
		System.out.println("What detail would you like to change ?");
		Scanner input = new Scanner(System.in);
		String property = input.nextLine();
		
		switch(property)
		{
		case "first name" : columnAndTable[0] = "FIRST_NAME"; columnAndTable[1] = "PERSONS"; 
		break; 
		
		case "last name": columnAndTable[0] = "LAST_NAME"; columnAndTable[1] = "PERSONS"; 
		break;
		
		case "gender" : columnAndTable[0] = "GENEDER"; columnAndTable[1] = "PERSONS"; 
		break;
		
		case "registration date": columnAndTable[0] = "REGISTRATION_DATE"; 
		columnAndTable[1] = "PERSONS";
		break;
				
		case "gym id": columnAndTable[0] = "GYM_ID"; columnAndTable[1] = "PERSONS";
			break;
			
		case "monthly payment": columnAndTable[0] = "MONTHLY_PAYMENT"; columnAndTable[1] = "CUSTOMERS";
		break;
		
		case "month": columnAndTable[0] = "MONTH"; columnAndTable[1] = "CUSTOMERS";
		break;
			
			
		case "password": columnAndTable[0] = "PASSWORD"; columnAndTable[1] = "EMPLOYEES";		
		break;
		
		case "role": columnAndTable[0] = "ROLE"; columnAndTable[1] = "EMPLOYEES";
		break;
		
		case "per hour": columnAndTable[0] = "PER_HOUR"; columnAndTable[1] = "EMPLOYEES";
		break;
		
		case "hours": columnAndTable[0] = "HOURS"; columnAndTable[1] = "EMPLOYEES";
		break;
		
		}		
		return columnAndTable;
	}
	
	private void PrepareDisplay(String table,String id)
	{
		
		int count, i = 1, money;
			
		float time;
		
		Statement SQLstm;
		
		try 
		{
			SQLstm = SQLconnection.createStatement();
					
		
		
		ResultSet  SQLrs = SQLstm.executeQuery("SELECT * FROM " +table+" WHERE PERSON_ID = "+id);
		
		ResultSetMetaData SQLmt = SQLrs.getMetaData();
		
		count = SQLmt.getColumnCount();
							
		if(SQLrs.next())
		{
			if(!table.equalsIgnoreCase("PERSONS"))
			{
				i = 2;
				
				if(table.equalsIgnoreCase("EMPLOYEES"))
					i = 3;
			}
			for (; i <= count; i++) 
			{
				System.out.print(SQLmt.getColumnName(i)+" : ");
				
				System.out.println(SQLrs.getString(i));
				
				System.out.println();
			}
			
			if(table.equalsIgnoreCase("CUSTOMERS"))
			{
				money = SQLrs.getInt(2);
				
				time = Integer.parseInt(SQLrs.getString(3));
				
				System.out.println("Total price : " + money*time);	
			}
			
			else if(table.equalsIgnoreCase("EMPLOYEES"))
			{
				money = SQLrs.getInt(4);
				
				time = SQLrs.getInt(5);
				
				System.out.println("Total salary : "+ money*time);
			}
						
	}
		
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	private void FinishDate(String id)
	{
		
		String registrationDate="", month="";
		
		Statement SQLstm;
		try {
			SQLstm = SQLconnection.createStatement();
			
			ResultSet SQLrs = SQLstm.executeQuery("SELECT Registration_date FROM PERSONS WHERE PERSON_ID = "+id);
			if(SQLrs.next())
			 registrationDate = SQLrs.getString(1);
			
			SQLrs = SQLstm.executeQuery("SELECT MONTH FROM CUSTOMERS WHERE PERSON_ID = "+id);
			if(SQLrs.next())
			 month = SQLrs.getString(1);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		//DATE
		Calendar c = Calendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d");
		
		try {
			
			Date date = sdf.parse(registrationDate);
						
			c.setTime(date);
			
			c.add(Calendar.MONTH, Integer.parseInt(month));
			
			System.out.println("Finish date : " + sdf.format(c.getTime())+"\n");
			
			
		}
		
		catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
	
}





