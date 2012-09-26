package my.test.apps.admin.rpc;

import java.util.List;

import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("picasa")
public interface PicasaService extends RemoteService {

	List<Album> getUserAlbums(String email);
	List<Photo> getPhotosAlbum(Album album);
}
