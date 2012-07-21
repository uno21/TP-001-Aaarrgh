package t4p.acciones;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import t4p.model.Tweet;
import t4p.model.User;
import t4p.servicios.ServiceLocator;
import t4p.servicios.TweetService;
import t4p.servicios.UserService;

/**
 * Servlet implementation class TimelineServlet
 */

public class TimelineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TimelineServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request , response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		//UserService userService =  ServiceLocator.getInstance().getUserService();
		TweetService tweetService = ServiceLocator.getInstance().getTweetService();
		User usuario = (User)request.getSession().getAttribute(SessionConstants.USER);
		
		
		Collection<Tweet> allTweets = tweetService.getAllTweets(usuario);		
		String username = usuario.getUsername();
		Collection<User> followers = usuario.getFollowers();
		Collection<User> following = usuario.getFollowing();
		int numberOfTweets = tweetService.getNumberOfTweets(usuario);
		String urlFoto = usuario.getFoto(); 				
		
		
		request.setAttribute("allUserTweets", allTweets);
		request.setAttribute("usuario", username);
		request.setAttribute("seguidores", followers);
		request.setAttribute("siguiendo", following);
		request.setAttribute("improperios", numberOfTweets);
		request.setAttribute("foto", urlFoto);
		
		RequestDispatcher ruta = request.getRequestDispatcher("timeline.jsp");
		ruta.forward(request, response);
		
	}

}
