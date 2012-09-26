package my.test.apps.admin.datacell;

import java.util.ArrayList;
import java.util.List;

import my.test.apps.admin.Main;
import my.test.apps.admin.events.UserEvent;
import my.test.apps.admin.rpc.AdminServicesAsync;
import my.test.apps.shared.model.MyUser;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public  class UserDataProvider extends AsyncDataProvider<MyUser> {

	final AdminServicesAsync service;
	final HasData<MyUser> display;
	
	Main mainCtl = Main.INST;

	public UserDataProvider(HasData<MyUser> display, AdminServicesAsync service) {
		super();
		this.service = service;
		this.display = display;
	}
	
	@Override
	public void onRangeChanged(HasData<MyUser> display) {
		service.getMyUsers(new AsyncCallback<List<MyUser>>() {

			@Override
			public void onSuccess(List<MyUser> result) {
				updateRowCount(result.size(), true);
				updateRowData(0, result);
				mainCtl.getEventBus().fireEvent(new UserEvent());
			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public void addUser(String email){
		service.addUser(email, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie dodał");
				
			}

			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
		});
	}
	
	public void delUser(Iterable<MyUser> users){
		service.deleteUsers(users, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie usunal");
				
			}
		});
	}
	
	public void delUser(String email){
		List<String> emails = new ArrayList<String>();
		emails.add(email);
		service.deleteemails(emails,  new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("nie usunal");
				
			}

			@Override
			public void onSuccess(Void result) {
				onRangeChanged(display);
				
			}
			
		});
	}

}
