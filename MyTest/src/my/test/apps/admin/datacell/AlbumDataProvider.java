package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.AdminServices;
import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MyUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class AlbumDataProvider extends AsyncDataProvider<Album>  {

	final AdminServicesAsync service;
	final HasData<Album> display;
	
	public AlbumDataProvider(HasData<Album> display, AdminServicesAsync service) {
		super();
		this.service = service;
		this.display = display;
	}
	
	@Override
	public void onRangeChanged(HasData<Album> display) {
		service.getAlbums(new AsyncCallback<List<Album>>() {
			
			@Override
			public void onSuccess(List<Album> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void addAlbum(Album album){
		service.addAlbum(album, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie dodał albumu Picasa");
				
			}
		});
	}
	
	public void delAlbum(Album album){
		service.delAlbum(album, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie usunal albumu");
				
			}
		});
	}
	
}
