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
 * Servlet implementation class EditFile
 */
@WebServlet("/EditFolder")
public class EditFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		String value = getFile(parent).getName();
		
		response.setContentType("text/html");
		out.println("<html>");
		out.println( "<head><title>Edit File</title></head>" );
	    out.println( "<body>");
	    
	    out.println( "<form action='EditFolder?key="+ parent +"' method='post'>" );
	    out.println( "File Name: <input type='text' name='name' value='" + value + "' /> <br />" );
	    out.println( "<input type='submit' name='edit' value='edit' /> <br />" );
        out.println( "</form>" );

	    out.println( "</body>" );
		out.println( "</html>" );
	}
	
	@SuppressWarnings("unchecked")
	private File getFile(Integer id) {
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		
		for (File f : files)
			if (id.equals(f.getId())) return f;
		
		return null;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		String name = request.getParameter("name");
		File editFile = getFile(parent);
		
		File grand = editFile.getParent();
				
		editFile.setName(name);
		
		if (grand == null)
		response.sendRedirect("MyFileManager");
		else
			response.sendRedirect("MyFileManager?key=" + grand.getId());
	}

}
