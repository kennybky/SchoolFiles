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
		List<File> files = new ArrayList<File>();
		getServletContext().setAttribute("Files", files);
		
		File f = new File(1, "MyFiles", null, true );
		
		File f1 = new File(4, "Other", f, true);
		
		files.add(f);
		files.add(f1);
		files.add(new File(2, "Documents", null, true ));
		files.add(new File(3, "Temp", null, true ));
		
		
			
		
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Integer parent = 0;
		String key = request.getParameter("key");
		
		if (key !=null)
		parent = Integer.parseInt(key);
		
		List<File> files = (List<File>) getServletContext().getAttribute("Files");
		
		File parentFile = getFile(parent);
		
		File grand = null;
		
		if (parentFile !=null)
			 grand = parentFile.getParent();
		
			
		out.println("<html>");
		out.println( "<head><title>File Manager</title></head>" );
	    out.println( "<body>");
	    
	    if (parent!= 0) {
	    	  if (grand == null)
	  		    out.println("<a href='MyFileManager'>/...</a>");
	  		    else
	  		    	 out.print("<a href='MyFileManager?key="+ grand.getId() +"'>/...</a>");
	    	 out.print(parentFile.getName());
	    }
	    
	    out.println("<a href='CreateFolder?key="+ parent +"'>[New File]</a>");
	    
	    out.println("<a href='Upload?key="+ parent +"'>[Upload File]</a>");
	    
	    
	
	    
	    out.println("<br/><br/>");
	   out.println("<table border=1 cellpadding=2>");
	   
	   out.println("<tr>");
	    out.println("<th>Name</th>");
	    out.println("<th>Date</th>");
	    out.println("<th>Size</th>");
	    out.println("<th>Operations</th>");
	    
	    out.println("</tr>");
	    if (parentFile == null) {
	    	for (File f : files) {
	    	    if (f.getParent() == null) {
	    	     if (f.isFolder() == false) {
	    	     out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
		    	 out.println("<td>" + f.getDate() + "</td>");
	    	     out.println("<td>" + f.getSize() + "</td>");
	    	     } else {
	    	    	 out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
			    	 out.println("<td>" + f.getDate() + "</td>");
	    	    	 out.println("<td></td>");
	    	     }
	    	     out.println("<td><a href='EditFolder?key=" + f.getId() + "'>Rename</a>");
	    		 out.println(" | <a href='DeleteFolder?key=" + f.getId() + "'>Delete</a></td>");
	    		 out.println("</tr>");
	    	    }
	    	}
	    }
	    else
	    for (File f : files) {
	    if (f.getParent() == parentFile) {
	    	 if (f.isFolder() == false) {
	    	     out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
		    	 out.println("<td>" + f.getDate() + "</td>");
	    	     out.println("<td>" + f.getSize() + "</td>");
	    	     } else {
	    	    	 out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
			    	 out.println("<td>" + f.getDate() + "</td>");
	    	    	 out.println("<td></td>");
	    	     }
	    	     out.println("<td><a href='EditFolder?key=" + f.getId() + "'>Rename</a>");
	    		 out.println(" | <a href='DeleteFolder?key=" + f.getId() + "'>Delete</a></td>");
	    		 out.println("</tr>");
	    }
	    }
	    
	    out.println("</table>");
	    
	    out.println( "</body>" );
		out.println( "</html>" );
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
