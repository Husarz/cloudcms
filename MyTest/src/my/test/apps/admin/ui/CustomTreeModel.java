package my.test.apps.admin.ui;

import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.MenuDataProvider;
import my.test.apps.shared.model.MapMenu;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class CustomTreeModel implements TreeViewModel {

	// ListDataProvider<MapMenu> List;
	MenuDataProvider menuData = FactoryDataProvider.getMenuDataProvider(null);

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
				if (node.getObjKey().getKind().equalsIgnoreCase("MyText")){
					//TODO
//					return DefaultNodeInfo<String>(, new TextCell());
				}
				
				
//				return DefaultNodeInfo<String>();
			}
				
			return new DefaultNodeInfo<MapMenu>(data, cell);
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}
