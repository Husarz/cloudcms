package my.test.apps.admin.rpc;

import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	
	<T> void queryEntities(String clazz, AsyncCallback<List<T>> callback);
	
	void isAdmin(AsyncCallback<Boolean> callback);
}
