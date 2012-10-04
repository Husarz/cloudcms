package my.test.apps.admin.datacell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import my.test.apps.admin.rpc.AdminServicesAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.googlecode.objectify.Key;

public class EntitiesDataProvider<T> extends AsyncDataProvider<T>{

	final AdminServicesAsync service;
	final HasData<T> display;
	Class<T> clazz;
	List<Key<T>> keys;
	ListDataProvider<T> list = new ListDataProvider<T>();
	Map<Key<T>, T> result;
	
	public EntitiesDataProvider(AdminServicesAsync adminservice, HasData<T> display) {
		super();
		this.service = adminservice;
		this.display = display;
		keys = new ArrayList<Key<T>>();
		getFeed();
	}

	public EntitiesDataProvider(Class<T> clazz, AdminServicesAsync adminservice) {
		super();
		this.clazz = clazz;
		this.service = adminservice;
		this.display = null;
		keys = new ArrayList<Key<T>>();
		getFeed();
	}

	@Override
	protected void onRangeChanged(HasData<T> display) {
//		if(keys.size()>0 && display != null)
//		service.getMapEntities(keys, new AsyncCallback<Map<Key<T>, T>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Map<Key<T>, T> result) {
//				EntitiesDataProvider.this.result = result;
////				List<T> list = new ArrayList<T>(result.values());
////				updateRowCount(list.size(), true);
////				updateRowData(0, list);
//			}
//		});
	}
	protected void getFeed() {
//		if(keys.size()>0)
//		service.getMapEntities(keys, new AsyncCallback<Map<Key<T>, T>>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onSuccess(Map<Key<T>, T> result) {
//				EntitiesDataProvider.this.result = result;
//				EntitiesDataProvider.this.list.getList().addAll(result.values());
////				List<T> list = new ArrayList<T>(result.values());
////				updateRowCount(list.size(), true);
////				updateRowData(0, list);
//			}
//		});
	}
	
		
	public ListDataProvider<T> getListData(){
//		getFeed();
		return list;
	}

	public Map<Key<T>, T> getResult() {
//		getFeed();
		return result;
	}

	public List<Key<T>> getKeys() {
		return keys;
	}

	public void setKeys(List<Key<T>> keys) {
		this.keys = keys;
	}
	public void setKeys(Key<T> key) {
		keys.clear();
		this.keys.add(key);
	}

}
