package t4p.model;

import java.util.LinkedList;
import java.util.List;

import t4p.servicios.ServiceLocator;
import t4p.servicios.UserService;

public class User {

	private long id;
	private String username;
	private String password;
	private String fullName;
	private String description;
	private String foto;
	
	private List<User> followers = new LinkedList<User>();
	private List<User> following = new LinkedList<User>();
	UserService userService =  ServiceLocator.getInstance().getUserService();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<User> getFollowers() {
		if(followers == null || (followers!=null && followers.size()==0))
			followers = (List<User>)userService.obtnerSeguidores(this);
		
		return followers;
	}
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	public List<User> getFollowing() {
		if(following == null || (following!=null && following.size()==0))
			following = (List<User>)userService.obtnerSeguiendo(this);
		return following;
	}
	public void setFollowing(List<User> following) {
		this.following = following;
	}
	public boolean addFollower(User e) {
		if (!followers.contains(e)) {
			followers.add(e);
			e.addFollowing(this);
			return true;
		} 
		return false;
	}
	public boolean addFollowing(User e) {
		if (!following.contains(e)) {
			following.add(e);
			e.addFollower(this);
			return true;
		} 
		return false;
	}	
	
	public String getFoto() {
		if(foto == null)
			foto = (String)userService.obtenerFoto(this.username);
		return foto;
	
	}
	public void setFoto(String nomFoto) {
		this.foto = nomFoto;
		
	}

	
}
