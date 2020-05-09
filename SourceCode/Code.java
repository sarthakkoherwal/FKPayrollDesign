package FilesHere;
import java.util.Scanner;
public class Code{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		System.out.println("Press 1 to add new Employee");
		int choice=in.nextInt();
		// int choice=1;
		if(choice==1){
			String Name,Address;
			while(choice==1){
				in.nextLine();
				System.out.println("Enter Name of Employee");
				Name=in.nextLine();
				System.out.println("Enter Address of Employee");
				Address=in.nextLine();
				System.out.println("Press 1 to add another new Employee or 0 to exit");
				choice=in.nextInt();
			}
		}
	}
}