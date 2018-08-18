package filemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filemanager.models.Folder;

/**
 * Servlet implementation class CreateFolder
 */
@WebServlet("/CreateFolder")
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Integer idSeed = 100;
	
    public CreateFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Folder getFolder(Integer id) {
		List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");
		
		for (Folder f : folders)
			if (id.equals(f.getId())) return f;
		
		return null;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Integer parent = Integer.parseInt(request.getParameter("key"));
		response.setContentType("text/html");
		out.println("<html>");
		out.println( "<head><title>Create Folder</title></head>" );
	    out.println( "<body>");
	    
	    out.println( "<form action='CreateFolder?key="+ parent +"' method='post'>" );
	    out.println( "Folder Name: <input type='text' name='name' /> <br />" );
	    out.println( "<input type='submit' name='create' value='create' /> <br />" );
        out.println( "</form>" );

	    out.println( "</body>" );
		out.println( "</html>" );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		Folder parentFolder = getFolder(parent);
		
		String name = request.getParameter("name");
		
		
			folders.add(new Folder(++idSeed, name, parentFolder ));

		response.sendRedirect("MyFileManager?key="+ parent);
		
	}

}
