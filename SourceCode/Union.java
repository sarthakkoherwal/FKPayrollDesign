import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
class Union{
	public void insertUnionInfo(int Id) throws Exception{
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="insert into union values("+Id+")";
		st.executeUpdate(q);
		conn.close();
	}
	public void deleteMember(int id) throws Exception{
		//delete member from table Union whose ID matches with id
		Connection conn=Connect.obj();
		Statement st=conn.createStatement();
		String q="delete from Union where Id="+id;
		st.executeUpdate(q);
		conn.close();
	}
	public void insertUnionFees(int id,int serviceCharge) throws Exception{
		//for loop which iterates over all values in table Union and for every person in the table updates their membershipFees and serviceCharge
		Connection conn=Connect.obj();
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);
		Statement st=conn.createStatement();
     	String q="insert into Union values ('"+id+","+serviceCharge+","+date+")";
     	st.executeUpdate(q);
		conn.close();
	}
}