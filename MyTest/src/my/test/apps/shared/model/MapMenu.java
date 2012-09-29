package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

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
	List<Key<MapMenu>> mapsId;
	Key<MapMenu> parent;
	String map;
	String descrip;
	Key<?> objKey;
	

	public List<Key<MapMenu>> getMapsId() {
		return mapsId;
	}
	public void setMapsId(List<Key<MapMenu>> mapsId) {
		this.mapsId = mapsId;
	}
	public Key<MapMenu> getParent() {
		return parent;
	}
	public void setParent(Key<MapMenu> parent) {
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
