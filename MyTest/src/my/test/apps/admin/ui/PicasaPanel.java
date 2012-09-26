package my.test.apps.admin.ui;

import my.test.apps.admin.Main;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.PicasaAlbumDataProvider;
import my.test.apps.admin.events.UserEvent;
import my.test.apps.admin.events.UserEventHandler;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;

public class PicasaPanel extends Composite implements Main.PicasaEntry{

	private static PicasaPanelUiBinder uiBinder = GWT
			.create(PicasaPanelUiBinder.class);

	interface PicasaPanelUiBinder extends UiBinder<Widget, PicasaPanel> {
	}

	@UiField ListBox listBox;
	@UiField(provided = true) CellTable<Album> cellTable;
	@UiField(provided = true) SimplePager pager;
	@UiField Button add, show, addall;
	
	Main mainCtl = Main.INST;
	PicasaAlbumDataProvider data;
	SingleSelectionModel<Album> select;
	
	public PicasaPanel() {
		cellTable = new CellTable<Album>();
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));

	
		data = FactoryDataProvider.getPicasaAlbumDataProvider(cellTable);
		select = new SingleSelectionModel<Album>();
		initCellTable();
		
		
		data.addDataDisplay(cellTable);
		
		pager.setDisplay(cellTable);
		pager.setPageSize(10);

		mainCtl.getEventBus().addHandler(UserEvent.TYPE, new UserEventHandler() {
			
			@Override
			public void onUserEventChanged(UserEvent userEvent) {
				listBox.clear();
				for(MyUser u: mainCtl.getUserEntry().getUsers()){
					if (u==null) return;
					listBox.addItem(u.getEmailAddress());
				}
//				showAlbums("");
			}
		});
		
//		mainCtl.getUserEntry();
	//	showAlbums("");
	}
	
	public void showAlbums(String email){
//		initTable();
		if (email == "" || email == null)
			if (listBox.getItemCount()>0)
				email = listBox.getValue(listBox.getSelectedIndex());
		if (email == "" || email == null)
			return;
		data.getAlbums(email);
		
	}
	
	@UiHandler("listBox")
	void clicListBox(ClickEvent e){
		if (listBox.getItemCount()>0)
			showAlbums(listBox.getValue(listBox.getSelectedIndex()));
	}
	
	@UiHandler("addall")
	void addallClick(ClickEvent e){
		Album album = select.getSelectedObject();
		addPicasaAlbum(album);
		mainCtl.getPhotoEntry().addPhotos(album);
	}
	
	@UiHandler("add")
	void addClick(ClickEvent e){
		addPicasaAlbum(select.getSelectedObject());
	}
	@UiHandler("show")
	void showClick(ClickEvent e){
		mainCtl.getPhotoEntry().showPicasaPhotos(select.getSelectedObject());
	}
	
	private void initTable(){
		if (data!=null) data.removeDataDisplay(cellTable);

	}
	
	private void initCellTable() {
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		cellTable.setSelectionModel(select);
		
//		Column<Album, String> editTextColumn1 = new Column<Album, String>(new TextInputCell()) {
//
//			@Override
//			public String getValue(Album object) {
//				return object.getAlbumId();
//			}
//
//		};
//		cellTable.addColumn(editTextColumn1, "Id");
//		TextColumn<PicasaAlbum>  col0 = new TextColumn<PicasaAlbum>() {
//			
//			@Override
//			public String getValue(PicasaAlbum object) {
//				return object.getAlbumId();
//			}
//		};
//		cellTable.addColumn(col0, "Id");
		
		TextColumn<Album>  col1 = new TextColumn<Album>() {
			
			@Override
			public String getValue(Album object) {
				return object.getTitle();
			}
		};
		cellTable.addColumn(col1, "Title");
		
//		Column<UserAlbum, Hyperlink> col = new Column<UserAlbum, Hyperlink>(new HyperlinkCell()){
//
//			@Override
//			public Hyperlink getValue(UserAlbum object) {
//				// TODO Auto-generated method stub
//				return new Hyperlink(SafeHtmlUtils.fromTrustedString( object.getUrl()),"link");
//			}
//			
//		};
//		cellTable.addColumn(col, "Url");
		Column<Album, String> editTextColumn = new Column<Album, String>(new TextInputCell()) {

			@Override
			public String getValue(Album object) {
				return object.getUrl();
			}

		};
		cellTable.addColumn(editTextColumn, "url");
//		TextColumn<PicasaAlbum>  col2 = new TextColumn<PicasaAlbum>() {
//			
//			@Override
//			public String getValue(PicasaAlbum object) {
//				return object.getUrl();
//			}
//		};
//		cellTable.addColumn(col2, "Url");
	
		TextColumn<Album>  col3 = new TextColumn<Album>() {
			
			@Override
			public String getValue(Album object) {
				return String.valueOf(object.getNumberPhotos());
			}
		};
		cellTable.addColumn(col3, "number");
	}

	@Override
	public void setVisiblePanel(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPicasaAlbum(Album album) {
		mainCtl.getAlbumEntry().addAlbums(album);
	}

	@Override
	public void showUserAlbum(String email) {
		showAlbums(email);
		
	}

	@Override
	public void addPicasaPhotos(Iterable<Photo> photos) {
		// TODO Auto-generated method stub
		
	}
	
}
