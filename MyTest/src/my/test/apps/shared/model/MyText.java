package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.annotation.Unindexed;

@Entity
public class MyText implements AppEntity{

	private static final long serialVersionUID = 1L;
	
	@Id Long id;
	String title;
	@Unindexed String text;
	Date date;
	String owenr;

	public MyText() { } 
	
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

	@Override
	public String getName() {
		String str = getTitle();
		if (str==null||str.isEmpty())
			return "bez tytulu";
		return str;
	}

	@Override
	public String getType() {
		return "Text";
	}

	@Override
	public String getInfo() {
		return getOwenr();
	}

}
