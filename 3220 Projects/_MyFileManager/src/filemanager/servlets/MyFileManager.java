package filemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import filemanager.models.*;


@WebServlet("/MyFileManager")
public class MyFileManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MyFileManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<Folder> folders = new ArrayList<Folder>();
		getServletContext().setAttribute("folders", folders);
		
		Folder f = new Folder(1, "MyFiles", null );
		
		Folder f1 = new Folder(4, "Other", f);
		
		folders.add(f);
		folders.add(f1);
		folders.add(new Folder(2, "Documents", null ));
		folders.add(new Folder(3, "Temp", null ));
		
		
			
		
	}
	
	@SuppressWarnings("unchecked")
	private Folder getFolder(Integer id) {
		List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");
		
		for (Folder f : folders)
			if (id.equals(f.getId())) return f;
		
		return null;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		List<Folder> folders = (List<Folder>) getServletContext().getAttribute("folders");
		
		Folder parentFolder = getFolder(parent);
		
		Folder grand = null;
		
		if (parentFolder !=null)
			 grand = parentFolder.getParent();
		
			
		out.println("<html>");
		out.println( "<head><title>File Manager</title></head>" );
	    out.println( "<body>");
	    
	    if (parent!= 0) 
	    	 out.print(parentFolder.getName());
	    
	    out.println("<a href='CreateFolder?key="+ parent +"'>[New Folder]</a>");
	    
	    
	    if (parent!= 0) {
	    out.println("<a href='EditFolder?key=" + parentFolder.getId() + "'>[Edit Folder]</a>");
	    out.println("<a href='DeleteFolder?key=" + parentFolder.getId() + "'>[Delete Folder]</a>");
	    }
	    
	    out.println("<br/><br/>");
	    if (parent!= 0) {
	    if (grand == null)
		    out.println("<a href='MyFileManager'>/...</a><br/>");
		    else
		    	 out.println("<a href='MyFileManager?key="+ grand.getId() +"'>/...</a><br/>");
	    }
	    if (parentFolder == null) {
	    	for (Folder f : folders) {
	    	    if (f.getParent() == null)
	    	     out.println("<a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a><br/>");
	    	    }
	    }
	    else
	    for (Folder f : folders) {
	    if (f.getParent() == parentFolder)
	     out.println("<a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a><br/>");
	    }
	    
	    out.println( "</body>" );
		out.println( "</html>" );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
