package my.test.apps.admin.datacell.exp;

import java.util.Arrays;

import my.test.apps.admin.rpc.DataServiceAsync;
import my.test.apps.shared.model.AppEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import com.googlecode.objectify.Key;

public class EntityDataProvider extends AsyncDataProvider<AppEntity> {

	final DataServiceAsync service;
	Key<AppEntity> key;
	
	ListDataProvider<AppEntity> listData = new ListDataProvider<AppEntity>();
	
	public EntityDataProvider(DataServiceAsync service , Key<AppEntity> key) {
		super();
		this.service = service;
		this.key = key;
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

	public ListDataProvider<AppEntity> getListData() {
		return listData;
	}


}
