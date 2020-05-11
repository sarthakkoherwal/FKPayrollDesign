import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
class hourlySalary{
	private static int rate;
	static{
		rate=10;
	}
	public void insertHourlyInfo(int Id) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into Hourly(Id,rate) values("+Id+","+rate+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void deleteMember(int id) throws Exception{
		//delete member from table hourly whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from Hourly where Id="+id;
		st.executeUpdate(q);
		conn.close();
	}
	public void insertTimeCard(int id,int hrs) throws Exception{
		Connection conn=Connect.obj();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
    	String q1="select rate from hourly where id="+id;
        Statement st= conn.createStatement();
     	ResultSet rs = st.executeQuery(q1);
     	int rate = rs.getInt("rate");
     	String q2="insert into hourly values ('"+id+","+hrs+","+date+","+rate+")";
     	st.executeUpdate(q2);
		conn.close();
	}
	public void updateRate(int rate) throws Exception{
		Connection conn=Connect.obj();
		String q="update hourly set rate="+rate;
		Statement st= conn.createStatement();
		st.executeUpdate(q);
		conn.close();
	}
}