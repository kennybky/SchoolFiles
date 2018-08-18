package cs3220.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cs3220.model.File;
import cs3220.util.DbUtils;

@Path("/file/{id}")
public class FileResource {

    private static final Logger logger = LoggerFactory
        .getLogger( FileResource.class );

    @GET
    @Produces("application/json")
    public File getFile( @PathParam("id") int id )
    {
        DbUtils dbUtils = new DbUtils();
        File file = dbUtils.getFile( id );
        dbUtils.close();

        logger.debug( "File: [" + file.getId() + ", " + file.getName() + "]" );

        return file;
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public File updateFile( File file )
    {
        DbUtils dbUtils = new DbUtils();
        dbUtils.updateFile( file );
        dbUtils.close();

        logger.debug( "File " + file.getId() + " updated" );

        return file;
    }

    @DELETE
    public void deleteFile( @PathParam("id") int id )
    {
        DbUtils dbUtils = new DbUtils();
        dbUtils.deleteFile( id );
        dbUtils.close();

        logger.debug( "File " + id + " deleted" );
    }

}
