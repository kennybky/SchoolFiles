package filemanager.servlets;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		List<User> users = new ArrayList<User>();
		User admin = new User("cysun", "abcd", "Chengyu", "Sun");
		users.add(admin);
		users.add(new User("kktogs", "xyz", "Adekola", "Togunloju"));
		users.add(new User("kenny", "cs3220", "Kenny", "Michaels"));
		getServletContext().setAttribute("users", users);
		
		File f = new File(1, "MyFiles", null, true, "Folder", new Date(), null, admin );
		
		File f1 = new File(4, "Other", f, true, "Folder", new Date(), null, admin);
		
		files.add(f);
		files.add(f1);
		files.add(new File(2, "Documents", null, true, "Folder", new Date(), null, admin ));
		files.add(new File(3, "Temp", null, true, "Folder", new Date(), null, admin ));
		
		String path = "/images";
		
		List<String> lib = new ArrayList<String>(); 
		
		String fileDir = getServletContext().getRealPath( path);
		
		 java.io.File file = new java.io.File(fileDir);
		 
		java.io. File[] listOfFiles = file.listFiles();
		
		for (java.io.File img : listOfFiles) {
			String[] name = img.getName().split("\\.");
			lib.add(name[0]);
		}
		getServletContext().setAttribute("imgLib", lib);	
		
		
		
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
		 Integer parent = 0;
		String key = request.getParameter("key");
		if (key !=null)
			parent = Integer.parseInt(key);	
		
			File parentFile = getFile(parent);
			
			
	       
	       if (user == null) {
	    	   response.sendRedirect("FileManagerLogin");
	       } else if (parentFile!=null && parentFile.getUser() !=user ) {
	    	   request.setAttribute("message", "You are not allowed to view this file");
	    	   request.getRequestDispatcher( "ErrorMessage.jsp" )
	           .forward( request, response );
	    	   
	       } else {
		
		
		
		
		
		
		
		File grand = null;
		
		if (parentFile !=null)
			 grand = parentFile.getParent();
		
		LinkedList<File> ancestors = new LinkedList<File>();
		File fileList = null;

		if (grand !=null){
			fileList = grand;
			ancestors.addFirst(fileList);
		}
		
		while (fileList !=null) {
			ancestors.addFirst(fileList.getParent());
			fileList = fileList.getParent();
		}
		
		request.setAttribute("grand", grand);
		request.setAttribute("parent", parent);
		request.setAttribute("parentFile", parentFile);
		request.setAttribute("ancestors", ancestors);
		
		request.getRequestDispatcher( "DisplayFiles.jsp" )
        .forward( request, response );
		
	       }
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
