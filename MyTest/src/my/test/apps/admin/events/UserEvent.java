package my.test.apps.admin.events;

import com.google.gwt.event.shared.GwtEvent;

public class UserEvent extends GwtEvent<UserEventHandler>{


	public static Type<UserEventHandler> TYPE = new Type<UserEventHandler>();
	
	@Override
	public Type<UserEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(UserEventHandler handler) {
		handler.onUserEventChanged(this);
		
	}
}
