package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Id;

/**
 * @author adi
 *
 *	This class represents a menu in an application, 
 *  in the form of a tree
 */
public class MapMenu implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id Long id;
	List<Long> mapsId;
	Long parent;
	String map;
//	T obj;
	
	public List<Long> getMapsId() {
		return mapsId;
	}
	public void setMapsId(List<Long> mapsId) {
		this.mapsId = mapsId;
	}
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
//	public T getObj() {
//		return obj;
//	}
//	public void setObj(T obj) {
//		this.obj = obj;
//	}
}
