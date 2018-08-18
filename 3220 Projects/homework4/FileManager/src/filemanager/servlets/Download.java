package filemanager.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filemanager.models.User;

@WebServlet("/Download")
public class Download extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Download()
    {
        super();
    }
    
    @SuppressWarnings("unchecked")
    private filemanager.models.File getFile(Integer id) {
    	List<filemanager.models.File> files = (List<filemanager.models.File>) getServletContext().getAttribute("Files");
		
		for (filemanager.models.File f : files)
			if (id.equals(f.getId())) return f;
		
		return null;
	}

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	HttpSession session = request.getSession();
		 User user = (User) session.getAttribute( "user");
		 String key = request.getParameter("key");
		
	       
		 filemanager.models.File theFile = getFile(Integer.parseInt(key));
		 
	       if (user == null) {
	    	   response.sendRedirect("FileManagerLogin");
	       } else if(theFile == null || theFile.getUser() != user) {
	    	   request.setAttribute("message", "Invalid operation. You have no authorizarion for this transaction. "
						+ "Access denied.");
				request.getRequestDispatcher("ErrorMessage.jsp").forward(request, response);
	       }
	       
	       else {
    	
    	
        // Get the path to the file and create a java.io.File object
    	
    	String fpath = "/WEB-INF/files/" + key;
        String path = getServletContext()
            .getRealPath( fpath);
        File file = new File(path);
        
        String type = request.getParameter("type");
        String name = request.getParameter("name");

        // Set the response headers. File.length() returns the size of the file
        // as a long, which we need to convert to a String.
        response.setContentType( type );
        response.setHeader( "Content-Length", "" + file.length() );
        response.setHeader( "Content-Disposition",
            "inline; filename=" + name  );

        // Binary files need to read/written in bytes.
        FileInputStream in = new FileInputStream( file );
        OutputStream out = response.getOutputStream();
        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();
	       }
    }

}