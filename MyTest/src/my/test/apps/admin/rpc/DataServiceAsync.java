package my.test.apps.admin.rpc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DataServiceAsync {
	
	void queryEntities(String clazz, AsyncCallback<ArrayList<Serializable>> callback);
	
	void isAdmin(AsyncCallback<Boolean> callback);

	void addAlbum(Album album, AsyncCallback<Void> callback);
}
