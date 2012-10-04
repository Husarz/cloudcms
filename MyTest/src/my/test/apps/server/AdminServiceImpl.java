package my.test.apps.server;

import java.util.List;
import java.util.Map;

import my.test.apps.admin.rpc.AdminServices;
import my.test.apps.server.dao.*;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MapMenu;
import my.test.apps.shared.model.MyText;
import my.test.apps.shared.model.MyUser;
import my.test.apps.shared.model.Photo;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class AdminServiceImpl extends RemoteServiceServlet implements AdminServices{

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

	@Override
	public MyUser getMyUser(String email) {
	//	if (!userService.isUserAdmin()) return null;
		MyUser user = null;
		try {
			user = userDao.get(email);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<String> getUsersemail() {
		List<String> list = null;
		list = userDao.getemails();
		return list;
	}

	@Override
	public void addUser(String email) {
		userDao.addUser(email);
	}

	@Override
	public List<MyUser> getMyUsers() {
		return userDao.getUsers();
	}

	@Override
	public void deleteemails(Iterable<String> email) {
		userDao.deleteUsers(email);
		
	}

	@Override
	public void deleteUsers(Iterable<MyUser> users) {
		userDao.deleteAll(users);
		
	}

	@Override
	public List<Album> getAlbums() {
		return albumDao.getAlbums();
	}

	@Override
	public void addAlbum(Album album) {
		albumDao.addAlbum(album);
		
	}

	@Override
	public void delAlbum(Album album) {
		albumDao.delete(album);
		
	}

	@Override
	public List<Photo> getPhotos(Album album) {
		return photoDao.getPhotos(album);
	}

	@Override
	public void addPhotos(List<Photo> photos) {
		photoDao.addPhotos(photos);
	}

	@Override
	public void delPhotos(List<Photo> photos) {
		photoDao.deleteAll(photos);
	}

	@Override
	public void addPhotos(Album album) {
		photoDao.addPhotos(album);
	}

	@Override
	public List<MyText> getText(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteText(MyText text) {
		textDao.delete(text);
	}

	@Override
	public void addText(MyText text) {
		textDao.addText(text);
	}

	@Override
	public List<MyText> getText() {
		return textDao.getText();
	}

	@Override
	public List<MapMenu> getAllMapMenu() {
		return map.getAll();
	}
//
//	@Override
//	public <T> T getEntity(Key<T> key) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <T> Map<Key<T>, T> getMapEntities(Iterable<Key<T>> keys) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> List<T> queryEntities(String clazz) {
		ObjectifyGenericDao<T> obj = null;
		try {
			obj = new ObjectifyGenericDao(Class.forName(clazz));
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		if (obj == null) 
			return null;
		return obj.getQuery();
	}
//	private <V extends ObjectifyGenericDao<?>> V getClassDao(String clazz){
//		Class klazz;
//		if (clazz.equals("MyText")){
//			return (V) textDao;
//		}
//		if (clazz.equals("Album")){
//			return null;
//		}
//		if (clazz.equals("MyUser")){
//			return null;
//		}
//		if (clazz.equals("MapMenu")){
//			return null;
//		}
//		if (clazz.equals("Photo")){
//			return null;
//		}
//		
//		return null;
//	}
}
