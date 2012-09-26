package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.PicasaServiceAsync;
import my.test.apps.shared.model.Album;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class PicasaAlbumDataProvider extends AsyncDataProvider<Album> {

	final PicasaServiceAsync service;
	final HasData<Album> display;
	String email;

	public PicasaAlbumDataProvider(HasData<Album> display, PicasaServiceAsync service) {
		super();
		this.service = service;
		this.display = display;
	}
	
	@Override
	protected void onRangeChanged(HasData<Album> display) {
		if (email == null) return;
		service.getUserAlbums(email, new AsyncCallback<List<Album>>() {
			
			@Override
			public void onSuccess(List<Album> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie dal albumow");
				
			}
		});
	}
	public void getAlbums(String email){
		setEmail(email);
		onRangeChanged(display);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
