package my.test.apps.admin.cellform;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ReadInputCell extends AbstractCell<String> {

	interface Templates extends SafeHtmlTemplates {
		@Template("<input type='text' value='{0}' tabindex='-1' " +
				"onclick='javascript:this.select()' readonly='readonly'></input>")
		SafeHtml cell(String value);
	}

	private static Templates templates = GWT.create(Templates.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			String value, SafeHtmlBuilder sb) {
		
//		SafeHtml safeValue = SafeHtmlUtils.fromString(value);
//		SafeHtml rendered = templates.cell(safeValue);
		
		SafeHtml rendered = templates.cell(value);
		
		if (value != null) {
		      sb.append(rendered);
		    } else {
		      sb.appendHtmlConstant("<input type=\"text\" tabindex=\"-1\"></input>");
		    }
		//sb.append(rendered);
	}

}
