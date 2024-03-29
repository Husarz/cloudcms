package my.test.apps.admin;

import java.util.Date;
import java.util.List;

import my.test.apps.shared.model.*;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.event.shared.EventBus;


public interface Main {
	Main INST = GWT.create(Main.class);
	
	IsWidget getMainWidget();
	EventBus getEventBus();
	Dictionary getInfo();

	<T> void setTabView(T view);
	MenuEntry getMenuEntry();
	UserEntry getUserEntry();
	AlbumEntry getAlbumEntry();
	PicasaEntry getPicasaEntry();
	PhotoEntry getPhotoEntry();
	TextEntry getTextEntry();
	TextFeed getTextFeed();
	EntityEntry getEntityEntry();
	
	PhotoGallery getPhotoGallery();
	
	public interface EntityEntry{
		void addEntities(Iterable<? extends AppEntity> e);
		void delEntities(Iterable<? extends AppEntity> e);
		List<? extends AppEntity> getEntities();
	}
	
	public interface VisiblePanel{
		void setVisiblePanel(boolean visible);
	}
	
	public interface MenuEntry extends VisiblePanel{
		void showMenu();
		void addNode(MapMenu node);
		void delNode(MapMenu node);
		void updateNode(MapMenu node);
		List<MapMenu> getMapMenu();
	}
	
	public interface UserEntry extends VisiblePanel{
		void addUser(String email);
		void delUser(MyUser user);
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
		
	public interface TextEntry {
		void showTexts(String email);
		void showTexts();
		void getText(Date date);
		void addText(String text);
	}
	
	public interface TextFeed{
		
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
