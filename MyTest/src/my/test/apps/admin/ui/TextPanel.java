package my.test.apps.admin.ui;

import java.util.Date;

import my.test.apps.admin.Main;
import my.test.apps.admin.datacell.FactoryDataProvider;
import my.test.apps.admin.datacell.TextDataProvider;
import my.test.apps.admin.datacell.exp.QueryDataProvider;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MyText;
import my.test.apps.shared.model.MyUser;

import com.google.gwt.cell.client.DateCell;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;

public class TextPanel extends Composite implements Main.TextEntry {

	private static TextPanelUiBinder uiBinder = GWT
			.create(TextPanelUiBinder.class);

	interface TextPanelUiBinder extends UiBinder<Widget, TextPanel> {
	}

	@UiField(provided = true) CellTable<MyText> cellTable;
	@UiField(provided = true) SimplePager pager;
	@UiField Button add;
	
	final Main mainCtl = Main.INST;
	QueryDataProvider<MyText> ud;
	TextDataProvider dataText;
	SingleSelectionModel<MyText> select;
	
	public TextPanel() {
		cellTable = new CellTable<MyText>();
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));
		
//		dataText = FactoryDataProvider.getTextDataProvider(cellTable);
//		ud = FactoryDataProvider.getQueryDataProvider(cellTable, MyText.class);
		ud = new QueryDataProvider<MyText>(cellTable, FactoryDataProvider.getDataservice(), MyText.class );
		ProvidesKey<MyText> key = new ProvidesKey<MyText>(){

			@Override
			public Object getKey(MyText item) {
				return (item == null) ? null : item.getId();
			}
			
		};
		select = new SingleSelectionModel<MyText>(key);
		initCellTable();
		
//		dataText.addDataDisplay(cellTable);
		ud.addDataDisplay(cellTable);
		
		pager.setDisplay(cellTable);
		pager.setPageSize(10);
		
	}

	private void initCellTable() {
		cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellTable.setSelectionModel(select);
		
		TextColumn<MyText>  col0 = new TextColumn<MyText>() {

			@Override
			public String getValue(MyText object) {
				return object.getOwenr();
			}
			
		};
		cellTable.addColumn(col0, "user");
				
		TextColumn<MyText>  col1 = new TextColumn<MyText>() {
			
			@Override
			public String getValue(MyText object) {
				String title =  object.getTitle();
				if (title == null || title.equals(""))
					return "bez tytulu";
				return title;
			}
		};
		cellTable.addColumn(col1, "Title");
		
		Column<MyText, Date> col2 = new Column<MyText, Date>(new DateCell()) {
			
			@Override
			public Date getValue(MyText object) {
				return object.getDate();
			}
		};
		cellTable.addColumn(col2, "Date");
		
	}

	@UiHandler("add")
	void addClick(ClickEvent e){
		mainCtl.getTextFeed();
	}
	
	@Override
	public void showTexts(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showTexts() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getText(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addText(String text) {
		MyText myText = new MyText();
		myText.setOwenr(mainCtl.getInfo().get("email"));
		myText.setDate(new Date());
		myText.setText(text);
		dataText.addText(myText);
	}



}
