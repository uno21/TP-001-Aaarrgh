package t4p.persistence;

import java.util.Collection;

import t4p.model.User;


public class UserDaoMock implements UserDao {

	public Collection<User> findAll() {
		return MockDataBase.getInstance().users.values();
	}

	public User findById(Long id) {
		return MockDataBase.getInstance().users.get(id);
	}

	public boolean delete(User user) {
		if (MockDataBase.getInstance().users.containsValue(user)) {
			MockDataBase.getInstance().users.remove(user.getId());
			return true;
		}
		return false;
	}

	public void insert(User user) {
		MockDataBase.getInstance().users.put(user.getId(), user);
	}

	public void update(User user) {
		MockDataBase.getInstance().users.put(user.getId(), user);
	}

	public void agregarSeguidor(User user, User user1) {
		// TODO Auto-generated method stub
		
	}

}
