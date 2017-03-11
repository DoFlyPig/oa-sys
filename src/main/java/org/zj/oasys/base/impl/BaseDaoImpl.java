package org.zj.oasys.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zj.oasys.base.BaseDao;
import org.zj.oasys.util.Pagination;

@Component
public class  BaseDaoImpl<T> implements BaseDao<T> {

	protected Class<T> entityClazz;  

	@Autowired
	protected SessionFactory sessionFactory;  

	@SuppressWarnings("unchecked")  
	public BaseDaoImpl() {  
		Type type = getClass().getGenericSuperclass();  
		if (type instanceof ParameterizedType) {  
			this.entityClazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];  
		} else {  
			this.entityClazz = null;  
		}  
	} 

	@Override
	public Object save(Object entity) {
		return getSession().save(entity);
	}

	@Override
	public void delete(Object entity) {
			
		getSession().delete(entity);
	}

	@Override
	public void update(Object entity) {
		getSession().update(entity);
	}

	@Override
	public void saveOrUpdate(Object entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveAll(Collection<?> entities) {
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Object entity = (Object) iterator.next();
			getSession().save(entity);
			
		}
	}

	@Override
	public void deleteAll(Collection<?> entities) {
		
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Object entity = (Object) iterator.next();
			getSession().delete(entity);
			
		}
	}

	@Override
	public void updateAll(Collection<?> entities) {
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Object entity = (Object) iterator.next();
			getSession().update(entity);
			
		}
	}

	@Override
	public void saveOrUpdateAll(Collection<?> entities) {
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Object entity = (Object) iterator.next();
			getSession().saveOrUpdate(entity);
			
		}
	}

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		
		return getSession().get(entityClass, id);
	}

	@Override
	public <T> T get(CharSequence queryString, Map<String, Object> params) {
		Query qry = getSession().createQuery(queryString.toString());
		qry = setParameter(qry, params);
		List list = qry.setMaxResults(1).list();
		if(list.isEmpty())
			return null;
		return (T) list.get(0);
	}

	@Override
	public <T> T get(CharSequence queryString, Object... params) {
		Query qry = getSession().createQuery(queryString.toString());
		for (int i = 0; i < params.length; i++) {
			Object object = params[i];
			qry.setParameter(i, object);
		}
		List list = qry.setMaxResults(1).list();
		if(list.isEmpty())
			return null;
		return (T)list.get(0);
	}

	@Override
	public <T> List<T> findList(CharSequence queryString, Object... params) {
		Query qry = getSession().createQuery(queryString.toString());
		for (int i = 0; i < params.length; i++) {
			Object object = params[i];
			qry.setParameter(i, object);
		}
		
		return qry.setMaxResults(1).list();
	}

	@Override
	public <T> List<T> findList(CharSequence queryString, Map<String, Object> params) {
		
		Query qry = getSession().createQuery(queryString.toString());
		
		qry = setParameter(qry, params);
		
		return qry.list();
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString, int pageIndex, int pageSize, Object... params) {
		
		
	        return null;  
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString, Map<String, Object> params, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString, CharSequence countString, int pageIndex,
			int pageSize, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findPagination(CharSequence queryString, CharSequence countString,
			Map<String, Object> params, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Pagination<T> findSqlPagination(CharSequence queryString, CharSequence countString,
			Map<String, Object> params, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeSqlUpdate(String sql) {
		
		return getSession().createNativeQuery(sql).executeUpdate();
	}

	@Override
	public int executeUpdate(String hql) {
		return getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public T getById(Serializable id) {
		return getSession().get(entityClazz, id);
	}

	@Override
	public T saveEntity(T o) {
		saveOrUpdate(o);
		return o;
	}

	@Override
	public T insert(T o) {
		save(o);
		return o;
	}

	@Override
	public void save(List<T> list) {
		saveOrUpdateAll(list);
	}

	@Override
	public void insert(List<T> list) {
		for (T entity : list) {  
            save(entity);  
        } 
	}

	@Override
	public void delete(List<T> list) {
		for (T entity : list) {  
            delete(entity);  
        } 
	}

	@Override
	public void update(List<T> list) {
		for (T entity : list) {  
            update(entity);  
        }  

	}

	@Override
	public List<T> findByProperty(String name, Object value) {
	      String hql = "from  " + entityClazz.getSimpleName() + " where " + name + "=? ";  
	        return findList(hql, value);  
	}

	@Override
	public List<T> findByProperty(Map<String, Object> conditionMap) {
		 StringBuilder hql = new StringBuilder();  
	        hql.append("from  " + entityClazz.getSimpleName());  
	        if (!conditionMap.isEmpty()) {  
	            Iterator<String> it = conditionMap.keySet().iterator();  
	            String key = it.next();  
	            hql.append(" where  " + key + "=:" + key);  
	            while (it.hasNext()) {  
	                key = it.next();  
	                hql.append(" and  " + key + "=:" + key);  
	            }  
	        }  
	        return findList(hql.toString(), conditionMap); 
	}

	@Override
	public <V> List<V> findListByMax(CharSequence queryString, int maxResults, Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> List<V> findListByMax(CharSequence queryString, int maxResults, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {  
		this.sessionFactory = sessionFactory;  
	}  

	protected Session getSession() {  
		return sessionFactory.getCurrentSession();  
	}  
	
	
	  protected Query setParameter(Query query, Map<String, Object> parameterMap) {  
	        for (
	        Iterator iterator = parameterMap.keySet().iterator(); iterator.hasNext();) {  
	            String key = (String) iterator.next();  
	            query.setParameter(key, parameterMap.get(key));  
	        }  
	        return query;  
	    }
}
