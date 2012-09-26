package my.test.apps.admin.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface Resources extends ClientBundle {
	public static final Resources INSTANCE =  GWT.create(Resources.class);
	
	interface MyAdmin extends CssResource {
		String tab();
		String panel();
	}
	
	@Source("my/test/apps/admin/path/admin.css")
	MyAdmin getadmin();
}
