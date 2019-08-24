import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Customer extends Person {
	private int Card_number;
	private double Pay_per_month;
	private String End;
	private String Start;
	private int Program;
	


	public int getProgram() {
		return Program;
	}

	public void setProgram(int program) 
	{
		if(program != 365 && program != 180)
		{
			System.out.println("Progarm type can be 365 or 180 (days)");
			this.setProgram(scan.nextInt());
		}
		Program = program;
	}

	public String getEnd() {
		return End;
	}

	public void setEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		String end = Start;
		try
		{
		Date d = sdf.parse(end);
		cal.setTime(d);
		cal.add(Calendar.DATE, this.Program);
		End = sdf.format(cal.getTime());

		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("Some think worng in date input");
			System.out.println("Date format is (year/month/day");
			System.out.println("");
			System.out.print("Register date :");
			this.setStart(scan.nextLine());
						
		}
	}

	public String getStart() {
		return Start;
	}

	public void setStart(String start) {
	Start = start;
	this.setEnd();
	
	}

	public int getCard_number() {
		return Card_number;
		
	}

	public void setCard_number(int card_number) {
		if(card_number > 999 || card_number < 100)
		{
			System.out.println("card number can be only 3 digit");
			System.out.println("Try again");
			this.setCard_number(scan.nextInt());
		}
		Card_number = card_number;
	}

	

	public double getPay_per_month() {
		return Pay_per_month;
	}

	public void setPay_per_month(double pay_per_month) {
		Pay_per_month = pay_per_month;
	}
	

}
