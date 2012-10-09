package my.test.apps.admin.ui;

import my.test.apps.admin.Main;
import my.test.apps.admin.cellform.ReadInputCell;
import my.test.apps.admin.datacell.AlbumDataProvider;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.exp.QueryDataProvider;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MyUser;
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
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;

public class AlbumsPanel extends Composite implements Main.AlbumEntry{

	private static AlbumsPanelUiBinder uiBinder = GWT
			.create(AlbumsPanelUiBinder.class);

	interface AlbumsPanelUiBinder extends UiBinder<Widget, AlbumsPanel> {
	}

	@UiField(provided = true) CellTable<Album> cellAlbum;
	@UiField(provided = true) SimplePager pager;
	@UiField Button del, show;
	
	final Main mainCtl = Main.INST;
	QueryDataProvider<Album> al;
	AlbumDataProvider albums;
	SingleSelectionModel<Album> select;
	
	public AlbumsPanel() {
		cellAlbum = new CellTable<Album>();
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));
		
//		albums = FactoryDataProvider.getAlbumDataProvider(cellAlbum);
//		al = FactoryDataProvider.getQueryDataProvider(cellAlbum, Album.class);
		al = new QueryDataProvider<Album>(cellAlbum, FactoryDataProvider.getDataservice(), Album.class);
		select = new SingleSelectionModel<Album>();
		
		initCellAlbum();
		
//		albums.addDataDisplay(cellAlbum);
		al.addDataDisplay(cellAlbum);
		
		pager.setDisplay(cellAlbum);
		pager.setPageSize(10);
		
//		al.onRangeChanged(cellAlbum);
	}

	private void initCellAlbum() {
		cellAlbum.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		cellAlbum.setSelectionModel(select);
		
		TextColumn<Album>  col0 = new TextColumn<Album>() {
			
			@Override
			public String getValue(Album object) {
				return object.getOwner();
			}
		};
		cellAlbum.addColumn(col0, "user");
		
		TextColumn<Album>  col1 = new TextColumn<Album>() {
			
			@Override
			public String getValue(Album object) {
				return object.getTitle();
			}
		};
		cellAlbum.addColumn(col1, "Title");
		
		
		Column<Album, String> editTextColumn3 = new Column<Album, String>(new ReadInputCell()) {

			@Override
			public String getValue(Album object) {
				return object.getUrl();
			}

		};
		cellAlbum.addColumn(editTextColumn3, "url");
		
	}
	
	@UiHandler("show")
	void showClick(ClickEvent e){
		mainCtl.getPhotoEntry().showPhotos(select.getSelectedObject());
	}
	
	@UiHandler("del")
	void delClic(ClickEvent e){
		delAlbum(select.getSelectedObject());
	}

	@Override
	public void setVisiblePanel(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAlbums(Album album) {
		albums.addAlbum(album);
	}

	@Override
	public void delAlbum(Album album) {
		albums.delAlbum(album);
		
	}


}
