package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.admin.rpc.DataServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class QueryDataProvider<T> extends AsyncDataProvider<T>{

	final DataServiceAsync service;
	final HasData<T> display;
	final AdminServicesAsync service1;
	final Class<T> clazz;

	public QueryDataProvider(HasData<T> display,
			DataServiceAsync dataservice, Class<T> clazz) {
		super();
		this.service = dataservice;
		this.display = display;
		service1 = null;
		this.clazz = clazz;
	}
	public QueryDataProvider(HasData<T> display,
			AdminServicesAsync service, Class<T> clazz) {
		super();
		this.service = null;
		this.display = display;
		service1 = service;
		this.clazz = clazz;
	}

	@Override
	public void onRangeChanged(HasData<T> display) {
		if (service != null)
		service.queryEntities(clazz.getName(), new AsyncCallback<List<T>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<T> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
				
			}
		});
		if (service1 != null)
			service1.queryEntities(clazz.getName(), new AsyncCallback<List<T>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(List<T> result) {
					updateRowCount(result.size(), true);
					updateRowData(0, result);
					
				}
			});
	}
	
	public void delEntities (Iterable<T> entities){
		//TODO
	}
	public void addEntities (Iterable<T> entities){
		//TODO
	}

}
