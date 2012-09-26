package my.test.apps.shared.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

//import com.googlecode.objectify.annotation.Unindexed;

@Entity
public class Node implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id private String name;
//	@Unindexed 
	private boolean root;
	private List<String> names;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public boolean isRoot() {
		return root;
	}
	public void setRoot(boolean root) {
		this.root = root;
	}
}
