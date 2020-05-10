package FilesHere;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Calendar;
import java.sql.*;
class Connect{
	private static String url="jdbc:sqlserver//localhost : 6675; database=Payroll";
	private static String name="sa";
	private static String pass="sarthak";
	public static Connection obj() throws Exception{
		class.forname("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn=DriverManager.getConnection(url,name,pass);
		return conn;
	}
}
class Employee{
	private static int count;
	Employee(){
		count++;
	}
	public void insertRecord(int Id,String Name,String Address,int Umember,int paymentRecieveMethod,int paymentMethod,String lastpaid){
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into employee values("+Id+","+Name+","+Address+","+Umember+","+paymentRecieveMethod+","+paymentMethod+lastpaid+")";
		st.executeUpdate(q);
		conn.close();
	}
	public int getId(){
		return count;
	}
	public void setUnionInfo(int Id){
		Union u=new Union();
		u.insertRecord(Id);
	}
	public void setHourlyInfo(int Id){
		hourlySalary h=new hourlySalary();
		h.insertHourlyInfo(Id);
	}
	public void setMonthlyInfo(int Id,int salary){
		monthlySalaried m=new monthlySalaried();
		m.insertMonthlyInfo(Id,salary);
	}
	public void setMonthySalesInfo(int Id,int salary){
		monthlyCommissionedSalary ms=new monthlyCommissionedSalary();
		ms.setMonthlySalesInfo(Id,salary);
	}
	// public void timeCard(){
	// 	hourlySalary temp=new hourlySalary();
	// 	temp.insertTimeCard();
	// }
	// public void receiptSales(){
	// 	monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
	// 	temp.insertReceiptSales();
	// }
	// public void unionFees(){
	// 	Union temp=new Union();
	// 	temp.updateUnionFees();
	// }
	// public void delete(int id){
	// 	//fetch the record form database whose Id matches with the Employee Id to be deleted
	// 	if(Umember){
	// 		Union temp=new Union();
	// 		temp.deleteMember(id);
	// 	}
	// 	if(paymentMethod==1){
	// 		hourlySalary temp=new hourlySalary();
	// 		temp.deleteMember(id);
	// 	}
	// 	else if(paymentMethod==2){
	// 		monthlySalaried temp=new monthlySalaried();
	// 		temp.deleteMember(id);
	// 	}
	// 	else{
	// 	monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
	// 	temp.deleteMember(id);
	// 	}
	// 	//delete record from Employee table
	// }
}
class Union{
	private int serviceCharge,Id;
	String date;
	public void insertRecord(int Id){
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into employee values("+Id+")";
		st.executeUpdate(q);
		conn.close();
	}
	// public void updateUnionFees(){
	// 	//for loop which iterates over all values in table Union and for every person in the table updates their membershipFees and serviceCharge
	// 	System.out.println("Enter membership fees");

	// 	System.out.println("Enter service charges");
	// }
	// public void deleteMember(int id){
	// 	//delete member from table Union whose ID matches with id
	// }
}
class hourlySalary{
	private static int rate;
	private int Id,hrs,date;
	static{
		rate=10;
	}
	public void insertHourlyInfo(int Id){
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into Hourly(Id) values("+Id+")";
		st.executeUpdate(q);
		conn.close();
	}
	// public void insertTimeCard(){
	// 	//for loop which iterates over all values in table hourlySalary and for every person in the table inserts their working hours for the current day
	// 	System.out.println("Enter Date");
	// 	System.out.println("Enter working hrs");
	// }
	// public void deleteMember(int id){
	// 	//delete member from table hourly whose ID matches with id
	// }
}
class monthlySalaried{
	private int salary,Id;
	public void insertMonthlyInfo(int Id,int salary){
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into Hourly(Id,salary) values("+Id+","+salary+")";
		st.executeUpdate(q);
		conn.close();
	}
	// public void deleteMember(int id){
	// 	//delete member from table monthlySalaried whose ID matches with id
	// }
}
class monthlyCommissionedSalary{
	private static double commissionrate;
	private int date,amountsale,salary,Id;
	static{
		commissionrate=5.5;
	}
	public void setMonthlySalesInfo(int Id,int salary){
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into Hourly(Id,salary,) values("+Id+","+salary+")";
		st.executeUpdate(q);
		conn.close();
	}
	// public void insertReceiptSales(){
	// 	//for loop which iterates over all values in table monthlyCommissionedSalary and for every person in the table inserts their sales amount and date
	// 	System.out.println("Enter Date");
	// 	System.out.println("Enter sales amount");
	// }
	// public void deleteMember(int id){
	// 	//delete member from table monthlyCommisionedSalary whose ID matches with id
	// }
}
public class Code{
	public static void main(String[] args){
		String  lastpaid = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		Scanner in=new Scanner(System.in);
		System.out.println("Press 1 to add new Employee");
		int choice=in.nextInt();
		if(choice==1){
			String Name,Address;
			int Id,paymentRecieveMethod;
			int Umember;
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
				if(Umember==1){
					E.setUnionInfo(Id);
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
				E.insertRecord(Id,Name,Address,Umember,paymentRecieveMethod,paymentMethod,lastpaid);
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
				// temp.delete(Id);
				System.out.println("Press 2 to delete another record or 0 to exit");
				choice=in.nextInt();
			}
		}
		// Employee e=new Employee();
		// e.timeCard();
		//fetch today's date and it if is sunday then update sales receipt for every employee in table monthlyCommissionedSalary
		// e.receiptSales();
		//fetch today's fate and if it is sunday then update Union information for all employees who belong to the union
		// e.unionFees();
	}
}