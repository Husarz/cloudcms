package my.test.apps.admin.ui.exp;

import java.util.List;

import my.test.apps.admin.Main;
import my.test.apps.admin.Main.EntityEntry;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.exp.EntityDataProvider;
import my.test.apps.admin.datacell.exp.QueryDataProvider;
import my.test.apps.shared.model.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

public class PanelEntities extends Composite implements EntityEntry{

	private static PanelEntitiesUiBinder uiBinder = GWT
			.create(PanelEntitiesUiBinder.class);

	interface PanelEntitiesUiBinder extends UiBinder<Widget, PanelEntities> {
	}
	
	@UiField ListBox listBox;
	@UiField(provided = true) CellTable<AppEntity> cellTable;
	@UiField(provided = true) SimplePager pager;
	
	Main mainCtl = Main.INST;
	QueryDataProvider<AppEntity> al;
	SingleSelectionModel<AppEntity> select;
	ListDataProvider<AppEntity> list;
	
	public PanelEntities() {
		cellTable = new CellTable<AppEntity>();
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));
		
		select = new SingleSelectionModel<AppEntity>();
		
		initCellAlbum();
		
		pager.setDisplay(cellTable);
		pager.setPageSize(10);
	}
	
	private void initCellAlbum() {
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		cellTable.setSelectionModel(select);
		
		TextColumn<AppEntity>  col0 = new TextColumn<AppEntity>() {
			
			@Override
			public String getValue(AppEntity object) {
				return object.getName();
			}
		};
		cellTable.addColumn(col0, "name");
		
		TextColumn<AppEntity>  col1 = new TextColumn<AppEntity>() {
			
			@Override
			public String getValue(AppEntity object) {
				return object.getInfo();
			}
		};
		cellTable.addColumn(col1, "Info");
	}

	@UiHandler("listBox")
	void clicListBox(ClickEvent e){
		String clazz = listBox.getItemText(listBox.getSelectedIndex());
		if(clazz.equals("MyUser"))
			list = new EntityDataProvider(MyUser.class.getName(), "any" , "any").getListData();
		if(clazz.equals("Album"))
			list = new EntityDataProvider(Album.class.getName(), "any" , "any").getListData();
		if(clazz.equals("MapMenu"))
			list = new EntityDataProvider(MapMenu.class.getName(), "any" , "any").getListData();
		list.addDataDisplay(cellTable);
	}

	@Override
	public void addEntities(Iterable<? extends AppEntity> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delEntities(Iterable<? extends AppEntity> e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<? extends AppEntity> getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

}
