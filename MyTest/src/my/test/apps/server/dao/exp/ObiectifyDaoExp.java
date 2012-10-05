package my.test.apps.server.dao.exp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import my.test.apps.shared.model.Album;
import my.test.apps.shared.model.MapMenu;
import my.test.apps.shared.model.MyText;
import my.test.apps.shared.model.MyUser;
import my.test.apps.shared.model.Photo;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

public class ObiectifyDaoExp<AppEntity> extends DAOBase {

	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;
	
	static {
		ObjectifyService.register(MyUser.class);
		ObjectifyService.register(Photo.class);
		ObjectifyService.register(Album.class);
		ObjectifyService.register(MapMenu.class);
		ObjectifyService.register(MyText.class);
	}
	protected Class<AppEntity> clazz;
	
	public ObiectifyDaoExp(Class<AppEntity> clazz) {
		this.clazz = clazz;
	}
	
//	public <T extends AppEntity> ObiectifyDaoExp() {
//		this.clazz = clazz;
//	}
	
	protected Key<AppEntity> put(AppEntity entity){
		return ofy().put(entity);
	}
	public Map<Key<AppEntity>, AppEntity> putAll(Iterable<AppEntity> entities){
		return ofy().put(entities);
	}
	
	public void delete(AppEntity entity){
		ofy().delete(entity);
	}
	public void deleteKey(Key<AppEntity> entityKey){
		ofy().delete(entityKey);
	}
	public void deleteAll(Iterable<AppEntity> entities){
		ofy().delete(entities);
	}
	public void deleteKeys(Iterable<Key<AppEntity>> keys){
		ofy().delete(keys);
	}
	
//	public T get(Long id) throws EntityNotFoundException{
//		return ofy().get(this.clazz, id);
//	}
	public AppEntity get(Long id) throws EntityNotFoundException{
		return ofy().get(this.clazz, id);
	}
	public AppEntity get(Key<AppEntity> key) throws EntityNotFoundException{
		return ofy().get(key);
	}
	public Map<Key<AppEntity>, AppEntity> getAll(Iterable<Key<AppEntity>> keys){
		return ofy().get(keys);
	}
	
	/**
	 * 
	 * Convenience method to get all objects matching a single property
	 * 
	 * @param propName		- pola encji
	 * @param propValue		- wartosci pol encji
	 * @return 				- zwraca wlasciwe encje
	 */
	
	public ArrayList<AppEntity> getQuery(){
		Query<AppEntity> q = ofy().query(clazz);
		return asList(q.fetch());
	}
	
	public AppEntity getByProperty(String propName, Object propValue){
		Query<AppEntity> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.get();
	}
	public ArrayList<AppEntity> listByProperty(String propName, Object propValue){
		Query<AppEntity> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asList(q.fetch());
	}
	public ArrayList<Key<AppEntity>> listKeysByProperty(String propName, Object propValue){
		Query<AppEntity> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asKeyList(q.fetchKeys());
	}
	public AppEntity getByExample(AppEntity exampleObj){
		Query<AppEntity> queryByExample = buildQueryByExample(exampleObj);
		Iterable<AppEntity> iterableResults = queryByExample.fetch();
		Iterator<AppEntity> i = iterableResults.iterator();
		AppEntity obj = i.next();
		if (i.hasNext())
			throw new RuntimeException("Too many results");
		return obj;
	}
	public ArrayList<AppEntity> listByExample(AppEntity exampleObj){
		Query<AppEntity> queryByExample = buildQueryByExample(exampleObj);
		return asList(queryByExample.fetch());
	}
	
	
	
	protected Query<AppEntity> buildQueryByExample(AppEntity exampleObj) {
		Query<AppEntity> q = ofy().query(clazz);
		for (Field field : clazz.getDeclaredFields()){
			// Ignore transient, embedded, array, and collection properties
			if (field.isAnnotationPresent(Transient.class)
					|| (field.isAnnotationPresent(Embedded.class))
					|| (field.getType().isArray())
					|| (Collection.class.isAssignableFrom(field.getType())) 
					|| ((field.getModifiers() & BAD_MODIFIERS) != 0))
					continue;
			field.setAccessible(true);
			Object value;
			try{
				value = field.get(exampleObj);
			}
			catch (IllegalArgumentException e){
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e){
				throw new RuntimeException(e);
			}
			
			if (value != null){
				q.filter(field.getName(), value);
			}
		}
		return q;
	}

	protected ArrayList<Key<AppEntity>> asKeyList(Iterable<Key<AppEntity>> fetchKeys) {
		ArrayList<Key<AppEntity>> keys = new ArrayList<Key<AppEntity>>();
		for (Key<AppEntity> key : fetchKeys){
			keys.add(key);
		}
		return keys;
	}

	protected ArrayList<AppEntity> asList(Iterable<AppEntity> fetch) {
		ArrayList<AppEntity> list = new ArrayList<AppEntity>();
		for (AppEntity t : fetch){
			list.add(t);
		}
		return list;
	}
}
