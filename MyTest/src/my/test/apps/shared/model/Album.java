package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Album implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id String albumId;
	private String url;
	private String owner;
	private String title;
//	private String img;
//	private List<String> imgs;
	private List<String> menu;
	private String service;
	int numberPhotos;
	
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public List<String> getMenu() {
		return menu;
	}
	public void setMenu(List<String> menu) {
		this.menu = menu;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public int getNumberPhotos() {
		return numberPhotos;
	}
	public void setNumberPhotos(int numberPhotos) {
		this.numberPhotos = numberPhotos;
	}
	
}
