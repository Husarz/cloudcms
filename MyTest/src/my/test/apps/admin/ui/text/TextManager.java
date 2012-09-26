package my.test.apps.admin.ui.text;

import my.test.apps.admin.Main.TextFeed;
import my.test.apps.admin.ui.text.toolbar.RichTextToolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Constants;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class TextManager extends Composite implements TextFeed{

	private static TextManagerUiBinder uiBinder = GWT
			.create(TextManagerUiBinder.class);

	interface TextManagerUiBinder extends UiBinder<Widget, TextManager> {
	}

//	public static interface CwConstants extends Constants {
//		String cwRichTextDescription();
//
//		String cwRichTextName();
//	}

	@UiField HTMLPanel panel;
//	@UiField RichTextArea area;
//	@UiField(provided = true) RichTextToolbar bar;

	public TextManager() {
		initWidget(uiBinder.createAndBindUi(this));
//		bar = new RichTextToolbar(area);
//		area.ensureDebugId("cwRichText-area");
		RichTextArea area = new RichTextArea();
		area.setSize("100%", "14em");
		
		
		RichTextToolbar bar = new RichTextToolbar(area);
		bar.setWidth("100%");
		panel.add(bar);
		panel.add(area);
//		toolBar.ensureDebugId("cwRichText-toolbar");
//		bar.setWidth("90%");
	}

}
