package my.test.apps.server.dao;

import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;

import my.test.apps.shared.model.Node;

public class NodeDao extends ObjectifyGenericDao<Node>{

private static final Logger LOG = Logger.getLogger(Node.class.getName());
	
	static{
		ObjectifyService.register(Node.class);
	}
	
	public NodeDao() {
		super(Node.class);
	}

}
