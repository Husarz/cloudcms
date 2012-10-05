package my.test.apps.shared.model;

import java.io.Serializable;

public interface AppEntity extends Serializable {

	String getName();
	String getType();
	String getInfo();
}
