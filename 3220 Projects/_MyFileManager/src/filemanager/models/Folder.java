package filemanager.models;


import java.util.Date;


public class Folder {
	Integer id;
    String name;
    Folder parent;
   
    
    public Folder(Integer id, String name, Folder parent) {
    	this.id = id;
    	this.name = name;
    	this.parent = parent;
    }
	
    public Folder getParent() {
    	return parent;
    }
    
    public String  getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public Integer getId(){
    	return id;
    }
    
}
