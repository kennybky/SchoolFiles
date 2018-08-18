package filemanager.models;


import java.util.Date;


public class Folder {
	Integer id;
    String name;
    Folder parent;
    String type;
    Date date;
    Long size;
    boolean isFolder;
    
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
    
    public Date getDate() {
    	return date;
    }
    
    public long getSize() {
    	return size;
    }
    
    public String getType() {
    	return type;
    }
    
    public boolean isFolder() {
    	return isFolder;
    }
}
