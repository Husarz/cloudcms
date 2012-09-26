package my.test.apps.admin;

import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;


public interface Main {
	Main INST = GWT.create(Main.class);
	
	IsWidget getMainWidget();
	EventBus getEventBus();
	
	
	
	<T> void setTabView(T view);
	UserEntry getUserEntry();
	AlbumEntry getAlbumEntry();
	PicasaEntry getPicasaEntry();
	PhotoEntry getPhotoEntry();
	
	PhotoGallery getPhotoGallery();
	
	public interface VisiblePanel{
		void setVisiblePanel(boolean visible);
	}
	
	public interface UserEntry extends VisiblePanel{
		void addUser(String email);
		void delUser(String email);
		void showUsers();
		List<MyUser> getUsers();
	}
	
	public interface AlbumEntry extends VisiblePanel{
		void addAlbums(Album album);
		void delAlbum(Album album);
	}
	
	public interface PicasaEntry extends VisiblePanel{
		void addPicasaAlbum(Album album);
		void showUserAlbum(String email);
		void addPicasaPhotos(Iterable<Photo> photos);
	}
	public interface PhotoEntry extends VisiblePanel{
		void showPicasaPhotos(Album album);
		void showPhotos(Album album);
		void addPhotos(Album album);
		void addPhotos(Iterable<Photo> photos);
		void delPhotos(Iterable<Photo> photos);
	}
		
	public interface PhotoGallery{
//		IsWidget getPanelWidget();
		void addPhotos(List<String> urls);
		void addPhoto(String url);
		void clearPhotos();
		void addPhotos(Iterable<Photo> photos);
//		void addPhotos(Album album);
	}
}
