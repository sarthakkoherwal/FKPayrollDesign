import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
public class Code{
	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
		System.out.println("Press 1 to add new Employee");
		int choice=in.nextInt();
		if(choice==1){
			String Name,Address;
			int Id,paymentRecieveMethod;
			int Umember,salary=01;
			Employee E=new Employee();
			while(choice==1){
				in.nextLine();
				System.out.println("Enter Name of Employee");
				Name=in.nextLine();
				System.out.println("Enter Address of Employee");
				Address=in.nextLine();
				System.out.println("Enter the method in which the Employee recieves his salary");
				System.out.println("Press 1 to mail paycheck to postal address");
				System.out.println("Press 2 to recieve paycheck from paymaster");
				System.out.println("Press 3 for bank transfer");
				paymentRecieveMethod=in.nextInt();
				System.out.println("Press 1 if the employee is a member of Employee Union ans 0 if he is not a member");
				Umember=in.nextInt();
				Id=E.getId();
				System.out.println("Enter the payment method for Employee");
				System.out.println("Press 1 for hourly payment");
				System.out.println("Press 2 for monthly payment");
				System.out.println("Press 3 for monthly payment with sales incentive");
				int paymentMethod=in.nextInt();
				if(paymentMethod==2 || paymentMethod==3){
					System.out.println("Enter monthly salary");
					salary=in.nextInt();
				}
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				choice=in.nextInt();
				E.insertRecord(Id,Name,Address,Umember,paymentRecieveMethod,paymentMethod,salary);
			}
		}
		System.out.println("Press 2 if you want to delete an employee");
		choice=in.nextInt();
		while(choice==1){
			int Id;
			System.out.println("Enter the employee ID to be deleted");
			Id=in.nextInt();
			Employee E=new Employee();
			E.delete(Id);
			System.out.println("Press 2 to delete another record or 0 to exit");
			choice=in.nextInt();
		}
		System.out.println("Press 3 to post timecard for an employee");
		choice=in.nextInt();
		while(choice==3){
			int id,hrs;
			System.out.println("Enter Employee Id");
			id=in.nextInt();
			System.out.println("Enter working hours");
			hrs=in.nextInt();
			Employee E=new Employee();
			E.timeCard(id,hrs);
			System.out.println("Press 3 to enter timecard for another employee or 0 to exit");
			choice=in.nextInt();
		}
		// fetch today's date and it if is sunday then update sales receipt for every employee in table monthlyCommissionedSalary		e.receiptSales();  
		String  weekday = new SimpleDateFormat("E").format(Calendar.getInstance().getTime());
		if(weekday.equals("Sun")){
			System.out.println("Press 4 to post Sales receipt for employee");
			choice=in.nextInt();
			Employee E=new Employee();
			while(choice==4){
				int id,salesamount;
				System.out.println("Enter Employee Id");
				id=in.nextInt();
				System.out.println("Enter Employee salesamount");
				salesamount=in.nextInt();
				E.receiptSales(id,salesamount);
				System.out.println("Press 4 to post sales receipt for another employee or 0 to exit");
				choice=in.nextInt();
			}
			System.out.println("Press 5 to post Union Info for employee");
			choice=in.nextInt();
			while(choice==5){
				int id,serviceCharge;
				System.out.println("Enter Employee Id");
				id=in.nextInt();
				System.out.println("Enter Employee serviceCharge");
				serviceCharge=in.nextInt();
				E.unionFees(id,serviceCharge);
				System.out.println("Press 5 to post Union Info for another employee or 0 to exit");
				choice=in.nextInt();
			}			
		}
		System.out.println("Press 6 if you want to update Employee Info");
		choice=in.nextInt();
		if(choice==6){
			System.out.println("Press 1 if you want to change rate for hourly employees");
			System.out.println("Press 2 if you want to change commission rate for monthlycommissioned employees");
			int changechoice;
			changechoice=in.nextInt();
			Employee E=new Employee();
			int rate;
			if(changechoice==1){
				System.out.println("Enter new rate");
				rate=in.nextInt();
				E.updateRate(rate);
			}
			else{
				System.out.println("Enter new commission rate");
				rate=in.nextInt();
				E.updateCommRate(rate);
			}
		}
	}
}