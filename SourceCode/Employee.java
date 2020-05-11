import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
class Employee{
	private static int count;
	Employee(){
		count++;
	}
	public int getId(){
		return count;
	}
	public void insertRecord(int Id,String Name,String Address,int Umember,int paymentRecieveMethod,int paymentMethod,int salary) throws Exception{
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
		String q="insert into employee values("+Id+","+Name+","+Address+","+Umember+","+paymentRecieveMethod+","+paymentMethod+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void delete(int id) throws Exception{
		int Umember,paymentMethod;
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q1="select * from employee where id="+ id;
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
		String q2="delete from employee where Id="+id;
		st.executeUpdate(q2);
		conn.close();
	}
	public void timeCard(int id,int hrs) throws Exception{
		hourlySalary temp=new hourlySalary();
		temp.insertTimeCard(id,hrs);
	}
	public void receiptSales(int id,int salesamount) throws Exception{
		monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
		temp.insertReceiptSales(id,salesamount);
	}
	public void unionFees(int id,int serviceCharge) throws Exception{
		Union temp=new Union();
		temp.insertUnionFees(id,serviceCharge);
	}
	public void updateRate(int rate) throws Exception{
		hourlySalary temp=new hourlySalary();
		temp.updateRate(rate);
	}
	public void updateCommRate(int rate) throws Exception{
		monthlyCommissionedSalary temp=new monthlyCommissionedSalary();
		temp.updateCommRate(rate);
	}
}