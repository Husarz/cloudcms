package my.test.apps.admin.rpc;

import java.util.List;
import java.util.Map;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.Key;

public interface AdminServicesAsync {

//	<T> void getEntity(Key<T> key, AsyncCallback<T> callback);
//	<T> void getMapEntities(Iterable<Key<T>> keys, AsyncCallback<Map<Key<T>, T>> callback);
	
	
	<T> void queryEntities(String clazz, AsyncCallback<List<T>> callback);
	
	void isAdmin(AsyncCallback<Boolean> callback);
	void getMyUser(String email, AsyncCallback<MyUser> callback);
	void getUsersemail(AsyncCallback<List<String>> callback);
	void addUser(String email, AsyncCallback<Void> callback);
	void getMyUsers(AsyncCallback<List<MyUser>> callback);
	void deleteemails(Iterable<String> email, AsyncCallback<Void> callback);
	void deleteUsers(Iterable<MyUser> users, AsyncCallback<Void> callback);
	
	void getAlbums(AsyncCallback<List<Album>> callback);
	void addAlbum(Album album, AsyncCallback<Void> callback);
	void delAlbum(Album album, AsyncCallback<Void> callback);
	
	void getPhotos(Album album, AsyncCallback<List<Photo>> callback);
	void addPhotos(Album album, AsyncCallback<Void> callback);
	void delPhotos(List<Photo> photos, AsyncCallback<Void> callback);
	void addPhotos(List<Photo> photos, AsyncCallback<Void> callback);
	
	void getText(String email, AsyncCallback<List<MyText>> callback);
	void deleteText(MyText text, AsyncCallback<Void> callback);
	void addText(MyText text, AsyncCallback<Void> callback);
	void getText(AsyncCallback<List<MyText>> callback);
	
	void getAllMapMenu(AsyncCallback<List<MapMenu>> callback);
	
	
}
