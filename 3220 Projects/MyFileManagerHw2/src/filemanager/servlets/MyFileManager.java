package filemanager.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
		Integer idSeed = 100;
		List<File> files = new ArrayList<File>();
		getServletContext().setAttribute("Files", files);
		
		getServletContext().setAttribute("idSeed", idSeed);
		
		File f = new File(1, "MyFiles", null, true, "Folder", new Date(), null );
		
		File f1 = new File(4, "Other", f, true, "Folder", new Date(), null);
		
		files.add(f);
		files.add(f1);
		files.add(new File(2, "Documents", null, true, "Folder", new Date(), null ));
		files.add(new File(3, "Temp", null, true, "Folder", new Date(), null ));
		
		
			
		
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
		SimpleDateFormat time = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		
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
		out.println( "<head><title>File Manager</title>" );
		out.println("<style> table {border-collapse:collapse; width:70%;}  td,th { border:1px black solid; padding: 10px; } a {text-decoration:none;}</style>");
		out.println("</head>");
	    out.println( "<body>");
	    
	    if (parent!= 0) {
	    	  if (grand == null)
	  		    out.println("<a href='MyFileManager'>..\\</a>");
	  		    else
	  		    	 out.print("<a href='MyFileManager?key="+ grand.getId() +"'>..\\</a>");
	    	 out.print(parentFile.getName());
	    }
	    
	    out.println("<a href='CreateFolder?key="+ parent +"'> [New Folder</a>");
	    out.println(" | ");
	    
	    out.println("<a href='Upload?key="+ parent +"'>[Upload File]</a>");
	    
	    
	
	    
	   out.println("<br/><br/>");
	   out.println("<table>");
	   
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
	    	     out.println("<tr><td><a href='Download?key="+  f.getId() +"&type=" + f.getType() +"&name=" + f.getName()+"'>" + f.getName() + "</a></td>");
		    	 out.println("<td>" + time.format(f.getDate()) + "</td>");
		    	 Long size = f.getSize();
		    	 String fSize;
		    	 if (size < 1024)
		    		 fSize = size + " B";
		    	 else
		    		 fSize = (size/1024) + " KB";
	    	     out.println("<td>" + fSize + "</td>");
	    	     } else {
	    	    	 out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
			    	 out.println("<td>" + time.format(f.getDate()) + "</td>");
	    	    	 out.println("<td></td>");
	    	     }
	    	     out.print("<td><a href='EditFolder?key=" + f.getId() + "'>Rename</a>");
	    		 out.println(" | <a href='DeleteFolder?key=" + f.getId() + "'>Delete</a></td>");
	    		 out.println("</tr>");
	    	    }
	    	}
	    }
	    else
	    for (File f : files) {
	    if (f.getParent() == parentFile) {
	    	 if (f.isFolder() == false) {
	    	     out.println("<tr><td><a href='Download?key="+  f.getId() +"&type=" + f.getType() +"&name=" + f.getName()+"'>" + f.getName() + "</a></td>");
		    	 out.println("<td>" + time.format(f.getDate()) + "</td>");
		    	 Long size = f.getSize();
		    	 String fSize;
		    	 if (size < 1024)
		    		 fSize = size + " B";
		    	 else
		    		 fSize = (size/1024) + " KB";
	    	     out.println("<td>" + fSize + "</td>");
	    	     } else {
	    	    	 out.println("<tr><td><a href='MyFileManager?key="+  f.getId() +"'>" + f.getName() + "</a></td>");
			    	 out.println("<td>" + time.format(f.getDate()) + "</td>");
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
