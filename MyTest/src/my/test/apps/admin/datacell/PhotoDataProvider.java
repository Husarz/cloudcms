package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.Main;
import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class PhotoDataProvider extends AsyncDataProvider<Photo> {

	final AdminServicesAsync service;
	final HasData<Photo> display;
	Album album;
	
	Main mainCtl = Main.INST;

	public PhotoDataProvider(AdminServicesAsync service, HasData<Photo> display) {
		super();
		this.service = service;
		this.display = display;
	}
	
	@Override
	protected void onRangeChanged(HasData<Photo> display) {
		if(album != null)
		service.getPhotos(album, new AsyncCallback<List<Photo>>() {
			
			@Override
			public void onSuccess(List<Photo> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void getPhotos(Album album){
		this.album = album;
		onRangeChanged(display);
	}
	
	public void addAllPhotos(Album album){
		service.addPhotos(album, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				// use eventbus and call refresheventalbum.
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
