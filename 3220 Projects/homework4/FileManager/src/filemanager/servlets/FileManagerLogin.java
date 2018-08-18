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


@WebServlet("/FileManagerLogin")
public class FileManagerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FileManagerLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    private User getUser(String name, String password) {
		List<User> users = (List<User>) getServletContext().getAttribute("users");
		
		for (User u : users) {
			if (name.equals(u.getUserId()) && password.equals(u.getPassword())) {
				return u;
			}
		}
		return null;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("FileManagerLogin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = getUser(name, password);
		
		if(user == null) {
			request.setAttribute("message", "Invalid Username or password");
			request.getRequestDispatcher("FileManagerLogin.jsp").forward(request, response);
		} else {
			session.setAttribute("user", user);
			response.sendRedirect("MyFileManager");
		}
	}

}
