package filemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filemanager.models.Folder;

/**
 * Servlet implementation class DeleteFolder
 */
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

    private Folder getFolder(Integer id) {
    	List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");
		
		for (Folder f : folders)
			if (id.equals(f.getId())) return f;
		
		return null;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");

		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		
		
		
		PrintWriter out = response.getWriter();
		
		Folder deleteFolder = getFolder(parent);
		
		Folder grand = deleteFolder.getParent();
		

		
		/*List<Folder> deleteChild = new ArrayList<Folder>();
		for (Folder fold : folders) {
				if (fold.getParent() == deleteFolder)
				deleteChild.add(fold);
		} 
		folders.remove(deleteFolder); */
		
		List<Folder> children = new ArrayList<Folder>();
		
		recursiveDelete(deleteFolder, folders, children);
		
		for (Folder f : children) {
			folders.remove(f);
		}
		
		if (grand == null) 
			response.sendRedirect("MyFileManager");
		else
		    	response.sendRedirect("MyFileManager?key="+ grand.getId());
	}

    private void recursiveDelete(Folder fold, List<Folder> folders, List<Folder> children) {
    	
    	for (Folder f: folders) {
    		if (f.getParent() == fold) {
    			recursiveDelete(f, folders, children);
    		}
    	}
    	children.add(fold);
    	
 }
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
