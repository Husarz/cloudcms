package my.test.apps.admin.cellform;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Hyperlink;

public class HyperlinkCell extends AbstractCell<Hyperlink> {

//	interface Templates extends SafeHtmlTemplates {
//		@SafeHtmlTemplates.Template("<a href=\"{0}\">link</a>")
//		SafeHtml cell(SafeHtml value);
//	}
//
//	private static Templates templates = GWT.create(Templates.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Hyperlink value, SafeHtmlBuilder sb) {
		if (value == null) {
			return;
		}
//		SafeHtml safeValue = SafeHtmlUtils.fromString(value);
//		SafeHtml rendered = templates.cell(safeValue);
		SafeHtml rendered = SafeHtmlUtils.fromTrustedString(value.toString());
		sb.append(rendered);
	}
}
