package my.test.apps.admin.ui.gllery;

import my.test.apps.shared.model.Photo;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class SimplePhoto extends Composite{

	private static SimplePhotoUiBinder uiBinder = GWT
			.create(SimplePhotoUiBinder.class);

	interface SimplePhotoUiBinder extends UiBinder<Widget, SimplePhoto> {}

	@UiField(provided = true) CellList<String> list;
	@UiField(provided = true) SimplePager pager;
	@UiField Image img;
//	@UiField Label label;
	
	final SingleSelectionModel<String> selection;

	public SimplePhoto() {
		list = new CellList<String>(new TextCell()); 
		pager = new SimplePager();
		initWidget(uiBinder.createAndBindUi(this));
		
		list.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		selection = new SingleSelectionModel<String>();
		list.setSelectionModel(selection);
		
		selection.addSelectionChangeHandler(new Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				img.setUrl(selection.getSelectedObject());				
			}
		});
		
		pager.setDisplay(list);
		pager.setPageSize(4);
	}

	public void setImg(Photo  photo){
		//TODO
//		label.setText(photo.getUrl().get(0));
//		img.setUrl(photo.getUrl().get(0));
		list.setRowCount(photo.getUrls().size(), true);
		list.setRowData(0, photo.getUrls());
		
	}
	
	public void setImg(String link) {
		this.img.setUrl(link);
	}
}
