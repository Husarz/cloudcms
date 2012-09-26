package my.test.apps.admin;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

public class MyAdmin implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootLayoutPanel rp = RootLayoutPanel.get();
		Main ap = Main.INST;
		rp.add(ap.getMainWidget());
	}
}
