package my.test.apps.admin.ui.text;

import my.test.apps.admin.Main;
import my.test.apps.admin.Main.TextFeed;
import my.test.apps.admin.ui.text.toolbar.RichTextToolbar;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
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
	@UiField(provided = true) RichTextArea area;
	@UiField(provided = true) RichTextToolbar bar;
	@UiField Button add, show;
	@UiField HTML label;

	final Main mainCtl = Main.INST;
	
	public TextManager() {
		area = new RichTextArea();
		bar = new RichTextToolbar(area);
		initWidget(uiBinder.createAndBindUi(this));
//		area.ensureDebugId("cwRichText-area");
//		toolBar.ensureDebugId("cwRichText-toolbar");

	}
	
	@UiHandler("add")
	void addClick(ClickEvent e){
		mainCtl.getTextEntry().addText(area.getHTML());
	}

	@UiHandler("show")
	void showClick(ClickEvent e){
		label.setHTML(area.getHTML());
	}

}
