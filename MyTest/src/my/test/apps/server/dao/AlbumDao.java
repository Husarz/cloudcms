package my.test.apps.server.dao;

import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

import my.test.apps.shared.model.Album;

public class AlbumDao extends ObjectifyGenericDao<Album> {
	
private static final Logger LOG = Logger.getLogger(Album.class.getName());
	
	static{
		ObjectifyService.register(Album.class);
	}
	
	public AlbumDao() {
		super(Album.class);
	}
	
	public List<Album> getAlbums(){
		Query<Album> q = ofy().query(Album.class);
		return asList(q);
	}
	
	public List<Album> getAlbums(String email){
		return listByProperty("owner", email);
	}
	
	public void addAlbum(Album album){
		put(album);
	}

}
