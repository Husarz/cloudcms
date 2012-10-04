package my.test.apps.admin.datacell;

import my.test.apps.admin.rpc.*;
import my.test.apps.shared.model.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.view.client.HasData;

public class FactoryDataProvider {
	
	private static final FactoryDataProvider Instance = new FactoryDataProvider();
	
	private static final AdminServicesAsync adminService = GWT.create(AdminServices.class);
	private static final DataServiceAsync dataService = GWT.create(DataService.class);
	private static final PicasaServiceAsync picasaService = GWT.create(PicasaService.class);
	
	private static UserDataProvider userData;
	private static PicasaAlbumDataProvider picasaAlbum;
	private static AlbumDataProvider albumData;
	private static PicasaPhotoDataProvider picasaPhoto;
	private static PhotoDataProvider photoData;
	private static TextDataProvider textData;
	private static MenuDataProvider menuData;
	
	private FactoryDataProvider() {}

	public static FactoryDataProvider getInstance() {
		return Instance;
	}
	
	public static AdminServicesAsync getAdminservice() {
		return adminService;
	}

	public static DataServiceAsync getDataservice() {
		return dataService;
	}

	public static PicasaServiceAsync getPicasaservice() {
		return picasaService;
	}

	public static <T> EntityDataProvider<T> getEntityDataProvider(HasData<T> display){
		EntityDataProvider<T> entityData = new EntityDataProvider<T>(display, adminService);
		return entityData;
	}
	public static <T> EntitiesDataProvider<T> getEntitiesDataProvider(Class<T> clazz){
		EntitiesDataProvider<T> entityData = new EntitiesDataProvider<T>(clazz, adminService);
		return entityData;
	}
	
	public static <T> QueryDataProvider<T> getQueryDataProvider(HasData<T> display, Class<T> clazz){
		QueryDataProvider<T> queryData = new QueryDataProvider<T>(display, dataService, clazz);
		return queryData;
	}
	public static <T> QueryDataProvider<T> getQueryDataProvider1(HasData<T> display, Class<T> clazz){
		QueryDataProvider<T> queryData = new QueryDataProvider<T>(display, adminService, clazz);
		return queryData;
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
	
	public static MenuDataProvider getMenuDataProvider(HasData<MapMenu> display){
		if (display == null){
			if (menuData == null) menuData = new MenuDataProvider(adminService);
			return menuData;
		}
			// dont use ...
		if (menuData == null) menuData = new MenuDataProvider(adminService, display);
		return menuData;
	}
}
