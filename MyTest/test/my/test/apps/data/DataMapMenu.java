package my.test.apps.data;

import java.util.ArrayList;

import my.test.apps.shared.model.MapMenu;

public class DataMapMenu {

	ArrayList<MapMenu> list = new ArrayList<MapMenu>();
	
	public DataMapMenu() {
		
	}
	
	
	private MapMenu getRootMap(){
		return null;

	}
	
	public ArrayList<MapMenu> getMaps(){
		
		
		return list;
	}
}
