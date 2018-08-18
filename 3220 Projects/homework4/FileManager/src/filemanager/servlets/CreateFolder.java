package filemanager.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filemanager.models.File;
import filemanager.models.User;


@WebServlet("/CreateFolder")
public class CreateFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Integer idSeed = 100;
       

	
    public CreateFolder() {
        super();
       
    }

    @SuppressWarnings("unchecked")
    private File getFile(Integer id) {
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		
		for (File f : files)
			if (id.equals(f.getId())) return f;
		
		return null;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 User user = (User) session.getAttribute( "user");
	       
	       if (user == null) {
	    	   response.sendRedirect("FileManagerLogin");
	       } else {
		
		request.getRequestDispatcher("CreateForm.jsp").forward(request, response);
	       }
	}


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute( "user");
		 
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		
		Integer idSeed = (Integer) getServletContext().getAttribute("idSeed");
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		File parentFile = getFile(parent);
		
		String name = request.getParameter("name");
		
		
			files.add(new File(++idSeed, name, parentFile, true, "Folder", new Date(), null, user ));

			getServletContext().setAttribute("idSeed", idSeed);
		response.sendRedirect("MyFileManager?key="+ parent);
		
	}

}
