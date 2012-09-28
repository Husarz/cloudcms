package my.test.apps.admin;

import my.test.apps.admin.bundle.Resources;
import my.test.apps.admin.ui.*;
import my.test.apps.admin.ui.gllery.*;
import my.test.apps.admin.ui.text.TextManager;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

class MainView extends Composite implements Main{

	interface MyStyle extends CssResource{
		String panel();
	}
	
	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	final EventBus EVENTBUS;
	Dictionary info;
	PicasaPanel picasa;
	AlbumsPanel album;
	UserPanel user;
	PhotoPanel photo;
	MiniGallery gallery;
	TextPanel textPanel;
	TextManager text;
	@UiField HTMLPanel mainPanel;
	@UiField Button usersTab, albumsTab, picasaTab, photoTab, textPanelTab, textTab;
	
	public MainView() {
		EVENTBUS = new SimpleEventBus();
		Resources.INSTANCE.getadmin().ensureInjected();
		info = Dictionary.getDictionary("info");
		initWidget(uiBinder.createAndBindUi(this));
	}
	
//	private final static MainView Instance = new MainView();

//	public static Main getInstance() {
//		return Instance;
//	}

	@UiHandler("usersTab")
	void usersClick(ClickEvent e) {
		getUserEntry();
		visible(user);
	}
	@UiHandler("albumsTab")
	void albumsClick(ClickEvent e) {
		getAlbumEntry();
		visible(album);
	}
	@UiHandler("picasaTab")
	void picasaClick(ClickEvent e) {
		getPicasaEntry();
		visible(picasa);
	}
	@UiHandler("photoTab")
	void flicerClick(ClickEvent e) {
		getPhotoEntry();
		visible(photo);
	}
	
	@UiHandler("textPanelTab")
	void textPanelTabClick(ClickEvent e) {
		getTextEntry();
		visible(textPanel);
	}
	
	@UiHandler("textTab")
	void textClick(ClickEvent e) {
		getTextFeed();
	}
	
	@Override
	public IsWidget getMainWidget() {
		return asWidget();
	}
	
	@Override
	public Dictionary getInfo() {
		return info;
	}

	private void visible(HasVisibility panel){
		if (panel.isVisible()) panel.setVisible(false);
		else panel.setVisible(true);
	}

	@Override
	public EventBus getEventBus() {
		return EVENTBUS;
	}

	@Override
	public <T> void setTabView(T view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserEntry getUserEntry() {
		if (user == null){
			user = new UserPanel();
			mainPanel.add(user);
			user.setVisible(false);
		}
		return user;
	}

	@Override
	public AlbumEntry getAlbumEntry() {
		if (album == null){
			album = new AlbumsPanel();
			mainPanel.add(album);
			album.setVisible(false);
		}
		return album;
	}

	@Override
	public PicasaEntry getPicasaEntry() {
		if (picasa == null){
			picasa = new PicasaPanel();
			mainPanel.add(picasa);
			picasa.setVisible(false);
		}
		return picasa;
	}

	@Override
	public PhotoEntry getPhotoEntry() {
		if (photo == null) {
			//TODO
			photo = new PhotoPanel("picasa");
			mainPanel.add(photo);
			photo.setVisible(false);
		}
		return photo;
	}

	@Override
	public PhotoGallery getPhotoGallery() {
//		if(gallery == null){
			gallery = new MiniGallery();
			// not add to panel see the constructors...
//		}
		return gallery;
	}

	@Override
	public TextFeed getTextFeed() {
		//TODO
//		if (text == null)
		text = new TextManager();
		mainPanel.add(text);
		return text;
	}

	@Override
	public TextEntry getTextEntry() {
		if (textPanel == null) {
			textPanel = new TextPanel();
			mainPanel.add(textPanel);
			textPanel.setVisible(false);
		}
		return textPanel;
	}


}
