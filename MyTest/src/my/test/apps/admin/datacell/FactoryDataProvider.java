package my.test.apps.admin.datacell;

import my.test.apps.admin.rpc.*;
import my.test.apps.shared.model.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.view.client.HasData;

public class FactoryDataProvider {
	
	private static final FactoryDataProvider Instance = new FactoryDataProvider();
	
	private static final AdminServicesAsync adminService = GWT.create(AdminServices.class);
	private static final PicasaServiceAsync picasaService = GWT.create(PicasaService.class);
	
	private static UserDataProvider userData;
	private static PicasaAlbumDataProvider picasaAlbum;
	private static AlbumDataProvider albumData;
	private static PicasaPhotoDataProvider picasaPhoto;
	private static PhotoDataProvider photoData;
	private static TextDataProvider textData;
	
	private FactoryDataProvider() {}

	public static FactoryDataProvider getInstance() {
		return Instance;
	}
	
	public static UserDataProvider getUserDataProvider(HasData<MyUser> display){
		if (userData == null) userData = new UserDataProvider(display, adminService);
		return userData;
	}
	
	public static PicasaAlbumDataProvider getPicasaAlbumDataProvider(HasData<Album> display){
		if (picasaAlbum == null) picasaAlbum = new PicasaAlbumDataProvider(display, picasaService);
		 return picasaAlbum;
	}
	
	public static AlbumDataProvider getAlbumDataProvider(HasData<Album> display){
		if (albumData == null) albumData = new AlbumDataProvider(display, adminService);
		return albumData;
	}
	
	public static PicasaPhotoDataProvider getPicasaPhotoDataProvider(HasData<Photo> display){
		if (picasaPhoto == null) picasaPhoto = new PicasaPhotoDataProvider(picasaService, display);
		return picasaPhoto;
	}
	
	public static PhotoDataProvider getPhotoDataProvider(HasData<Photo> display){
		if (photoData == null) photoData = new PhotoDataProvider(adminService, display);
		return photoData;
	}
	
	public static TextDataProvider getTextDataProvider(HasData<MyText> display){
		if (textData == null) textData = new TextDataProvider(display, adminService);
		return textData;
	}
}
