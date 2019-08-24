
public class Factory 
{
	public Employee CreateEmplyee(String employeeType,String id)
	{
		if(employeeType.equals("Clerk"))
		{
			return new Clerk();
		}
		else if(employeeType.equals("Manager"))
			
			return new Manager();
					
		return null;
	}
}
