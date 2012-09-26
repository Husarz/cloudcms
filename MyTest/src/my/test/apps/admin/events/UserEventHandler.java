package my.test.apps.admin.events;

import com.google.gwt.event.shared.EventHandler;

public interface UserEventHandler extends EventHandler  {
	void onUserEventChanged(UserEvent userEvent);
}
