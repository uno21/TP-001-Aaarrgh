package t4p.persistence;

import java.lang.reflect.Array;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import t4p.model.Tweet;
import t4p.model.User;

public class OurTweetDao {
	
	private static OurTweetDao instance = new OurTweetDao();
	
	private OurTweetDao(){
		
	}
	
	public static OurTweetDao getInstance(){
		return instance;
	}
	
	public Collection<Tweet> getAllTweetsByUsername(User username){
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select * from tweet where author = ? order by id_tweet desc");

			statement.setString(1, username.getUsername());
			ResultSet resultado = statement.executeQuery();	
				
				return listOfTweet(resultado);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new LinkedList<Tweet>();
		
	}

	
	public Collection<Tweet> getAllTweets(User usuario) {
		
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select * from tweet where author = ? order by id_tweet desc");

			statement.setString(1, usuario.getUsername());
			ResultSet resultado = statement.executeQuery();	
				
				return listOfTweet(resultado);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}


	public int getNumerOfTweets(User usuario){
		Collection<Tweet> allTweets = getAllTweets(usuario);
		return allTweets == null ? 0 : allTweets.size();


	}

	public Collection<Tweet> getAllTweetsMentionMe(User user) {
		
		
		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connectionManager
					.prepareStatement("select * from tweet where mentions = ? order by id_tweet desc");

			statement.setString(1, user.getUsername());
			ResultSet resultado = statement.executeQuery();	
				
				return listOfTweet(resultado);
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
		

	public Collection<Tweet> getTweetsHilo(User user) {
		Collection<Tweet> allTweets = DaoFactory.getInstance().getTweetDao().findAll();
		List<Tweet> tweetMentionMeToReply = new LinkedList<Tweet>();
		
		for (Tweet oneTweet : allTweets) {
			if(oneTweet.getText().contains(user.getUsername()) || oneTweet.getText().contains((CharSequence) oneTweet.getAuthor().getUsername())){
				tweetMentionMeToReply.add(oneTweet);
			}
		}
		return tweetMentionMeToReply;
	}
	
	public void insertTweet(Tweet newTweet) {
		

		try {
		    Connection connectionManager = ConnectionProvider.getConnection();
			
		    if(newTweet.getMentions()!= null){
		    	
				PreparedStatement statement = connectionManager
						.prepareStatement("insert into tweet (tweet_description, author, mentions ) values(?,?,?)");
	
				
				statement.setString(1, newTweet.getText());
				statement.setString(2, newTweet.getAuthor().getUsername());
				statement.setString(3, newTweet.getMentions().getUsername());
				statement.executeUpdate();	

		    }
		    else{
		    	
				PreparedStatement statement = connectionManager
						.prepareStatement("insert into tweet (tweet_description, author ) values(?,?)");
				
				statement.setString(1, newTweet.getText());
				statement.setString(2, newTweet.getAuthor().getUsername());
				statement.executeUpdate();			
		    }
		    
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	
	
	private Collection<Tweet> listOfTweet (ResultSet resultado) throws SQLException{		
		Collection<Tweet> allTweets = new LinkedList<Tweet>(); 

		while(resultado.next()){
			Tweet tweet = new Tweet();
			tweet.setId(resultado.getLong(1));
			tweet.setText(resultado.getString(2));
			tweet.setAuthor(OurUserDao.getInstance().findByUsername(resultado.getString(3)));
			
			if(resultado.getString(4) != null)
				tweet.setMentions(OurUserDao.getInstance().findByUsername(resultado.getString(4)));
			else
				tweet.setMentions(null);
			
			allTweets.add(tweet);	
		}

		return allTweets;
	}	
}
