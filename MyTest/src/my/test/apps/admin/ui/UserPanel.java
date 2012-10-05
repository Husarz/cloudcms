package my.test.apps.admin.ui;

import java.util.List;

import my.test.apps.admin.bundle.Resources;

import my.test.apps.admin.Main;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.UserDataProvider;
import my.test.apps.admin.datacell.exp.QueryDataProvider;
import my.test.apps.shared.model.*;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;

public class UserPanel extends Composite implements Main.UserEntry {

	private static UserPanelUiBinder uiBinder = GWT
			.create(UserPanelUiBinder.class);

	interface UserPanelUiBinder extends UiBinder<Widget, UserPanel> {
	}

	@UiField(provided = true) CellTable<MyUser> cellTable;
	@UiField(provided = true) SimplePager pager1;
	@UiField Button add, del, show, gallery;
	@UiField Button ref;
	@UiField TextBox text;
	
	final Main mainCtl ;
	QueryDataProvider<MyUser> ud;
	UserDataProvider userdata;
	final MultiSelectionModel<MyUser> selection;
	
	public UserPanel() {
		cellTable = new CellTable<MyUser>();
		pager1 = new SimplePager();
		
		this.mainCtl = Main.INST;
		initWidget(uiBinder.createAndBindUi(this));
		
//		userdata = FactoryDataProvider.getUserDataProvider(cellTable);
//		ud = FactoryDataProvider.getQueryDataProvider(cellTable, MyUser.class);
//		ud = FactoryDataProvider.getQueryDataProvider1(cellTable, MyUser.class);
		ud = new QueryDataProvider<MyUser>(cellTable, FactoryDataProvider.getDataservice(), MyUser.class);
		
		ProvidesKey<MyUser> key = new ProvidesKey<MyUser>() {

			@Override
			public Object getKey(MyUser item) {
				return (item == null) ? null : item.getEmailAddress();
			}
		};
		
		selection = new MultiSelectionModel<MyUser>(key);
		initCellTable();
		
//		userdata.addDataDisplay(cellTable);
		ud.addDataDisplay(cellTable);
		
		pager1.setDisplay(cellTable);
		pager1.setPageSize(10);
		
//		userdata.onRangeChanged(cellTable);
//		ud.onRangeChanged(cellTable);
	}
	
	@UiHandler("ref")
	void refClick(ClickEvent e) {
		ud.onRangeChanged(cellTable);
	}

	@UiHandler("del")
	void delClick(ClickEvent e) {
		if (selection.getSelectedSet().size()>0)
			ud.delEntities(selection.getSelectedSet());
//			userdata.delUser(selection.getSelectedSet());
		selection.clear();
	}
	
	@UiHandler("add")
	void addClick(ClickEvent e) {
		if(!text.getText().isEmpty())
			addUser(text.getText());
	}
	
	@UiHandler("show")
	void showClick(ClickEvent e){
		for (MyUser u:selection.getSelectedSet()){
//			if(u.getService().equalsIgnoreCase("picasa"))
			if (mainCtl!=null)
				mainCtl.getPicasaEntry().showUserAlbum(u.getEmailAddress());
			else 
				Window.alert("mainCTL is null");
		}
		
	}
	
	private void initCellTable() {
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		cellTable.setSelectionModel(selection, DefaultSelectionEventManager.<MyUser> createCheckboxManager());

		
		Column<MyUser, Boolean> checkColumn = new Column<MyUser, Boolean>(
				new CheckboxCell(true, false)){

					@Override
					public Boolean getValue(MyUser object) {
						return selection.isSelected(object);
					}
			
		};
		cellTable.addColumn(checkColumn,  SafeHtmlUtils.fromSafeConstant("<br/>"));
		TextColumn<MyUser>  col1 = new TextColumn<MyUser>() {
			
			@Override
			public String getValue(MyUser object) {
				return object.getEmailAddress();
			}
		};
		cellTable.addColumn(col1, "email");
		TextColumn<MyUser> col2 = new TextColumn<MyUser>() {

			@Override
			public String getValue(MyUser object) {
				return object.getService();
			}
		};
		cellTable.addColumn(col2, "service");
	}

	@Override
	public void setVisiblePanel(boolean visible) {
		this.setVisible(visible);
	}

	@Override
	public void addUser(String email) {
		MyUser user = new MyUser(email);
		ud.addEntity(user);
	}

	@Override
	public void delUser(MyUser user) {
		ud.delEntity(user);
	}

	@Override
	public void showUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MyUser> getUsers() {
		return cellTable.getVisibleItems();
	}
	
}
