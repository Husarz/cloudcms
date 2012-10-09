package my.test.apps.admin.rpc;

<<<<<<< HEAD
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> d1c34a83a8b74b66a19b5893522a41fbd028e6c7

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.objectify.Key;

@RemoteServiceRelativePath("dataservice")
//@SuppressWarnings("hiding")
public interface DataService extends RemoteService{
	
	boolean isAdmin();
	ArrayList<AppEntity> queryEntities(String clazz);
	void addEntities(ArrayList<AppEntity> list);
	void delEntities(ArrayList<AppEntity> list);
//	void delEntities(ArrayList<AppEntity> list);
	
<<<<<<< HEAD
	ArrayList<Serializable> queryEntities(String clazz);
	
	void addAlbum(Album album);
=======
	ArrayList<AppEntity> getEntities(String clazz, String fild, String value);
	
	AppEntity getEntity(Key<AppEntity> key);
>>>>>>> d1c34a83a8b74b66a19b5893522a41fbd028e6c7
}
