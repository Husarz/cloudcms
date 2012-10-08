package my.test.apps.server.dao;

//import java.util.Map;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import my.test.apps.shared.model.*;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

/**
 * @author adi
 *
 * @param <T>
 */
public class ObjectifyGenericDao<T> extends DAOBase{

	static final int BAD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.TRANSIENT;
			
	static {
//		ObjectifyService.register(MyUser.class);
//		ObjectifyService.register(Photo.class);
//		ObjectifyService.register(Album.class);
//		ObjectifyService.register(MapMenu.class);
//		ObjectifyService.register(MyText.class);
	}
	protected Class<T> clazz;
	
	public ObjectifyGenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	protected Key<T> put(T entity){
		return ofy().put(entity);
	}
	public Map<Key<T>, T> putAll(Iterable<T> entities){
		return ofy().put(entities);
	}
	
	public void delete(T entity){
		ofy().delete(entity);
	}
	public void deleteKey(Key<T> entityKey){
		ofy().delete(entityKey);
	}
	public void deleteAll(Iterable<T> entities){
		ofy().delete(entities);
	}
	public void deleteKeys(Iterable<Key<T>> keys){
		ofy().delete(keys);
	}
	
//	public T get(Long id) throws EntityNotFoundException{
//		return ofy().get(this.clazz, id);
//	}
	public T get(Long id) throws EntityNotFoundException{
		return ofy().get(this.clazz, id);
	}
	public T get(Key<T> key) throws EntityNotFoundException{
		return ofy().get(key);
	}
	public Map<Key<T>, T> getAll(Iterable<Key<T>> keys){
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
	
	public ArrayList<T> getQuery(){
		Query<T> q = ofy().query(clazz);
		return asList(q.fetch());
	}
	
	public T getByProperty(String propName, Object propValue){
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return q.get();
	}
	public List<T> listByProperty(String propName, Object propValue){
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asList(q.fetch());
	}
	public List<Key<T>> listKeysByProperty(String propName, Object propValue){
		Query<T> q = ofy().query(clazz);
		q.filter(propName, propValue);
		return asKeyList(q.fetchKeys());
	}
	public T getByExample(T exampleObj){
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		Iterable<T> iterableResults = queryByExample.fetch();
		Iterator<T> i = iterableResults.iterator();
		T obj = i.next();
		if (i.hasNext())
			throw new RuntimeException("Too many results");
		return obj;
	}
	public List<T> listByExample(T exampleObj){
		Query<T> queryByExample = buildQueryByExample(exampleObj);
		return asList(queryByExample.fetch());
	}
	
	
	
	protected Query<T> buildQueryByExample(T exampleObj) {
		Query<T> q = ofy().query(clazz);
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

	protected List<Key<T>> asKeyList(Iterable<Key<T>> fetchKeys) {
		ArrayList<Key<T>> keys = new ArrayList<Key<T>>();
		for (Key<T> key : fetchKeys){
			keys.add(key);
		}
		return keys;
	}

	protected ArrayList<T> asList(Iterable<T> fetch) {
		ArrayList<T> list = new ArrayList<T>();
		for (T t : fetch){
			list.add(t);
		}
		return list;
	}

}
