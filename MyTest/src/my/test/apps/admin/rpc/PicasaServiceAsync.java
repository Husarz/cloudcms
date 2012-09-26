package my.test.apps.admin.rpc;

import java.util.List;

import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.Photo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PicasaServiceAsync {

	void getUserAlbums(String email, AsyncCallback<List<Album>> callback);

	void getPhotosAlbum(Album album, AsyncCallback<List<Photo>> callback);

}
