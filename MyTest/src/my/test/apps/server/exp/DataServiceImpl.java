package my.test.apps.server.exp;

import java.util.ArrayList;
import java.util.List;

import my.test.apps.admin.rpc.DataService;
import my.test.apps.server.dao.*;
import my.test.apps.server.dao.exp.ObiectifyDaoExp;
import my.test.apps.shared.model.*;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	private static final long serialVersionUID = 1L;

	private UserService userService = UserServiceFactory.getUserService();
	
	@Override
	public boolean isAdmin() {
		if (userService.isUserAdmin()) 
			return true;
		return false;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList<AppEntity> queryEntities(String clazz) {
		ObiectifyDaoExp<AppEntity> obj = null;
		try {
			obj = new ObiectifyDaoExp(Class.forName(clazz));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (obj == null) 
			return null;
		return obj.getQuery();
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addEntities(ArrayList<AppEntity> list) {
		ObiectifyDaoExp<AppEntity> obj = null;
		obj = new ObiectifyDaoExp(list.get(0).getClass());
		if(obj!=null){
			obj.putAll(list);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void delEntities(ArrayList<AppEntity> list) {
		ObiectifyDaoExp<AppEntity> obj = null;
		obj = new ObiectifyDaoExp(list.get(0).getClass());
		if(obj!=null){
			obj.deleteAll(list);
		}	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public AppEntity getEntity(Key<AppEntity> key) {
		ObiectifyDaoExp<AppEntity> obj = null;
		try {
			obj = new ObiectifyDaoExp(Class.forName(key.getKind()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (obj == null) 
			return null;
		try {
			return obj.get(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
