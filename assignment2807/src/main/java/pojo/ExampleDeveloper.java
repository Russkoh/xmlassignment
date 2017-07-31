package pojo;



import collection.assignment2807.IService;
import collection.assignment2807.Service;

public class ExampleDeveloper {
	private ExampleDeveloper(){
		super();
	}

	public static void main(String[] args){
		IService service = new Service();
		
		System.out.println(service.handleException("server", "deposit", new java.sql.SQLException()));
		
	}
}
