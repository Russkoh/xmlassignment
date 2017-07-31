package pojo;

public class LibException implements Comparable<Object> {

	private String projectName;
	private String moduleName;
	private String exceptionName;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public int compareTo(Object o) {
		LibException ol = (LibException) o; 
		//System.out.println("project name "+this.getProjectName().compareTo(ol.getProjectName()));
		if(this.getProjectName().compareTo(ol.getProjectName())==0){
			
			//System.out.println("mod name "+ this.getModuleName().compareTo(ol.getModuleName()));
			if(this.getModuleName().compareTo(ol.getModuleName()) == 0){
				//System.out.println(" exception name "+this.getExceptionName().compareTo(ol.getExceptionName()));
				if(this.getExceptionName().compareTo(ol.getExceptionName())==0){
					
					return 0;
				}
			}
		}
		return 1;
	}
}
