package action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class email extends Actions{

	@Override
	public void executeAction(Map<String, String> attributes) {
		
		Set set = attributes.entrySet();
        Iterator i = set.iterator();
		
		while(i.hasNext()){
			Map.Entry me = (Map.Entry)i.next();
			System.out.println("Sending "+ me.getKey() +" to " + me.getValue());
	}
	}
	
	
}
