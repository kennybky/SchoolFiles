package filemanager.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Upload")
public class Upload extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public Upload()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher("UploadForm.jsp").forward(request, response);
    }
    
    @SuppressWarnings("unchecked")
    private filemanager.models.File getFile(Integer id) {
		List<filemanager.models.File> files = (List<filemanager.models.File>) getServletContext().getAttribute("Files");
		
		for (filemanager.models.File f : files)
			if (id.equals(f.getId())) return f;
		
		return null;
	}

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	Integer idSeed = (Integer) getServletContext().getAttribute("idSeed");
    	List<filemanager.models.File> files = (List<filemanager.models.File>) getServletContext().getAttribute("Files");
    	
    	Integer parentId = Integer.parseInt(request.getParameter("key"));
    	
    	filemanager.models.File parentFile = getFile(parentId);
    	
    	
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig()
            .getServletContext();
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        factory.setRepository( repository );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );

        
        // The directory we want to save the uploaded files to.
        String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );
        // Parse the request
        try
        {
            List<FileItem> items = upload.parseRequest( request );
            for( FileItem item : items )
            {
                // If the item is not a form field - meaning it's an uploaded
                // file, we save it to the target dir
                if( !item.isFormField() )
                {
                    // item.getName() will return the full path of the uploaded
                    // file, e.g. "C:/My Documents/files/test.txt", but we only
                    // want the file name part, which is why we first create a
                    // File object, then use File.getName() to get the file
                    // name.
                     String fileName = (new File( item.getName() )).getName();
                    
                    File file = new File( fileDir, ++idSeed +"" );
                    item.write( file );
                    
                    getServletContext().setAttribute("idSeed", idSeed);
            		files.add(new  filemanager.models.File(idSeed, fileName, parentFile, false, item.getContentType(), new Date(), item.getSize() ));
                }
            }

        }
        catch( Exception e )
        {
            throw new IOException( e );
        }

        
        
        response.sendRedirect("MyFileManager?key=" + parentId);
    }
    
   

}