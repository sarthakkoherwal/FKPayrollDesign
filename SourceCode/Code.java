package FilesHere;
import java.util.Scanner;
import java.sql.*;
class Employee{
	private static int count;
	boolean Umember;
	private int Id,paymentMethod,paymentRecieveMethod;
	private String Name,Address;
	Employee(){
		Id=0;
		paymentMethod=0;
		paymentRecieveMethod=0;
		Name="";
		Address="";
		Umember=false;
	}
	Employee(String Name,String Address,boolean Umember,int paymentRecieveMethod){
		count++;
		Id=count;
		this.Name=Name;
		this.Address=Address;
		this.Umember=Umember;
		this.paymentRecieveMethod=paymentRecieveMethod;
	}
	public void setPaymentMethod(int paymentMethod){
		this.paymentMethod=paymentMethod;
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
	public void timeCard(){
		hourlySalary temp=new hourlySalary();
		temp.insertTimeCard();
	}
	public void receiptSales(){
		monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
		temp.insertReceiptSales();
	}
	public void unionFees(){
		Union temp=new Union();
		temp.updateUnionFees();
	}
	public void delete(int id){
		//fetch the record form database whose Id matches with the Employee Id to be deleted
		if(Umember){
			Union temp=new Union();
			temp.deleteMember(id);
		}
		if(paymentMethod==1){
			hourlySalary temp=new hourlySalary();
			temp.deleteMember(id);
		}
		else if(paymentMethod==2){
			monthlySalaried temp=new monthlySalaried();
			temp.deleteMember(id);
		}
		else{
		monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
		temp.deleteMember(id);
		}
		//delete record from Employee table
	}
}
class Union{
	private int memberFees,serviceCharge,Id;
	Union(){
		Id=0;
		memberFees=0;
		serviceCharge=0;
	}
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
	public void updateUnionFees(){
		//for loop which iterates over all values in table Union and for every person in the table updates their membershipFees and serviceCharge
		System.out.println("Enter membership fees");
		System.out.println("Enter service charges");
	}
	public void deleteMember(int id){
		//delete member from table Union whose ID matches with id
	}
}
class hourlySalary{
	private static int rate;
	private int Id,hrs,date;
	static{
		rate=10;
	}
	hourlySalary(){
		Id=0;
		hrs=0;
		date=0;
	}
	hourlySalary(int Id){
		this.Id=Id;
	}
	public void insertTimeCard(){
		//for loop which iterates over all values in table hourlySalary and for every person in the table inserts their working hours for the current day
		System.out.println("Enter Date");
		System.out.println("Enter working hrs");
	}
	public void deleteMember(int id){
		//delete member from table hourly whose ID matches with id
	}
}
class monthlySalaried{
	private int salary,Id;
	monthlySalaried(){
		salary=0;
		Id=0;
	}
	monthlySalaried(int Id,int salary){
		this.Id=Id;
		this.salary=salary;
	}
	public void deleteMember(int id){
		//delete member from table monthlySalaried whose ID matches with id
	}
}
class monthlyCommissionedSalary{
	private static double commissionrate;
	private int date,amountsale,salary,Id;
	static{
		commissionrate=5.5;
	}
	monthlyCommissionedSalary(){
		commissionrate=0.0;
		date=0;
		amountsale=0;
		salary=0;
		Id=0;
	}
	monthlyCommissionedSalary(int Id,int salary){
		this.Id=Id;
		this.salary=salary;
	}
	public void insertReceiptSales(){
		//for loop which iterates over all values in table monthlyCommissionedSalary and for every person in the table inserts their sales amount and date
		System.out.println("Enter Date");
		System.out.println("Enter sales amount");
	}
	public void deleteMember(int id){
		//delete member from table monthlyCommisionedSalary whose ID matches with id
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
				Employee E=new Employee(Name,Address,Umember,paymentRecieveMethod);
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
				E.setPaymentMethod(paymentMethod);
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				choice=in.nextInt();
			}
		}
		System.out.println("Press 2 if you want to delete an employee");
		choice=in.nextInt();
		if(choice==2){
			while(choice==2){
				int Id;
				System.out.println("Enter the employee ID to be deleted");
				Id=in.nextInt();
				Employee temp=new Employee();
				temp.delete(Id);
				System.out.println("Press 2 to delete another record or 0 to exit");
				choice=in.nextInt();
			}
		}
		Employee e=new Employee();
		e.timeCard();
		//fetch today's date and it if is sunday then update sales receipt for every employee in table monthlyCommissionedSalary
		e.receiptSales();
		//fetch today's fate and if it is sunday then update Union information for all employees who belong to the union
		e.unionFees();
	}
}