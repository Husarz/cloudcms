package my.test.apps.admin.datacell;

import my.test.apps.admin.rpc.AdminServicesAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.googlecode.objectify.Key;

public class EntityDataProvider<T> extends AsyncDataProvider<T>{

	final AdminServicesAsync service;
	final HasData<T> display;
	Key<T> key;
	
	public EntityDataProvider(HasData<T> display,
			AdminServicesAsync adminservice) {
		super();
		this.service = adminservice;
		this.display = display;
	}

	@Override
	protected void onRangeChanged(HasData<T> display) {
//		service.getEntity(key, new AsyncCallback<T>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(T result) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
	}

}
