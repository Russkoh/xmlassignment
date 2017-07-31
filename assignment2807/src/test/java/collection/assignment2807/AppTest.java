package collection.assignment2807;

import static org.junit.Assert.assertEquals;

import java.sql.SQLDataException;


import org.junit.Test;


public class AppTest {
    
 
	IService service = new Service();
    
    @Test
    public void validRequestForActions(){
    	assertEquals("Done", service.handleException("bank", "withdraw", new java.sql.SQLException()));
    }
 
    @Test
    public void nullProject(){
    	assertEquals("Project name must not be blank", service.handleException(null, "withdraw", new java.sql.SQLException()));
    }
    @Test
    public void nullModule(){
    	assertEquals("Module name must not be blank", service.handleException("bank", null, new java.sql.SQLException()));
    }
    @Test
    public void nullException(){
    	assertEquals("Exception must not be blank", service.handleException("bank", "withdraw",null));
    }
    @Test
    public void invalidProjectName(){
    	assertEquals("Invalid Project Name", service.handleException("ba", "withdraw", new java.sql.SQLException()));
    }
    
    @Test
    public void invalidModuleName(){
    	assertEquals("Invalid Module Name", service.handleException("bank", "withdaw", new java.sql.SQLException()));
    }
    @Test
    public void invalidException(){
    	assertEquals("Invalid Exception Name", service.handleException("bank", "withdraw", new SQLDataException()));
    }
    
}
