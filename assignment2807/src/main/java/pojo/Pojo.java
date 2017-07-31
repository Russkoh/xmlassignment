package pojo;

import java.util.HashSet;

import action.Actions;


public class Pojo implements Comparable<Pojo>{

	public Pojo(){
		
	}
	private String projectName;
	private String moduleName;
	private String exceptionname;
	private HashSet<Actions> hs = new HashSet<Actions>();
	
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setExceptionname(String exceptionname) {
		this.exceptionname = exceptionname;
	}

	Pojo(String pName, String mName, String eName){
		super();
		this.projectName = pName;
		this.moduleName = mName;
		this.exceptionname = eName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public String getExceptionname() {
		return exceptionname;
	}

	public void addNewAction(Actions a){
		hs.add(a);
	}
	
	

	public int compareTo(Pojo o) {
		
		String pName = this.getProjectName();
		String mName = this.getModuleName();
		String eName = this.getExceptionname();
		
		String p2Name = o.getProjectName();
		String m2Name = o.getModuleName();
		String e2Name = o.getExceptionname();
		
		if((pName.compareTo(p2Name) == 0)&& (mName.compareTo(m2Name) == 0) && (eName.compareTo(e2Name)==0)){
			return pName.compareTo(p2Name);
		}
		return mName.compareTo(m2Name);
	}
	
	
}
