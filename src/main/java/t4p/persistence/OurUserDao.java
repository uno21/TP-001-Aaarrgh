package t4p.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import t4p.model.Tweet;
import t4p.model.User;

public class OurUserDao {
	
	private static OurUserDao instance = new OurUserDao();
	
	private OurUserDao(){
		
	}
	
	public static OurUserDao getInstance(){
		return instance;
	}
	
	public boolean verifyUser(final String user, final String pass){
		
			try {
			    Connection connectionManager = ConnectionProvider.getConnection();
				
				PreparedStatement statement = connectionManager
						.prepareStatement("select 1 from usuarios where usuario=? and clave=?");
				
				statement.setString(1, user);
				statement.setString(2, pass);
				
				ResultSet resultado = statement.executeQuery();
				
				if (resultado.next()) {
					
					return true;}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	
	public User findByUsername(String userName){

		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select usuario, clave, nombre, descripcion, id, picture from usuarios join persona  on id = id_person where usuario = ?");

			statement.setString(1, userName);
			ResultSet resultado = statement.executeQuery();	
				
				return crearUser(resultado);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

	public User findById(Long id) {
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select usuario, clave, nombre, descripcion, id, picture from usuarios join persona  on id = id_person where id = ?");

			statement.setLong(1, id);
			ResultSet resultado = statement.executeQuery();	
				
				return crearUser(resultado);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	

	public boolean removeFollowing	(User nuevoUsuario, User usuario) {

		List<User> following = usuario.getFollowing();
		
			if (following.contains(nuevoUsuario)) {
				following.remove(nuevoUsuario);
				return true;
			} 
			return false;
		}

	public boolean removeFollower(User nuevoUsuario, User usuario) {
		List<User> follower = nuevoUsuario.getFollowers();
		
		if (follower.contains(usuario)) {
			follower.remove(usuario);
			return true;
		} 
		return false;
	}	
		

	private User crearUser (ResultSet resultado) throws SQLException{
			if (resultado.next()) {
				
				User user = new User ();
				
				user.setUsername(resultado.getString(1));
				user.setPassword(resultado.getString(2));
				user.setFullName(resultado.getString(3));
				user.setDescription(resultado.getString(4));
				user.setId(resultado.getInt(5));
				user.setFoto(resultado.getString(6));
				
				return user;
			}
			else {
				return null;
			}
		
	}
	
	public void agregarSeguidor (User user, User userSeguidor){
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("INSERT INTO seguidores (usuario,usuario_seguidor) SELECT ?, ? WHERE NOT EXISTS (SELECT usuario,usuario_seguidor FROM seguidores WHERE usuario=? and usuario_seguidor=?);");

			
			statement.setString(1, user.getUsername());
			statement.setString(2, userSeguidor.getUsername());
			statement.setString(3, user.getUsername());
			statement.setString(4, userSeguidor.getUsername());
			
			statement.executeUpdate();	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
public void agregarSiguiendo (User user, User userSiguiendo){
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("insert into siguiendo (usuario, usuario_siguiendo ) values(?,?);");
			
			statement.setString(1, user.getUsername());
			statement.setString(2, userSiguiendo.getUsername());
			
			
			statement.executeUpdate();	
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	public Collection<User> obtnerSeguidores(User user){
		
		 
		Collection<User> seguidores = new LinkedList<User>();
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select u.usuario, u.clave, p.nombre, p.descripcion, u.id from usuarios u join persona p on u.id = p.id_person join seguidores s on s.usuario_seguidor = u.usuario where s.usuario = ?");
			
			
			statement.setString(1, user.getUsername());
			ResultSet resultado = statement.executeQuery();	
			
			while(resultado.next()){
				User user2 = new User ();
				
				user2.setUsername(resultado.getString(1));
				user2.setPassword(resultado.getString(2));
				user2.setFullName(resultado.getString(3));
				user2.setDescription(resultado.getString(4));
				user2.setId(resultado.getInt(5));
				
				seguidores.add(user2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  seguidores;
	}
	
	
	public Collection<User> obtnerSeguiendo(User user){
		
		 
		Collection<User> siguiendo = new LinkedList<User>();
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select u.usuario, u.clave, p.nombre, p.descripcion, u.id from usuarios u join persona p on u.id = p.id_person join seguidores s on s.usuario = u.usuario where s.usuario_seguidor = ?");
			
			
			statement.setString(1, user.getUsername());
			ResultSet resultado = statement.executeQuery();	
			
			
			while(resultado.next()){
				User user2 = new User ();
				
				user2.setUsername(resultado.getString(1));
				user2.setPassword(resultado.getString(2));
				user2.setFullName(resultado.getString(3));
				user2.setDescription(resultado.getString(4));
				user2.setId(resultado.getInt(5));
				
				siguiendo.add(user2);
			}	
						
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return  siguiendo;

	}
	
	
	
	
	public User getMention(String tweet){
		int x = 0;
		StringBuffer user;
		char[] text = tweet.toCharArray();
		StringBuffer cadena = new StringBuffer();
		
		
		do{
			cadena.append(text[x]);
			x++;
			
		}while(text[x]!=' ');
		
		user = cadena.delete(0, 1);
		
		return findByUsername(user.toString());
		
		
	}
	
	public String obtnerFoto(String userName){
		
		 
		String foto = new String();
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select picture from usuarios where usuario = ?");
			
			statement.setString(1, userName);
			ResultSet resultado = statement.executeQuery();
			
			if(resultado.next()){
				
				foto = resultado.getString(1);
			}
		
		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return foto;
	}	

	
	
	public boolean dejarDeSeguir(User usuario, User usuarioPerfil){
			
		boolean flag = false ;
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select * from seguidores where usuario = ? and usuario_seguidor = ?");
			
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuarioPerfil.getUsername());
			ResultSet resultado = statement.executeQuery();
			
			flag = (resultado.next()) ? true : false;
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (flag==true) ? true : false; 
		
	}		
				
}