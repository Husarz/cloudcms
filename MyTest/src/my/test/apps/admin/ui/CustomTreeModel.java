package my.test.apps.admin.ui;

import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.MenuDataProvider;
import my.test.apps.admin.datacell.exp.EntityDataProvider;
import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.AppEntity;
import my.test.apps.shared.model.MapMenu;
import my.test.apps.shared.model.MyText;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.googlecode.objectify.Key;

public class CustomTreeModel implements TreeViewModel {

	// ListDataProvider<MapMenu> List;
	MenuDataProvider menuData = FactoryDataProvider.getMenuDataProvider(null);
	AdminServicesAsync service = FactoryDataProvider.getAdminservice();
	
//	@SuppressWarnings("unchecked")
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value instanceof MapMenu) {
			MapMenu node = (MapMenu) value;
			
			// add empty menu this field give us posibility to create new menu
			MapMenu empty = new MapMenu();
			empty.setParent(node.getId());
			empty.setMap("");
			
			ListDataProvider<MapMenu> data = menuData.getListData(node.getId());
			data.getList().add(empty);
			
//			EditTextCell cell = new EditTextCell();
			
//			EditTextCelll<MapMenu> cell = new EditTextCelll<MapMenu>(){};
			Cell<MapMenu> cell = new AbstractCell<MapMenu>() {

				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						MapMenu value, SafeHtmlBuilder sb) {
					if (value != null) {
						sb.appendEscaped(value.getMap());
					}
				}
			};
			if (data.getList().size() == 0 ){
				
				@SuppressWarnings("unchecked")
				Key<AppEntity> key = (Key<AppEntity>) node.getObjKey();
				if (key == null) return null;
				
				ListDataProvider<AppEntity> datalist = new EntityDataProvider(FactoryDataProvider.getDataservice(), key).getListData();
				Cell<AppEntity> cellText = new AbstractCell<AppEntity>() {
					
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							AppEntity value, SafeHtmlBuilder sb) {
						sb.appendEscaped(value.getName());	
					}
				};
				return new DefaultNodeInfo<AppEntity>(datalist, cellText);
				
//				if (key.getKind().equalsIgnoreCase("MyText")) {
//					
//					ListDataProvider<MyText> dataText = FactoryDataProvider.getEntitiesDataProvider(MyText.class).getListData();
//					
//					Cell<MyText> cellText = new AbstractCell<MyText>() {
//						
//						@Override
//						public void render(
//								com.google.gwt.cell.client.Cell.Context context,
//								MyText value, SafeHtmlBuilder sb) {
//							sb.appendEscaped(value.getTitle());
//							
//						}
//					};
//					
//					return new DefaultNodeInfo<MyText>(dataText, cellText);	
//				}
//				if (key.getKind().equalsIgnoreCase("Album")){
//					return new DefaultNodeInfo<Album>(data, cell);
//				}
//				Class<T> clazz =  Class.forName(key.getKind());
//				ListDataProvider<T> dataText = FactoryDataProvider.getEntitiesDataProvider(clazz).getListData();

				//				ListDataProvider<AppEntity> dataText = 
				
				
				
//				return DefaultNodeInfo<String>();
			}
			
			ProvidesKey<MapMenu> keyProvider = new ProvidesKey<MapMenu>(){

				@Override
				public Object getKey(MapMenu item) {
					return (item == null) ? null : item.getId();
				}
				
			};
			
			/**
			 * add selection model to mapmenu data
			 */
			SingleSelectionModel<MapMenu> select = new SingleSelectionModel<MapMenu>(keyProvider);
			
			/**
			 * add valueUpdater for update DB objetify.
			 */
			ValueUpdater<MapMenu> valueUpdater = new ValueUpdater<MapMenu>() {
				
				@Override
				public void update(MapMenu value) {
					//TODO
			//		value.setMap(map);
					
				}
			};
			
			return new DefaultNodeInfo<MapMenu>(data, cell, select, valueUpdater);
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		if (value instanceof MapMenu)
			return false;
		return true;
	}

}
