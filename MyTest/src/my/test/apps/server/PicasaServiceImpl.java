package my.test.apps.server;

import java.util.List;

import javax.servlet.ServletException;

import my.test.apps.server.gdata.PicasaService;
import my.test.apps.shared.model.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PicasaServiceImpl extends RemoteServiceServlet implements my.test.apps.admin.rpc.PicasaService{

	private static final long serialVersionUID = 1L;

	Picasa picasa;
	
	public interface Picasa{
		List<Album> getAlbums(String email);
		List<Photo> getPhotos(Album album);
	}
	
	@Override
	public void init() throws ServletException {
		this.picasa = new PicasaService();
		super.init();
	}
	
	@Override
	public List<Album> getUserAlbums(String email) {
		if (picasa == null) 
			return null;
		return picasa.getAlbums(email);
	}

	@Override
	public List<Photo> getPhotosAlbum(Album album) {
		if (picasa == null) 
			return null;
		return picasa.getPhotos(album);
	}
}
