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
 * Servlet implementation class OtherProfileServlet
 */

public class OtherProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherProfileServlet() {
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

		UserService userService =  ServiceLocator.getInstance().getUserService();
		
		User usuario = (User)request.getSession().getAttribute(SessionConstants.USER);
		
		String idUser = request.getParameter("profile");
		Long id = Long.parseLong(idUser);
		User usuarioPerfil = userService.findById(id);
		boolean flag = userService.dejarDeSeguir(usuario, usuarioPerfil);
		List<User> followers = usuarioPerfil.getFollowers();
		List<User> following = usuarioPerfil.getFollowing();
			
		TweetService tweetService = ServiceLocator.getInstance().getTweetService();
		Collection<Tweet> allTweets = tweetService.getTweetByUser(usuarioPerfil);
	
		request.setAttribute("usuarioPerfil", usuarioPerfil);
		request.setAttribute("tweetsUsuario", allTweets);
		request.setAttribute("seguidores", followers);
		request.setAttribute("siguiendo", following);
		request.setAttribute("improperios", allTweets.size());
	//	request.setAttribute("flagDejarSeguir", flag);

		
		RequestDispatcher ruta = request.getRequestDispatcher("otherProfile.jsp");
		ruta.forward(request, response);
	}

}
