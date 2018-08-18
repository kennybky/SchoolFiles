package filemanager.models;


import java.util.Date;


public class File {
	Integer id;
    String name;
    File parent;
    String type;
    Date date;
    Long size;
    boolean folder;
    User user;
    
    public File(Integer id, String name, File parent, boolean isFolder, String type, Date date, Long size, User user) {
    	this.id = id;
    	this.name = name;
    	this.parent = parent;
    	this.folder = isFolder;
    	this.date = date;
    	this.size = size;
    	this.type = type;
    	this.user = user;
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
    	return folder;
    }
    
    public User getUser() {
    	return user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }
}
