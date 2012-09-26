package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Unindexed;

public class MyText implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id Long id;
	String title;
	@Unindexed String text;
	Date date;
	String owenr;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOwenr() {
		return owenr;
	}

	public void setOwenr(String owenr) {
		this.owenr = owenr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
