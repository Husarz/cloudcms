package my.test.apps.server.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import my.test.apps.shared.model.MyText;

public class MyTextDao extends ObjectifyGenericDao<MyText> {

	static{
		ObjectifyService.register(MyText.class);
	}
	
	public MyTextDao() {
		super(MyText.class);
	}


	public void addText(MyText text){
		put(text);
	}
	
	public List<MyText> getText(){
		return asList(ofy().query(MyText.class));
	}
}
