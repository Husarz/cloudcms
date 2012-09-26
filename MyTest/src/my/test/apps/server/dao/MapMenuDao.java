package my.test.apps.server.dao;

import com.googlecode.objectify.ObjectifyService;

import my.test.apps.shared.model.MapMenu;

public class MapMenuDao extends ObjectifyGenericDao<MapMenu>{

	static{
		ObjectifyService.register(MapMenu.class);
	}
	
	public MapMenuDao() {
		super(MapMenu.class);
	}
	
	public void getMap(){
		
	}
	public void addMap(){
		
	}
}
