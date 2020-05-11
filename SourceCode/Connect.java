import java.util.Scanner;
import java.text.SimpleDateFormat;
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