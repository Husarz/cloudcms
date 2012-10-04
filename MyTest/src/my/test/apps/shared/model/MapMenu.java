package my.test.apps.shared.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.googlecode.objectify.Key;

/**
 * @author adi
 *
 *	This class represents a menu in an application, 
 *  in the form of a tree
 */
@Entity
public class MapMenu implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id Long id;
//	List<Long> mapsId;
	Long parent;
	String map;
	String descrip;
	Key<?> objKey;

	public MapMenu() { }

	public Long getId() {
		return id;
	}
//	public List<Long> getMapsId() {
//		return mapsId;
//	}
//	public void setMapsId(List<Long> mapsId) {
//		this.mapsId = mapsId;
//	}
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
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public Key<?> getObjKey() {
		return objKey;
	}
	public void setObjKey(Key<?> objKey) {
		this.objKey = objKey;
	}
}
