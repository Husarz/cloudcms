package my.test.apps.admin.ui.exp;

import my.test.apps.admin.Main;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.exp.QueryDataProvider;
import my.test.apps.shared.model.*;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SingleSelectionModel;

public class PanelEntities extends Composite {

	private static PanelEntitiesUiBinder uiBinder = GWT
			.create(PanelEntitiesUiBinder.class);

	interface PanelEntitiesUiBinder extends UiBinder<Widget, PanelEntities> {
	}
	
	@UiField(provided = true) CellTable<AppEntity> cellTable;
	@UiField(provided = true) SimplePager pager;
	
	final Main mainCtl = Main.INST;
	QueryDataProvider<AppEntity> al;
	SingleSelectionModel<AppEntity> select;
	
	public PanelEntities() {
		cellTable = new CellTable<AppEntity>();
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));
		
		al = new QueryDataProvider<AppEntity>(cellTable, FactoryDataProvider.getDataservice(), AppEntity.class);
		
		//TODO
	}

}
