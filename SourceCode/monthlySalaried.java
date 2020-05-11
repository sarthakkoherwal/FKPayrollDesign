import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
class monthlySalaried{
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
		String q="delete from monthly where Id="+id;
		st.executeUpdate(q);
		conn.close();
	}
}