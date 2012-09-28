package my.test.apps.admin.datacell;

import java.util.List;

import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.MyText;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public class TextDataProvider extends AsyncDataProvider<MyText>{

	final AdminServicesAsync service;
	final HasData<MyText> display;
	
	public TextDataProvider(HasData<MyText> display, AdminServicesAsync service) {
		super();
		this.service = service;
		this.display = display;
	}
	
	@Override
	protected void onRangeChanged(HasData<MyText> display) {
		service.getText( new AsyncCallback<List<MyText>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<MyText> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
			}
		});
		
	}
	
	public void addText(MyText text){
		service.addText(text, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
