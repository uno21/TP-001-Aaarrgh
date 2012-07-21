package t4p.persistence;

import java.util.Collection;

import t4p.model.Tweet;


public class TweetDaoMock implements TweetDao {

	public Collection<Tweet> findAll() {
		return MockDataBase.getInstance().tweets.values();
	}

	public Tweet findById(int id) {
		return MockDataBase.getInstance().tweets.get(id);
	}

	public boolean delete(Tweet tweet) {
		if (MockDataBase.getInstance().tweets.containsValue(tweet)) {
			MockDataBase.getInstance().tweets.remove(tweet.getId());
			return true;
		}
		return false;
	}

	public void insert(Tweet tweet) {
		MockDataBase.getInstance().tweets.put(tweet.getId(), tweet);
	}

	public void update(Tweet tweet) {
		MockDataBase.getInstance().tweets.put(tweet.getId(), tweet);
	}

}
