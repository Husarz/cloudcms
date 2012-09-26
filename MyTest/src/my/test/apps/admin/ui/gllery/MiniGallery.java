package my.test.apps.admin.ui.gllery;

import java.util.List;

import my.test.apps.admin.Main.PhotoGallery;
import my.test.apps.shared.model.Photo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class MiniGallery extends Composite implements PhotoGallery{

	private static MiniGalleryUiBinder uiBinder = GWT
			.create(MiniGalleryUiBinder.class);

	interface MiniGalleryUiBinder extends UiBinder<Widget, MiniGallery> {}
	
	interface MyStyle extends CssResource{
		String panel();
	}

//	@UiField Grid grid;
	@UiField HTMLPanel panel;
	@UiField FocusPanel sp;
	
	Iterable<Photo> photos;
	final Panel parent;
	
	public MiniGallery() {
		initWidget(uiBinder.createAndBindUi(this));
		
		parent = RootLayoutPanel.get();
		parent.add(this);
	}
	
	public MiniGallery(Panel parent){
		this.parent = parent;
		initWidget(uiBinder.createAndBindUi(this));
		
		parent.add(this);
	}
	
	@UiHandler("sp")
	void spEvent(ClickEvent e){
		panel.clear();
		closeGallery();
	}
	
	public void closeGallery(){
		parent.remove(this);
	}

	@Override
	public void addPhotos(List<String> urls) {
		for (String url: urls)
			addPhoto(url);
	}

	@Override
	public void addPhoto(String url) {
		panel.add(new Image(url));
	}

	@Override
	public void clearPhotos() {
		panel.clear();
	}

	@Override
	public void addPhotos(Iterable<Photo> photos) {
		this.photos = photos;
		for (Photo photo: photos){
			String url = photo.getUrls().get(2); //mini photos
			addPhoto(url);
		}
		
	}

	
//	@Override
//	public void addPhotos(Album album) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public IsWidget getPanelWidget() {
//		return this;
//	}

}
