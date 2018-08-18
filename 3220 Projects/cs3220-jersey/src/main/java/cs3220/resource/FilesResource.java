package cs3220.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cs3220.model.File;
import cs3220.util.DbUtils;

@Path("/files")
public class FilesResource {

    private static final Logger logger = LoggerFactory
        .getLogger( FilesResource.class );

    @GET
    @Produces("application/json")
    public List<File> getFile()
    {
        DbUtils dbUtils = new DbUtils();
        List<File> files = dbUtils.getFiles();
        dbUtils.close();

        logger.debug( files.size() + " files loaded for the root folder" );

        return files;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public List<File> getFile( @PathParam("id") int id )
    {
        DbUtils dbUtils = new DbUtils();
        List<File> files = dbUtils.getFiles( id );
        dbUtils.close();

        logger.debug( files.size() + " files loaded for folder " + id );

        return files;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public File addFile( File file )
    {
        DbUtils dbUtils = new DbUtils();
        file = dbUtils.addFile( file );
        dbUtils.close();

        logger.debug( "New file id: " + file.getId() );

        return file;
    }

}
