package my.test.apps.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import my.test.apps.shared.model.MapMenu;

public class MapMenuDao extends ObjectifyGenericDao<MapMenu>{

	static{
		ObjectifyService.register(MapMenu.class);
	}
	
	MapMenu root;
	Key<MapMenu> key;
	
	public MapMenuDao() {
		super(MapMenu.class);
		root = getByProperty("parent", null);
		if (root == null){
			MapMenu entity = new MapMenu();
			entity.setDescrip("root Menu");
			entity.setMap("ROOT");
			 //= entity.setObjKey(objKey);
			key = put(entity);
			try {
				root = get(key);
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			root.setObjKey(key);
			put(root);
		}
		
	}
	
	/**
	 * @return Linst menu where in list(0) will be a root
	 */
	public List<MapMenu> getAll(){
		List<MapMenu>  list = new ArrayList<MapMenu>();
		// ROOT on the first place
		list.add(root);
		Query<MapMenu> q = ofy().query(MapMenu.class);
		list.addAll(asList(q));
		return list;
	}
	public void addMap(){
		
	}

	public MapMenu getRoot() {
		return root;
	}
}
