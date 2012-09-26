package my.test.apps.server.gdata;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.GphotoEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class PicasaServlet extends HttpServlet {

	URL feedUrl;
	PicasawebService myService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		try {

			// Create a new Picasa Web Albums service
			PicasawebService myService = new PicasawebService("My Application");
			// myService.setUserCredentials("apilat@gmail.com","Adi82PilP");

			// Get a list of all entries
			URL metafeedUrl = new URL(
					"http://picasaweb.google.com/data/feed/api/user/"
							+ "apilat@gmail.com" + "?kind=album");
			out.println("Getting Picasa Web Albums entries..." + "<br />");
			UserFeed resultFeed = myService
					.getFeed(metafeedUrl, UserFeed.class);
			List<GphotoEntry> entries = resultFeed.getEntries();
			for (int i = 0; i < entries.size(); i++) {
				GphotoEntry entry = entries.get(i);
				out.println("<br />" + entry.getTitle().getPlainText());
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
