package t4p.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Tweet {
	private long id;
	private String text;
	private Date timestamp;
	private Tweet previous;
	private User author;
	private User mention;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Tweet getPrevious() {
		return previous;
	}
	public void setPrevious(Tweet previous) {
		this.previous = previous;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public User getMentions() {
		return mention;
	}
	public void setMentions(User mention) {
		this.mention = mention;
	}
//	public boolean addMentions(User e) {
	//	return mention.add(e);
//	}
	
}
