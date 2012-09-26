package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.PicasaServiceAsync;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class PicasaPhotoDataProvider extends AsyncDataProvider<Photo>{

	final PicasaServiceAsync service;
	final HasData<Photo> display;
	Album album;
	
	public PicasaPhotoDataProvider(PicasaServiceAsync service, HasData<Photo> display) {
		super();
		this.display = display;
		this.service = service;
	}
	
	@Override
	protected void onRangeChanged(HasData<Photo> display) {
		if (album != null)
		service.getPhotosAlbum(album, new AsyncCallback<List<Photo>>() {
			
			@Override
			public void onSuccess(List<Photo> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie dal zdjec");
			}
		});	
	}
	
	public void getPhotos(Album album){
		this.album = album;
		onRangeChanged(display);
	}

}
