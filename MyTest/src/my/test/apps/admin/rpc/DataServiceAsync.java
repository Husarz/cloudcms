package my.test.apps.admin.rpc;

<<<<<<< HEAD
import java.io.Serializable;
=======
>>>>>>> d1c34a83a8b74b66a19b5893522a41fbd028e6c7
import java.util.ArrayList;
import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.Key;

//@SuppressWarnings("hiding")
public interface DataServiceAsync {
	
<<<<<<< HEAD
	void queryEntities(String clazz, AsyncCallback<ArrayList<Serializable>> callback);
	
	void isAdmin(AsyncCallback<Boolean> callback);

	void addAlbum(Album album, AsyncCallback<Void> callback);
=======
	void isAdmin(AsyncCallback<Boolean> callback);
	void queryEntities(String clazz, AsyncCallback<ArrayList<AppEntity>> asyncCallback);
	void addEntities(ArrayList<AppEntity> list, AsyncCallback<Void> callback);
	void delEntities(ArrayList<AppEntity> list, AsyncCallback<Void> callback);
	
	void getEntities(String clazz, String fild, String value,
			AsyncCallback<ArrayList<AppEntity>> callback);
	
	void getEntity(Key<AppEntity> key, AsyncCallback<AppEntity> callback);
	
>>>>>>> d1c34a83a8b74b66a19b5893522a41fbd028e6c7
}
