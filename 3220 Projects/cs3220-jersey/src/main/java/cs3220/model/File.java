package cs3220.model;

public class File {

    private Integer id;

    private String name;

    private Integer parentId;

    private boolean isFolder = false;

    public File()
    {
    }

    public Integer getId()
    {
        return id;
    }

    public void setId( Integer id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId( Integer parentId )
    {
        this.parentId = parentId;
    }

    public boolean isFolder()
    {
        return isFolder;
    }

    public void setFolder( boolean isFolder )
    {
        this.isFolder = isFolder;
    }

}
