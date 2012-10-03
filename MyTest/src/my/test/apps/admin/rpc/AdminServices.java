package my.test.apps.admin.rpc;

import java.util.List;
import java.util.Map;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.googlecode.objectify.Key;

@RemoteServiceRelativePath("manage")
public interface AdminServices extends RemoteService{
	
	<T> T getEntity(Key<T> key);
	<T> Map<Key<T>, T> getMapEntities(Iterable<Key<T>> keys);
	<T> List<T> queryEntities(Class<T> clazz);
	
	boolean isAdmin();
	MyUser getMyUser(String email);
	List<MyUser> getMyUsers();
	List<String> getUsersemail();
	void addUser(String email);
	void deleteemails(Iterable<String> email);
	void deleteUsers(Iterable<MyUser> users);
	
	List<Album> getAlbums();
	void addAlbum(Album album);
	void delAlbum(Album album);
	
	List<Photo> getPhotos(Album album);
	void addPhotos(Album album);
	void addPhotos(List<Photo> photos);
	void delPhotos(List<Photo> photos);
	
	List<MyText> getText(String email);
	List<MyText> getText();
	void addText(MyText text);
	void deleteText(MyText text);
	
	List<MapMenu> getAllMapMenu();
	
}
