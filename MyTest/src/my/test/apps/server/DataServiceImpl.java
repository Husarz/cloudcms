package my.test.apps.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.test.apps.admin.rpc.DataService;
import my.test.apps.server.dao.*;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MapMenu;
import my.test.apps.shared.model.MyText;
import my.test.apps.shared.model.MyUser;
import my.test.apps.shared.model.Photo;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserServiceFactory.getUserService();
	private MyUserDao userDao = new MyUserDao();
	private AlbumDao albumDao = new AlbumDao();
	private PhotoDao photoDao = new PhotoDao();
	private MyTextDao textDao = new MyTextDao();
	private MapMenuDao map = new MapMenuDao();
	@Override
	public boolean isAdmin() {
		if (userService.isUserAdmin()) 
			return true;
		return false;
	}
	
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	@Override
//	public <T> List<T> queryEntities(String clazz) {
//		ObjectifyGenericDao<T> obj = null;
//		try {
//			obj = new ObjectifyGenericDao(Class.forName(clazz));
//		} catch (ClassNotFoundException e) {
//			
//			e.printStackTrace();
//		}
//		if (obj == null) 
//			return null;
//		List<T> list = obj.getQuery();
//		return list;
//	}

	@Override
	public void addAlbum(Album album) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ArrayList<Serializable> queryEntities(String clazz) {
		ObjectifyGenericDao<?> obj = null;
		try {
			obj = new ObjectifyGenericDao(Class.forName(clazz));
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		if (obj == null) 
			return null;
//		List<T> list = obj.getQuery();
		return (ArrayList<Serializable>) obj.getQuery();
	}

}
