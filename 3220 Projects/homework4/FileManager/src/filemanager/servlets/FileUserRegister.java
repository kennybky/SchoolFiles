package filemanager.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filemanager.models.User;


@WebServlet("/FileUserRegister")
public class FileUserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session  = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("FileRegisterUser.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		
		
		if (username == null || password == null || password =="" || fName == null || lName == null ||
				username == "" || password =="" || fName == "" || lName == "") {
			request.setAttribute("message", "You must complete all fields");
			request.getRequestDispatcher("FileRegisterUser.jsp").forward(request, response);
		} else {
			List<User> users = (List<User>) getServletContext().getAttribute("users");
			boolean exists = checkIfExists(users, username);
			if(exists == true) {
				request.setAttribute("message", "Username already exists");
				request.getRequestDispatcher("FileRegisterUser.jsp").forward(request, response);
				
			} else {
			
			
			
			User user = new User(username, password, fName, lName); 
			users.add(user);
			session.setAttribute("user", user);
			response.sendRedirect("MyFileManager");
			}
		}
	}
	
	private boolean checkIfExists(List<User> users, String username) {
		for (User u : users) {
			if (u.getUserId().equals(username)) {
				return true;
			}
		}
		return false;
	}

}
