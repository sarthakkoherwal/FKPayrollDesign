package FilesHere;
import java.util.Scanner;
class Employee{
	private static int count;
	private int Id;
	private String Name,Address;
	Employee(){
		count++;
		Id=count;
	}
	public void setName(String Name){
		this.Name=Name;
	}
	public void setAddress(String Address){
		this.Address=Address;
	}
	public String getName(){
		return this.Name;
	}
	public String getAddress(){
		return Address;
	}
	public int getId(){
		return Id;
	}
}
public class Code{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		System.out.println("Press 1 to add new Employee");
		int choice=in.nextInt();
		if(choice==1){
			String Name,Address;
			int Id;
			while(choice==1){
				Employee E=new Employee();
				in.nextLine();
				System.out.println("Enter Name of Employee");
				Name=in.nextLine();
				System.out.println("Enter Address of Employee");
				Address=in.nextLine();
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				Id=E.getId();
				choice=in.nextInt();
			}
		}
	}
}