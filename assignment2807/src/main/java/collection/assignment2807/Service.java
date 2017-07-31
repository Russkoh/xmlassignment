package collection.assignment2807;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import action.Actions;
import pojo.LibException;
import pojo.ActionList;


public class Service implements IService{
	static Map<LibException, Map<Integer,ActionList>> la = new HashMap<LibException,Map<Integer, ActionList>>();
	public Service() {
		super();
		init();
	}
	
	

	static Document readAndStore() throws Exception{
		 File inputFile = new File("xmlinput.xml");
        DocumentBuilderFactory dbFactory 
           = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        return doc;
	}
	
	

	static void  storeExceptions(Document doc,  Map la) {
		 
		 NodeList eList = doc.getElementsByTagName("exception");
		 NodeList aList = doc.getElementsByTagName("action");
         for (int temp3 = 0; temp3 < eList.getLength(); temp3++) {
        	 LibException le = new LibException();
        	 
        	 	Node aNode = aList.item(temp3);
	            Node eNode = eList.item(temp3);
	            if (eNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element exElement = (Element) eNode;
	                Element aeElement = (Element) aNode;
	                //Save exceptions
	                
	                le.setExceptionName(exElement.getAttribute("type"));
	                le.setModuleName(((Element)exElement.getParentNode()).getAttribute("name"));
	                le.setProjectName(((Element)(exElement.getParentNode().getParentNode())).getAttribute("name"));
	                //System.out.println(((Element)exElement.getParentNode()).getAttribute("name") + " module");
		          }
	           
	            	la.put(le, storeChildActions(aNode));
	                
	            }
	
		
	}

	

	static ActionList storeChildActions(Node aNode) {
		ActionList al = new ActionList();
		 Element aeElement = (Element) aNode;
		 
		 Map<String, String> attMap = new HashMap<String, String>();
		// System.out.println( aNode.getChildNodes().getLength());
         for (int temp5 = 0; temp5 < aNode.getChildNodes().getLength(); temp5++) {
         	
	          if(temp5%2==1){
	        	 // System.out.println("action : " 
		           //        + aeElement.getChildNodes().item(temp5).getNodeName()+" "+ aeElement.getChildNodes().item(temp5).getAttributes().item(0).getNodeValue());
		                attMap.put(aeElement.getChildNodes().item(temp5).getNodeName(), aeElement.getChildNodes().item(temp5).getAttributes().item(0).getNodeValue());
	          }
	         
         }
         
         al.setAttributeMap(attMap);
         al.setName(aeElement.getNodeName());
         return al;
		
	}

	public void init() {
		
		try{
			storeExceptions(readAndStore(),la);
			
		}catch (Exception e) {
	         e.printStackTrace();
	      }	
	}

	public String handleException(String projectName, String modName, Exception ex) {
		LibException le2 = new LibException();
		if(projectName == null){
			return "Project name must not be blank";
		}
		if(modName == null){
			return "Module name must not be blank";
		}
		if(ex == null){
			return "Exception must not be blank";
		}
		le2.setExceptionName(getExceptionName(ex));
		le2.setModuleName(modName);
		le2.setProjectName(projectName);
		
		return findException(le2);
		
		//return null;
	}

	String findException(LibException le2){
		
		Set set = la.entrySet();
        Iterator i = set.iterator();
		String ans= "";
		ActionList ansl = null;
		while(i.hasNext()){
			
			Map.Entry me = ((Map.Entry)i.next());
		
			if(le2.compareTo(me.getKey())== 0){
				
				ActionList al2 = new ActionList();
				al2 = (ActionList) me.getValue();
				Set alSet = al2.getAttributeMap().entrySet();
				
				Iterator j = alSet.iterator();
				while(j.hasNext()){
					Map.Entry alme = ((Map.Entry)j.next());
					//System.out.println(alme.getKey().toString() + alme.getValue().toString());
					ans = alme.getKey().toString();
					
					
				}
				factoryDesignSolution(ans,(ActionList)(me.getValue()));
				
				return "Done";
			}
			
		}
		Set set2 = la.entrySet();
        Iterator i2 = set2.iterator();
        
		while(i2.hasNext()){
			
			Map.Entry me = ((Map.Entry)i2.next());
		if(le2.getProjectName().compareTo(((LibException)me.getKey()).getProjectName())==0){
			ans = "";
			break;
		}
		ans = "Invalid Project Name";}
		if(ans ==""){
		Set set3 = la.entrySet();
        Iterator i3 = set3.iterator();
		while(i3.hasNext()){
			Map.Entry me = ((Map.Entry)i3.next());
		if(le2.getModuleName().compareTo(((LibException)me.getKey()).getModuleName())==0){
			ans = "";
			break;
			
		}ans = "Invalid Module Name";}}
		if(ans ==""){
		Set set4 = la.entrySet();
        Iterator i4 = set4.iterator();
		while(i4.hasNext()){
			
			Map.Entry me = ((Map.Entry)i4.next());
		if(le2.getExceptionName().compareTo(((LibException)me.getKey()).getExceptionName())==0){
			ans = "";
			break;
		}ans = "Invalid Exception Name";}}
		return ans;
	}
	private void factoryDesignSolution(String name,ActionList ac) {
		
		try{
			Class<?> c = Class.forName("action."+name);
			Object o = c.newInstance();
			Actions a = (Actions) o;
			a.executeAction(ac.getAttributeMap());
			
		}
		catch(ClassNotFoundException e){
			System.out.println("Class not found");
		} catch (InstantiationException e) {
			System.out.println("Instantiation error");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("illegal access ");
			e.printStackTrace();
		}
		
	}

	public String getExceptionName(Exception ex){
		//System.out.println("Exception name "+ ex.getClass().getSimpleName());
		return ex.getClass().getSimpleName();
		
	}
	
	

}
