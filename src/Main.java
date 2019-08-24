import java.util.Scanner;

public class Main {
	
	public static void main(String [] args)
	{
		
		Login log = new Login();
		
		Factory factory = new Factory();
				
		Employee User;
		
		String id,employeeType;
		
		id = log.Verify();
		
		employeeType = log.EmployeeType(id);
		
		User = factory.CreateEmplyee(employeeType, id);
		
		Start(User);
				
	}
	static void Start(Employee User)
	{				
		Scanner input = new Scanner(System.in);
		
		int operation;
		
		boolean on = true;
				
		while(on)
		{
			PrintInstruction();
			
			operation = input.nextInt();
			
			switch(operation)
			{
			case 1 : User.Add(true);break;
			
			case 2 : User.UpdatePerson(); break;
			
			case 3 : User.DisplayAll();break;
			
			case 4 : User.Display();break;
			
			case 5: User.Delete(); break;
			
			case 6 : on = false; System.out.println("Good Bye"); break;
			}
			
			
		}
		input.close();
	}
	static void PrintInstruction()
	{
		System.out.println("\nMAIN \nClick the number of operation would you like to do and then press enter");
		
		System.out.println("(1) Add new person \n(2) Update data \n(3) Display all "
				+ "\n(4) Display private person \n(5) Delete \n(6) Quit");
	}
		

		
			
}



