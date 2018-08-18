package filemanager.servlets;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filemanager.models.File;
import filemanager.models.User;


@WebServlet("/EditFolder")
public class EditFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EditFolder() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 User user = (User) session.getAttribute( "user");
	       
	       if (user == null) {
	    	   response.sendRedirect("FileManagerLogin");
	       }
	       
	       else {
		
		
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		File file = getFile(parent);
		
		if (file.getUser() !=user) {
			request.setAttribute("message", "Invalid operation. You have no authorizarion for this transaction. "
					+ "Access denied.");
			request.getRequestDispatcher("ErrorMessage.jsp").forward(request, response);
		} else {
		
		String value = file.getName();
		
		request.setAttribute("value", value);
		request.getRequestDispatcher("EditForm.jsp").forward(request, response);
		}
	      }
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
