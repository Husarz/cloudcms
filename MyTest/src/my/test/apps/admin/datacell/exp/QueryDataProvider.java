package my.test.apps.admin.datacell.exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.admin.rpc.DataServiceAsync;
import my.test.apps.shared.model.AppEntity;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class QueryDataProvider<T extends AppEntity> extends AsyncDataProvider<T>{

	final DataServiceAsync service;
	final HasData<T> display;
	final Class<T> clazz;

	public QueryDataProvider(HasData<T> display,
			DataServiceAsync dataservice, Class<T> clazz) {
		super();
		this.service = dataservice;
		this.display = display;
		this.clazz = clazz;
	}


	@Override
	public void onRangeChanged(HasData<T> display) {
		if (service != null)
		service.queryEntities(clazz.getName(), new AsyncCallback<ArrayList<AppEntity>>() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(ArrayList<AppEntity> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, (List<T>) result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("didnt show query");
				
			}
		});

	}
	
	public void delEntities (Iterable<T> entities){
		service.delEntities(asArrayList(entities), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("didnt del list");
				
			}
		});
	}
	
	public void addEntities (Iterable<T> entities){

		service.addEntities(asArrayList(entities), new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("didnt add list");
				
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void addEntity (T entity){
		addEntities( Arrays.asList(entity));
	}
	
	@SuppressWarnings("unchecked")
	public void delEntity (T entity){
		delEntities( Arrays.asList(entity));
	}
	
	private ArrayList<AppEntity> asArrayList(Iterable<T> entities){
		ArrayList<AppEntity> list = new ArrayList<AppEntity>(); //ArrayList<AppEntity>(entities)
		for (T t: entities){
			list.add(t);
		}
		return list;
	}

}
