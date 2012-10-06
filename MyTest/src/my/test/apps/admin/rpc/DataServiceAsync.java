package my.test.apps.admin.rpc;

import java.util.ArrayList;
import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.Key;

//@SuppressWarnings("hiding")
public interface DataServiceAsync {
	
	void isAdmin(AsyncCallback<Boolean> callback);
	void queryEntities(String clazz, AsyncCallback<ArrayList<AppEntity>> asyncCallback);
	void addEntities(ArrayList<AppEntity> list, AsyncCallback<Void> callback);
	void delEntities(ArrayList<AppEntity> list, AsyncCallback<Void> callback);
	void getEntity(Key<AppEntity> key, AsyncCallback<AppEntity> callback);
	
}
