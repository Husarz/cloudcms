package my.test.apps.admin.rpc;

import java.util.ArrayList;

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
	
	ArrayList<AppEntity> getEntities(String clazz, String fild, String value);
	
	AppEntity getEntity(Key<AppEntity> key);
}
