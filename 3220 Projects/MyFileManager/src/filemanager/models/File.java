package filemanager.models;


import java.util.Date;


public class File {
	Integer id;
    String name;
    File parent;
    String type;
    Date date;
    Long size;
    boolean isFolder;
    
    public File(Integer id, String name, File parent,boolean isFolder) {
    	this.id = id;
    	this.name = name;
    	this.parent = parent;
    	this.isFolder = isFolder;
    }
	
    public File getParent() {
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
