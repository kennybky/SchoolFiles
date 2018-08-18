package filemanager.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import filemanager.models.File;
import filemanager.models.User;


@WebServlet("/DeleteFolder")
public class DeleteFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFolder() {
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
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		 User user = (User) session.getAttribute( "user");
		
		List<File> files = (List<File>) getServletContext().getAttribute("Files");

		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		
		File deleteFolder = getFile(parent);
		
	       
		 
	       if (user == null) {
	    	   response.sendRedirect("FileManagerLogin");
	       } else if(deleteFolder == null || deleteFolder.getUser() != user) {
	    	   request.setAttribute("message", "Invalid operation. You have no authorizarion for this transaction. "
						+ "Access denied.");
				request.getRequestDispatcher("ErrorMessage.jsp").forward(request, response);
	       }
	       
	       else {
		
		File grand = deleteFolder.getParent();
		

		
		
		List<File> children = new ArrayList<File>();
		
		recursiveDelete(deleteFolder, files, children);
		
		for (File f : children) {
			files.remove(f);
		}
		
		if (grand == null) 
			response.sendRedirect("MyFileManager");
		else
		    	response.sendRedirect("MyFileManager?key="+ grand.getId());
	       }
	}

    private void recursiveDelete(File fold, List<File> files, List<File> children) throws ServletException{
    	
    	for (File f: files) {
    		if (f.getParent() == fold) {
    			recursiveDelete(f, files, children);
    		}
    	}
    	 if (fold.isFolder() == false) {
    		 boolean success = deleteFile(fold.getId()+"");
    		 if (success == false)
    			 throw new ServletException("Error Path " + getServletContext().getRealPath("/WEB-INF/files/"+ fold.getId()) + " not found");
    	 }
    	children.add(fold);
    	
 }
    private boolean deleteFile(String name) {
    	String fileDir = getServletContext().getRealPath("/WEB-INF/files/"+ name);
    	
    	java.io.File file = new java.io.File(fileDir);
		boolean success = file.delete();
		  return success;
    }
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
