
package t4p.acciones;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4p.model.User;
import t4p.servicios.ServiceLocator;
import t4p.servicios.UserService;

/**
 * Servlet implementation class LoginServlet
 */


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 */
		
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		request.setAttribute("username", user);
	
		UserService userService = ServiceLocator.getInstance().getUserService();
		boolean correcto = userService.verifyUser(user, pass);
		
		
		if(correcto){
			User usuario = userService.findByUsername(user);
			request.getSession().setAttribute(SessionConstants.USER, usuario);
			
			RequestDispatcher a = request.getRequestDispatcher("timeline.do");
			a.forward(request, response);
		}
		
		else{
			
			RequestDispatcher a = request.getRequestDispatcher("error.do");
			a.forward(request, response);
		}
	}

}
