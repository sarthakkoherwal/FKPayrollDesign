package FilesHere;
import java.util.Scanner;
import java.sql.*;
class Employee{
	private static int count;
	boolean Umember;
	private int Id;
	private String Name,Address;
	Employee(String Name,String Address,boolean Umember){
		count++;
		Id=count;
		this.Name=Name;
		this.Address=Address;
		this.Umember=Umember;
	}
	public String getName(){
		return this.Name;
	}
	public String getAddress(){
		return this.Address;
	}
	public int getId(){
		return this.Id;
	}
	public void setUnionInfo(int Id,int memberFees,int serviceCharge){
		Union u=new Union(memberFees,serviceCharge,Id);
	}
	public void setHourlyInfo(int Id){
		hourlySalary h=new hourlySalary(Id);
	}
	public void setMonthlyInfo(int Id,int salary){
		monthlySalaried m=new monthlySalaried(Id,salary);
	}
	public void setMonthySalesInfo(int Id,int salary){
		monthlyCommissionedSalary ms=new monthlyCommissionedSalary(Id,salary);
	}
}
class Union{
	private int memberFees,serviceCharge,Id;
	Union(int memberFees,int serviceCharge,int Id){
		this.memberFees=memberFees;
		this.serviceCharge=serviceCharge;
		this.Id=Id;
	}
	public int getMemberFees(){
		return this.memberFees;
	}
	public int getServiceCharge(){
		return this.serviceCharge;
	}
}
class hourlySalary{
	private static int rate;
	private int Id,hrs,date;
	static{
		rate=10;
	}
	hourlySalary(int Id){
		this.Id=Id;
	}
}
class monthlySalaried{
	private int salary,Id;
	monthlySalaried(int Id,int salary){
		this.Id=Id;
		this.salary=salary;
	}
}
class monthlyCommissionedSalary{
	private static double commissionrate;
	private int date,amountsale,salary,Id;
	static{
		commissionrate=5.5;
	}
	monthlyCommissionedSalary(int Id,int salary){
		this.Id=Id;
		this.salary=salary;
	}
}
public class Code{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		System.out.println("Press 1 to add new Employee");
		int choice=in.nextInt();
		if(choice==1){
			String Name,Address;
			int Id,paymentRecieveMethod;
			boolean Umember;
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
				Umember=(1==in.nextInt());
				Employee E=new Employee(Name,Address,Umember);
				Id=E.getId();
				if(Umember==true){
					int memberFees,serviceCharge;
					System.out.println("Enter membership fees");
					memberFees=in.nextInt();
					System.out.println("Enter Service Charge");
					serviceCharge=in.nextInt();
					E.setUnionInfo(Id,memberFees,serviceCharge);
				}
				System.out.println("Enter the payment method for Employee");
				System.out.println("Press 1 for hourly payment");
				System.out.println("Press 2 for monthly payment");
				System.out.println("Press 3 for monthly payment with sales incentive");
				int paymentMethod=in.nextInt();
				if(paymentMethod==1){
					E.setHourlyInfo(Id);
				}
				else if(paymentMethod==2){
					int salary;
					System.out.println("Enter monthly salary");
					salary=in.nextInt();
					E.setMonthlyInfo(Id,salary);
				}
				else if(paymentMethod==3){
					int salary;
					System.out.println("Enter monthly Salary");
					salary=in.nextInt();
					E.setMonthySalesInfo(Id,salary);
				}
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				choice=in.nextInt();
			}
		}
	}
}