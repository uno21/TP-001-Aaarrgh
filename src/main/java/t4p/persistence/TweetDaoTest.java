package t4p.persistence;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import t4p.model.Tweet;
import t4p.persistence.DaoFactory;

public class TweetDaoTest {

	@Test
	public void testQueTraigaLosTweets(){
		Collection<Tweet> todosLosTweets = DaoFactory.getInstance().getTweetDao().findAll();
		
		Assert.assertFalse(todosLosTweets.isEmpty());
		Assert.assertEquals(7, todosLosTweets.size());
	}
	
}
