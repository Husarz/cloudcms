package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Photo implements AppEntity{

	private static final long serialVersionUID = 1L;
	
	@Id String photoId;
	String picasaURL;
	List<String> urls;
	String title;
	String albumId;
	
	public Photo() { }
	
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

	@Override
	public String getName() {
		return getTitle();
	}

	@Override
	public String getType() {
		return "Zdjecia";
	}

	@Override
	public String getInfo() {
		return getPicasaUrl();
	}
}
