package my.test.apps.admin.ui;

import java.util.List;

import my.test.apps.admin.Main;
import my.test.apps.shared.model.MapMenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public class MenuPanel extends Composite implements Main.MenuEntry {

	private static MenuPanelUiBinder uiBinder = GWT
			.create(MenuPanelUiBinder.class);

	interface MenuPanelUiBinder extends UiBinder<Widget, MenuPanel> {
	}
	
	@UiField(provided = true) CellBrowser cellBrowser;
	@UiField Label selectedLabel;
	
	MultiSelectionModel<MapMenu> select;
	
	public MenuPanel() {
		TreeViewModel viewModel = new CustomTreeModel();
		CellBrowser.Builder<String> builder = new  CellBrowser.Builder<String>( viewModel ,"value"); 
		cellBrowser = builder.build();
		initWidget(uiBinder.createAndBindUi(this));
		
		select = new MultiSelectionModel<MapMenu>();
	}

	
	
	
	@Override
	public void setVisiblePanel(boolean visible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNode(MapMenu node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delNode(MapMenu node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNode(MapMenu node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MapMenu> getMapMenu() {
		// TODO Auto-generated method stub
		return null;
	}


}
