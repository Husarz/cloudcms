package my.test.apps.server.gdata;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import my.test.apps.server.PicasaServiceImpl.Picasa;
import my.test.apps.shared.model.*;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.google.gdata.client.*;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.photos.*;


public class PicasaService implements Picasa {

	URL feedUrl;
	private static PicasawebService myService;
	
	public PicasaService() {
		myService = new PicasawebService("My Application");
	}
	
	@Override
	public List<Album> getAlbums(String email){
		
		if(email == null) return null;
		List<Album> listAlbum = new ArrayList<Album>();
		try {
			feedUrl = new URL(
					"https://picasaweb.google.com/data/feed/api/user/"
							+ email + "?kind=album");
			UserFeed resultFeed = myService
					.getFeed(feedUrl, UserFeed.class);
//			
//			List<GphotoEntry> entries = resultFeed.getEntries();
//			List<AlbumEntry> entries1 = resultFeed.getAlbumEntries();
//			for (int i = 0; i < entries.size(); i++) {
//				UserAlbum album = new UserAlbum();
//				GphotoEntry entry = entries.get(i);
//				album.setTitle(entry.getTitle().getPlainText());
//				album.setUrl(entry.getFeedLink().getRel());
//				listAlbum.add(album);
//			}
			
			for (AlbumEntry myAlbum : resultFeed.getAlbumEntries()) {
			    Album album = new Album();
			    album.setAlbumId(myAlbum.getGphotoId());
			    album.setTitle(myAlbum.getName());
			    album.setUrl(myAlbum.getHtmlLink().getHref());
			    album.setNumberPhotos(myAlbum.getPhotosUsed());
			    album.setOwner(email);
			    listAlbum.add(album);
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
		return listAlbum;
	}


	@Override
	public List<Photo> getPhotos(Album album) {
		if(album == null) 
			return null;
		List<Photo> listPhoto = new ArrayList<Photo>();
		try {
			feedUrl = new URL(
					"https://picasaweb.google.com/data/feed/api/user/"
					+ album.getOwner() + "/albumid/" + album.getAlbumId() 
					+ "?kind=photo&imgmax=1024&thumbsize=400,160c");
			AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);
			
			for(PhotoEntry photo : feed.getPhotoEntries()) {
				Photo myphoto = new Photo();
				myphoto.setTitle(photo.getTitle().getPlainText());
				myphoto.setAlbumId(photo.getAlbumId());
				myphoto.setPhotoId(photo.getGphotoId());
				myphoto.setPicasaUrl(photo.getHtmlLink().getHref());
				myphoto.setUrls(getListUrl(photo));
				listPhoto.add(myphoto);
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
		return listPhoto;
	}
	
	private List<String> getListUrl(PhotoEntry photo){
		List<String> list = new ArrayList<String>();
		for (com.google.gdata.data.media.mediarss.MediaContent m: photo.getMediaContents()){
			list.add(m.getUrl());
		}
		for (MediaThumbnail m: photo.getMediaThumbnails()){
			list.add(m.getUrl());
		}
		return list;
	}
	
}
