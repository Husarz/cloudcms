package my.test.apps.admin.rpc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dataservice")
//@RemoteServiceRelativePath("manage")
public interface DataService extends RemoteService{
	
	boolean isAdmin();
	
	ArrayList<Serializable> queryEntities(String clazz);
	
	void addAlbum(Album album);
}
