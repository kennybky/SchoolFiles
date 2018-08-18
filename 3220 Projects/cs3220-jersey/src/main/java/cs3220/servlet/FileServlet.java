package cs3220.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.File;
import cs3220.util.DbUtils;

@WebServlet(urlPatterns = "/file/*", loadOnStartup = 1)
public class FileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public FileServlet()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        // Get file id from URI
        String uri = request.getRequestURI();
        int id = Integer
            .parseInt( uri.substring( uri.lastIndexOf( "/" ) + 1 ) );

        // Get file object from database
        DbUtils dbUtils = new DbUtils();
        File file = dbUtils.getFile( id );
        dbUtils.close();

        // Create a JSON object and
        StringBuilder sb = new StringBuilder();
        sb.append( "{" )
            .append( "\"id\":" )
            .append( file.getId() )
            .append( "," )
            .append( "\"name\":\"" )
            .append( file.getName() )
            .append( "\"," )
            .append( "\"parentId\":" )
            .append( file.getParentId() )
            .append( "," )
            .append( "\"folder\":" )
            .append( file.isFolder() )
            .append( "}" );

        // Set back the JSON object in response
        response.setContentType( "application/json" );
        response.getWriter().print( sb.toString() );
    }

}
