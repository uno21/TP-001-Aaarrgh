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
 * Servlet implementation class PopUpServlet
 */

public class PopUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int List = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UserService userService =  ServiceLocator.getInstance().getUserService();
		User usuario =  (User)request.getSession().getAttribute(SessionConstants.USER);
		
		List<User> followers = usuario.getFollowers();
		List<User> following = usuario.getFollowing();
		
	
		TweetService tweetService = ServiceLocator.getInstance().getTweetService();
		String username = usuario.getUsername();
		
		int numberOfTweets = tweetService.getNumberOfTweets(usuario);
		
		Collection<Tweet> allTweets = tweetService.getTweetByUser(usuario);
		Collection<Tweet> tweetsMentionMe = tweetService.getAllTweets(usuario);
	
		
		
		request.setAttribute("HiloConversacion", tweetsMentionMe);
		
		RequestDispatcher path = request.getRequestDispatcher("popUp.jsp");
		path.forward(request, response);
		
	}

}
