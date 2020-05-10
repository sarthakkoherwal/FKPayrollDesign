//package FilesHere;
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn=DriverManager.getConnection(url,name,pass);
		return conn;
	}
}
class Employee{
	private static int count;
	Employee(){
		count++;
	}
	public int getId(){
		return count;
	}
	public void insertRecord(int Id,String Name,String Address,int Umember,int paymentRecieveMethod,int paymentMethod,String lastpaid,int salary) throws Exception{
		if(Umember==1){
			Union u=new Union();
			u.insertUnionInfo(Id);	
		}
		if(paymentMethod==1){
			hourlySalary h=new hourlySalary();
			h.insertHourlyInfo(Id);
		}
		else if(paymentMethod==2){
			monthlySalaried m=new monthlySalaried();
			m.insertMonthlyInfo(Id,salary);
		}
		else{
			monthlyCommissionedSalary ms=new monthlyCommissionedSalary();
			ms.setMonthlySalesInfo(Id,salary);
		}
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into employee values("+Id+","+Name+","+Address+","+Umember+","+paymentRecieveMethod+","+paymentMethod+lastpaid+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void delete(int id) throws Exception{
		int Umember,paymentMethod;
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q1="select * from employee where id='"+ id+"'";
		ResultSet rs=st.executeQuery(q1);
		Umember=rs.getInt("Umember");
		paymentMethod=rs.getInt("paymentMethod");
		if(Umember==1){
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
		String q2="delete from employee where Id='"+id+"'";
		st.executeUpdate(q2);
		conn.close();
	}
	public void timeCard() throws Exception{
		hourlySalary temp=new hourlySalary();
		temp.insertTimeCard();
	}
	// public void receiptSales(){
	// 	monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
	// 	temp.insertReceiptSales();
	// }
	// public void unionFees(){
	// 	Union temp=new Union();
	// 	temp.updateUnionFees();
	// }
}
class Union{
	private int serviceCharge,Id;
	String date;
	public void insertUnionInfo(int Id) throws Exception{
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
	public void deleteMember(int id) throws Exception{
		//delete member from table Union whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from Union where Id='"+id+"'";
		st.executeUpdate(q);
		conn.close();
	}
}
class hourlySalary{
	private static int rate;
	private int Id,hrs,date;
	static{
		rate=10;
	}
	public void insertHourlyInfo(int Id) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into Hourly(Id) values("+Id+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void deleteMember(int id) throws Exception{
		//delete member from table hourly whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from Union where Id='"+id+"'";
		st.executeUpdate(q);
		conn.close();
	}
	public void insertTimeCard() throws Exception{
//		Connection conn=Connect.obj();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		// System.out.println(date); 
		// Statement st=conn.createStatement();
		System.out.println("Enter working hrs");
//		conn.close();
	}
}
class monthlySalaried{
	private int salary,Id;
	public void insertMonthlyInfo(int Id,int salary) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into monthly(Id,salary) values("+Id+","+salary+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void deleteMember(int id) throws Exception{
		//delete member from table monthly whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from monthly where Id='"+id+"'";
		st.executeUpdate(q);
		conn.close();
	}
}
class monthlyCommissionedSalary{
	private static double commissionrate;
	private int date,amountsale,salary,Id;
	static{
		commissionrate=5.5;
	}
	public void setMonthlySalesInfo(int Id,int salary) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into monthlySales(Id,salary) values("+Id+","+salary+")";
		st.executeUpdate(q);
		conn.close();
	}
	// public void insertReceiptSales(){
	// 	//for loop which iterates over all values in table monthlyCommissionedSalary and for every person in the table inserts their sales amount and date
	// 	System.out.println("Enter Date");
	// 	System.out.println("Enter sales amount");
	// }
	public void deleteMember(int id) throws Exception{
		//delete member from table monthlySales whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from monthlySales where Id='"+id+"'";
		st.executeUpdate(q);
		conn.close();
	}
}
public class Code{
	public static void main(String[] args) throws Exception{
		String  lastpaid = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
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
				// if(Umember==1){
				// 	E.setUnionInfo(Id);
				// }
				System.out.println("Enter the payment method for Employee");
				System.out.println("Press 1 for hourly payment");
				System.out.println("Press 2 for monthly payment");
				System.out.println("Press 3 for monthly payment with sales incentive");
				int paymentMethod=in.nextInt();
				if(paymentMethod==2 || paymentMethod==3){
					System.out.println("Enter monthly salary");
					salary=in.nextInt();
					// E.setMonthlyInfo(Id,salary);
				}
				// else if(paymentMethod==3){
				// 	System.out.println("Enter monthly Salary");
				// 	salary=in.nextInt();
				// 	// E.setMonthySalesInfo(Id,salary);
				// }
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				choice=in.nextInt();
				E.insertRecord(Id,Name,Address,Umember,paymentRecieveMethod,paymentMethod,lastpaid,salary);
			}
		}
		System.out.println("Press 1 if you want to delete an employee");
		choice=in.nextInt();
		if(choice==1){
			while(choice==1){
				int Id;
				System.out.println("Enter the employee ID to be deleted");
				Id=in.nextInt();
				Employee temp=new Employee();
				temp.delete(Id);
				System.out.println("Press 1 to delete another record or 0 to exit");
				choice=in.nextInt();
			}
		}
		Employee e=new Employee();
		e.timeCard();
		//fetch today's date and it if is sunday then update sales receipt for every employee in table monthlyCommissionedSalary
		// e.receiptSales();
		//fetch today's fate and if it is sunday then update Union information for all employees who belong to the union
		// e.unionFees();
	}
}