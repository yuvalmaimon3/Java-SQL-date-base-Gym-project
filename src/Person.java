import java.util.Scanner;
public abstract class Person {
	Scanner scan = new Scanner(System.in);
	
	private int ID;
	
	private String First_name;
	
	private String Last_name;
	
	private String Addres;
	
	private String Phone_number;
	
	private String Gender;
	
	public void set_ID(int id)
	{	
		ID = id;
	}
	public int get_ID()
	{
		return ID;
	}
	
	
	public String getFirst_name()
	{
		return First_name;
	}
	public void setFirst_name(String fn)
	{
		First_name = fn;
	}
	
	
	
	public String getLast_name() 
	{
		return Last_name;
	}
	public void setLast_name(String last_name) 
	{
		Last_name = last_name;
	}
	
	
	
	public void set_Addres(String addres)
	{
		Addres = addres;
	}
	public String get_Addres()
	{
		return Addres;	
	}

	
	
	public void setPhone_number(String num)
	{
		if(num.length() > 5)
		{
			System.out.println("The number is too long");
			System.out.print("Card number :");
			this.setPhone_number(scan.nextLine());
		}
		else if(num.length() < 5)
		{
			System.out.println("The number is too short");
			System.out.print("Card number :");
			this.setPhone_number(scan.nextLine());
		}
		Phone_number = num;
	}
	public String getPhone_number()
	{
		return Phone_number;
	}
	
	
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		if(!gender.equals("Male") && !gender.equals("Female"))
		{
			System.out.println("Gender can be "+"Male"+" or "+"Female");
			System.out.print("Gender :");
			this.setGender(scan.nextLine());
		}
		Gender = gender;
	}
	

	

}
