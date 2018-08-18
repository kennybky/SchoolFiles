package filemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filemanager.models.File;

/**
 * Servlet implementation class CreateFile
 */
@WebServlet("/CreateFolder")
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Integer idSeed = 100;
	
    public CreateFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    private File getFile(Integer id) {
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		
		for (File f : files)
			if (id.equals(f.getId())) return f;
		
		return null;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Integer parent = Integer.parseInt(request.getParameter("key"));
		response.setContentType("text/html");
		out.println("<html>");
		out.println( "<head><title>Create File</title></head>" );
	    out.println( "<body>");
	    
	    out.println( "<form action='CreateFolder?key="+ parent +"' method='post'>" );
	    out.println( "File Name: <input type='text' name='name' /> <br />" );
	    out.println( "<input type='submit' name='create' value='create' /> <br />" );
        out.println( "</form>" );

	    out.println( "</body>" );
		out.println( "</html>" );
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		File parentFile = getFile(parent);
		
		String name = request.getParameter("name");
		
		
			files.add(new File(++idSeed, name, parentFile, true ));

		response.sendRedirect("MyFileManager?key="+ parent);
		
	}

}
