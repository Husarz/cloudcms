package my.test.apps.admin.ui;

import my.test.apps.admin.Main;
import my.test.apps.admin.cellform.ReadInputCell;
import my.test.apps.admin.datacell.AlbumDataProvider;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.PhotoDataProvider;
import my.test.apps.admin.datacell.PicasaPhotoDataProvider;
import my.test.apps.admin.ui.gllery.MiniGallery;
import my.test.apps.admin.ui.gllery.SimplePhoto;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class PhotoPanel extends Composite implements Main.PhotoEntry{

	private static PhotoPanelUiBinder uiBinder = GWT
			.create(PhotoPanelUiBinder.class);

	interface PhotoPanelUiBinder extends UiBinder<Widget, PhotoPanel> {
	}
	
//	@UiField ListBox listuser, listalbum;
	@UiField(provided = true) CellTable<Photo> cellphoto;
	@UiField(provided = true) SimplePager pager;
	@UiField SimplePhoto photoPanel;
	@UiField Button show;

	Main mainCtl = Main.INST;
	PicasaPhotoDataProvider dataPicasaPhoto;
	PhotoDataProvider photoData;
	
	SingleSelectionModel<Photo> select;
	
	String albumId ="";
	
	public PhotoPanel() {
		cellphoto = new CellTable<Photo>();
		pager = new SimplePager();
//		photoPanel = new SimplePhoto();
		initWidget(uiBinder.createAndBindUi(this));
		
		dataPicasaPhoto = FactoryDataProvider.getPicasaPhotoDataProvider(cellphoto);
		photoData = FactoryDataProvider.getPhotoDataProvider(cellphoto);
	
		select = new SingleSelectionModel<Photo>();
		
		select.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				photoPanel.setImg(select.getSelectedObject());
				
			}
		});
		
		initCellTable();
		
		pager.setDisplay(cellphoto);
		pager.setPageSize(5);
	}

	public PhotoPanel(String service){
		this();
		
		if (service.equalsIgnoreCase("picasa")){
			
			dataPicasaPhoto.addDataDisplay(cellphoto);
			photoData.addDataDisplay(cellphoto);
		}
		else {
			
			photoData.addDataDisplay(cellphoto);
		}
	}
	
	
	@UiHandler("show")
	void showClick(ClickEvent e){
		mainCtl.getPhotoGallery().addPhotos(cellphoto.getVisibleItems());
	}

	private void initCellTable() {
		cellphoto.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellphoto.setSelectionModel(select);
		
		Column<Photo, String> editTextColumn1 = new Column<Photo, String>(new TextInputCell()) {

			@Override
			public String getValue(Photo object) {
				return object.getPhotoId();
			}
		};
		cellphoto.addColumn(editTextColumn1, "Id");
		
		TextColumn<Photo> col1 = new TextColumn<Photo>() {

			@Override
			public String getValue(Photo object) {
				return object.getTitle();
			}
		};
		cellphoto.addColumn(col1, "title");
		
		Column<Photo, String> editTextColumn2 = new Column<Photo, String>(new TextInputCell()) {

			@Override
			public String getValue(Photo object) {
				return object.getPicasaUrl();
			}
		};
		cellphoto.addColumn(editTextColumn2, "url");
		
	}

	@Override
	public void setVisiblePanel(boolean visible) {
		//TODO
	}

//	public void showPhotos() {
//		if (photoData == null) return;
//		if(!albumId.equals(album.getAlbumId())){
//			albumId = album.getAlbumId();
//			dataPicasaPhoto.getPhotos(album);
//		}
//		this.setVisible(true);
//	}
	
	@Override
	public void showPhotos(Album album) {
		if(!albumId.equals(album.getAlbumId())){
			albumId = album.getAlbumId();
			photoData.getPhotos(album);
		}
		this.setVisible(true);
	}

	@Override
	public void addPhotos(Album album) {
		photoData.addAllPhotos(album);
		
	}

	@Override
	public void addPhotos(Iterable<Photo> photos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delPhotos(Iterable<Photo> photos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPicasaPhotos(Album album) {
		
		if(!albumId.equals(album.getAlbumId())){
			albumId = album.getAlbumId();
			dataPicasaPhoto.getPhotos(album);

		}
		this.setVisible(true);
		
	}

}
