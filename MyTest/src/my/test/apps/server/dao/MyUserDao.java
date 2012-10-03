package my.test.apps.server.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ResourceNotFoundException;
import com.google.gdata.util.ServiceException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import my.test.apps.shared.model.MyUser;

public class MyUserDao extends ObjectifyGenericDao<MyUser> {

	private static final Logger LOG = Logger.getLogger(MyUser.class.getName());
	
	static{
		ObjectifyService.register(MyUser.class);
	}
	
	public MyUserDao() {
		super(MyUser.class);
	}
	
	public Key<MyUser> addUser(String email){
		
		MyUser user = getByProperty("emailAddress", email);
		if (user == null) {
			user = new MyUser(email);
			
			setService(user);
			Key<MyUser> key = put(user);
		
			return key;
		}
		return null;
	}
	
	public MyUser get(String email) throws EntityNotFoundException{
		return ofy().get(MyUser.class, email);
	}
	
	public List<String> getemails(){
		 List<String> listId = new ArrayList<String>();
		 Query<MyUser> q = ofy().query(MyUser.class);
		 for (MyUser user: q){
			 listId.add(user.getEmailAddress());
		 }
		 return listId;
	}
	
	public List<MyUser> getUsers(){
		Query<MyUser> q = ofy().query(MyUser.class);
		return asList(q);
	}
	
	public void deleteUsers(Iterable<String> email){
		List<Key<MyUser>> keys = new ArrayList<Key<MyUser>>();
		for (String mail : email){
			keys.add(new Key<MyUser>(MyUser.class, mail));
		}
		deleteKeys(keys);
	}
	
	private void setService(MyUser user) {
//		URL feedUrl;
//		PicasawebService myService;
//		user.setService("no_service");
		try {

			// Create a new Picasa Web Albums service
			PicasawebService myService = new PicasawebService("My Application");
			// myService.setUserCredentials("apilat@gmail.com","Adi82PilP");

			// Get a list of all entries
			URL metafeedUrl = new URL(
					"http://picasaweb.google.com/data/feed/api/user/"
							+ user.getEmailAddress() + "?kind=album");
			UserFeed resultFeed = myService
					.getFeed(metafeedUrl, UserFeed.class);
			List<GphotoEntry> entries = resultFeed.getEntries();
			user.setService("picasa");

		} catch ( ResourceNotFoundException e){
			user.setService("no_service");
			return;
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
