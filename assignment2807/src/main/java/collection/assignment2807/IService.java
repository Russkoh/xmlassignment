package collection.assignment2807;

import java.util.Map;

import pojo.ActionList;
import pojo.LibException;

public interface IService {

	public void init();
	public String handleException(String projectName, String modName, Exception ex);
}
