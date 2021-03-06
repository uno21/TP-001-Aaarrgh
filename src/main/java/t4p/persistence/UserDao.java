package t4p.persistence;

import java.util.Collection;

import t4p.model.User;


public interface UserDao {

	public abstract Collection<User> findAll();

	public abstract User findById(Long id);

	public abstract boolean delete(User user);

	public abstract void insert(User user);

	public abstract void update(User user);
	
	public abstract void agregarSeguidor(User user, User user1);
	
	

}