import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
class monthlyCommissionedSalary{
	private static double commissionrate;
	static{
		commissionrate=5.5;
	}
	public void setMonthlySalesInfo(int Id,int salary) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into monthlySales(Id,salary,commissionrate) values("+Id+","+salary+","+commissionrate+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void deleteMember(int id) throws Exception{
		//delete member from table monthlySales whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from monthlySales where Id="+id;
		st.executeUpdate(q);
		conn.close();
	}
	public void insertReceiptSales(int id,int salesamount) throws Exception{
		Connection conn=Connect.obj();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
    	String q1="select commissionrate,salary from monthlySales where id="+id;
        Statement st= conn.createStatement();
     	ResultSet rs = st.executeQuery(q1);
     	int rate = rs.getInt("commissionrate");
     	int salary=rs.getInt("salary");
     	String q2="insert into monthlySales values ('"+id+","+salary+","+salesamount+","+rate+","+date+")";
     	st.executeUpdate(q2);
		conn.close();
	}
	public void updateCommRate(int rate) throws Exception{
		Connection conn=Connect.obj();
		String q="update monthlySales set commissionrate="+rate;
		Statement st= conn.createStatement();
		st.executeUpdate(q);
		conn.close();
	}
}