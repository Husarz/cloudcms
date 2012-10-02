package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.MapMenu;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

public class MenuDataProvider extends AsyncDataProvider<MapMenu> {

	final AdminServicesAsync service;
	HasData<MapMenu> display;
	
	List<MapMenu> listMenu;

//	Map<Long, MapMenu> mapMenu;

	public MenuDataProvider(AdminServicesAsync service) {
		super();
		this.service = service;
		getListMenu();
//		mapMenu = new HashMap<Long, MapMenu>();
	}
	
	public MenuDataProvider(AdminServicesAsync service, HasData<MapMenu> display) {
		super();
		this.service = service;
		this.display = display;
//		mapMenu = new HashMap<Long, MapMenu>();
	}

	@Override
	protected void onRangeChanged(HasData<MapMenu> display) {
		if(display!=null)
		service.getAllMapMenu(new AsyncCallback<List<MapMenu>>() {

			@Override
			public void onSuccess(List<MapMenu> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});

	}
//
	protected void getListMenu() {

			service.getAllMapMenu(new AsyncCallback<List<MapMenu>>() {

				@Override
				public void onSuccess(List<MapMenu> result) {
						listMenu = result;
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
	}
	
	public ListDataProvider<MapMenu> getListData(Long id){
		
		if (listMenu.size() == 0 || listMenu == null) 
			return null;
		ListDataProvider<MapMenu> data = new ListDataProvider<MapMenu>();
		List<MapMenu> list = data.getList();
		for (MapMenu entry : listMenu){
	
			if (entry.getParent().equals(id))
				list.add(entry);
		}
		if(list.size()==0)
			return null;
		return data;
	}
//
//	public Map<Long, MapMenu> getMapMenu() {
//		getListMenu();
//		return mapMenu;
//	}

}
