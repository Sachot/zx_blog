package domain;

import java.util.ArrayList;
import java.util.List;

public class Article {

	private String title;
	private String content;
	private String comment;
	List<String> comments = new ArrayList<String>();
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getComment() {
		return comment;
	}
	
	public List<String> getCommentList() {
		return comments;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
		this.addComments();
	}
	
	public void addComments() {
		comments.add(comment);
	}
	
	
}
