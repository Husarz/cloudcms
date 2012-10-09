package my.test.apps.admin.datacell.exp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.rpc.DataServiceAsync;
import my.test.apps.shared.model.AppEntity;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import com.googlecode.objectify.Key;

public class EntityDataProvider extends AsyncDataProvider<AppEntity> {

	final DataServiceAsync service;
	Key<AppEntity> key;
	List<Key<AppEntity>> keys;
	
	ListDataProvider<AppEntity> listData = new ListDataProvider<AppEntity>();
	
	public EntityDataProvider(DataServiceAsync service , Key<AppEntity> key) {
		super();
		this.service = service;
		this.key = key;
	}
	
	public EntityDataProvider(List<Key<AppEntity>> keys) {
		super();
		this.service = FactoryDataProvider.getDataservice();
		this.keys = keys;
	}
	
	public EntityDataProvider(String clazz, String field, String value) {
		super();
		this.service = FactoryDataProvider.getDataservice();
		listByProperty(clazz, field, value);
	
	}
	
	@Override
	protected void onRangeChanged(HasData<AppEntity> display) {
		listData.getList().clear();
		service.getEntity(key, new AsyncCallback<AppEntity> (){
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(AppEntity result) {
				listData.getList().addAll(Arrays.asList(result));
				
			}
			
		});
		
	}
	
	private void listByProperty(final String clazz, final String field, final String value){
		listData.getList().clear();
		service.getEntities(clazz, field, value, new AsyncCallback<ArrayList<AppEntity>>() {
			
			@Override
			public void onSuccess(ArrayList<AppEntity> result) {
				if (result == null) Window.alert("null");
				listData.getList().addAll(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("didnt show listByProperty("+clazz+","+field+","+value+")");
			}
		});
	}

	public ListDataProvider<AppEntity> getListData() {
		return listData;
	}


}
