package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

public class Photo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id String photoId;
	String picasaURL;
	List<String> urls;
	String title;
	String albumId;
	
	public String getPicasaUrl() {
		return picasaURL;
	}
	public void setPicasaUrl(String url) {
		this.picasaURL = url;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> url) {
		this.urls = url;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumid) {
		this.albumId = albumid;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
