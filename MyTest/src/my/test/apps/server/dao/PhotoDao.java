package my.test.apps.server.dao;

import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;

import my.test.apps.server.PicasaServiceImpl.Picasa;
import my.test.apps.server.gdata.PicasaService;
import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

public class PhotoDao extends ObjectifyGenericDao<Photo>{
	
	private static final Logger LOG = Logger.getLogger(Photo.class.getName());
	
	Picasa picasa;
	
	static{
		ObjectifyService.register(Photo.class);
	}
	
	public PhotoDao() {
		super(Photo.class);
	}

	public List<Photo> getPhotos(Album album){
		return listByProperty("albumId", album.getAlbumId());
	}
	
	public void addPhotos(List<Photo> photos){
		this.putAll(photos);
	}
	
	public void addPhotos(Album album){
		if(picasa == null) picasa = new PicasaService();
		List<Photo> photos = picasa.getPhotos(album);
		putAll(photos);
	}
}
