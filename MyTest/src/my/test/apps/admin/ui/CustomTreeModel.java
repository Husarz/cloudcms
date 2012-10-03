package my.test.apps.admin.ui;

import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.MenuDataProvider;
import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.MapMenu;
import my.test.apps.shared.model.MyText;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
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
			ListDataProvider<MapMenu> data = menuData.getListData(node.getId());
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
				
				Key<T> key = (Key<T>) node.getObjKey();
				if (key == null) return null;
				if (key.getKind().equalsIgnoreCase("MyText")) {
					
					ListDataProvider<MyText> dataText = FactoryDataProvider.getEntitiesDataProvider(MyText.class).getListData();
					
					Cell<MyText> cellText = new AbstractCell<MyText>() {
						
						@Override
						public void render(
								com.google.gwt.cell.client.Cell.Context context,
								MyText value, SafeHtmlBuilder sb) {
							sb.appendEscaped(value.getTitle());
							
						}
					};
					
					return new DefaultNodeInfo<MyText>(dataText, cellText);	
				}
//				if (key.getKind().equalsIgnoreCase("Album")){
//					return new DefaultNodeInfo<Album>(data, cell);
//				}
//				Class<T> clazz =  Class.forName(key.getKind());
//				ListDataProvider<T> dataText = FactoryDataProvider.getEntitiesDataProvider(clazz).getListData();
				
//				return DefaultNodeInfo<String>();
			}
				
			return new DefaultNodeInfo<MapMenu>(data, cell);
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
