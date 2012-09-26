package my.test.apps.admin.datacell;

//import java.util.ArrayList;
import java.util.List;

import my.test.apps.admin.rpc.AdminServices;
import my.test.apps.admin.rpc.AdminServicesAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
//import com.google.gwt.view.client.Range;

public class EmailDataProvider extends AsyncDataProvider<String>{

	AdminServicesAsync service = GWT.create(AdminServices.class);
	
	@Override
	public void onRangeChanged(HasData<String> display) {
//		final Range range = display.getVisibleRange();
		
		service.getUsersemail(new AsyncCallback<List<String>>() {
			
			@Override
			public void onSuccess(List<String> result) {
				
				updateRowData(0, result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
