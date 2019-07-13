package com.pifrans.framework.implementations.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pifrans.framework.hibernate.session.HibernateUtil;
import com.pifrans.framework.interfaces.crud.CrudInterface;
import com.pifrans.project.model.classes.Entity;

@Component
@Transactional
public class CrudImplementation<T> implements CrudInterface<T> {
	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	@Autowired
	private JdbcTemplateImplementation jdbcTemplateImplementation;
	@Autowired
	private SimpleJdbcTemplateImplementation simpleJdbcTemplateImplementation;
	@Autowired
	private SimpleJdbcInsertImplementation simpleJdbcInsertImplementation;
	@Autowired
	private SimpleJdbcClassImplementation simpleJdbcClassImplementation;

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public JdbcTemplateImplementation getJdbcTemplateImplementation() {
		return jdbcTemplateImplementation;
	}

	public SimpleJdbcTemplateImplementation getSimpleJdbcTemplateImplementation() {
		return simpleJdbcTemplateImplementation;
	}

	public SimpleJdbcInsertImplementation getSimpleJdbcInsertImplementation() {
		return simpleJdbcInsertImplementation;
	}

	public SimpleJdbcClassImplementation getSimpleJdbcClassImplementation() {
		return simpleJdbcClassImplementation;
	}

	@Override
	public void save(T object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().save(object);
		executeFlushSession();
	}

	@Override
	public void persist(T object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().persist(object);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(object);
		executeFlushSession();
	}

	@Override
	public void update(T object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().update(object);
		executeFlushSession();
	}

	@Override
	public void delete(T object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().delete(object);
		executeFlushSession();
	}

	@Override
	public T merge(T object) throws Exception {
		validSessionFactory();
		object = (T) sessionFactory.getCurrentSession().merge(object);
		executeFlushSession();
		return object;
	}

	@Override
	public List<T> findList(Class<T> entity) throws Exception {
		validSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append(" select distinct(entity) from ").append(entity.getSimpleName()).append(" entity ");
		List<T> list = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		return list;
	}

	@Override
	public Object findById(Class<T> entity, Long id) throws Exception {
		validSessionFactory();
		Object object = sessionFactory.getCurrentSession().load(getClass(), id);
		return object;
	}

	@Override
	public T findForId(Class<T> entity, Long id) throws Exception {
		validSessionFactory();
		T object = (T) sessionFactory.getCurrentSession().load(getClass(), id);
		return object;
	}

	@Override
	public List<T> findListByQueryDynamic(String query) throws Exception {
		validSessionFactory();
		List<T> list = new ArrayList<T>();
		list = sessionFactory.getCurrentSession().createQuery(query).list();
		return list;
	}

	@Override
	public void executeUpdateHQLDynamic(String queryHQL) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().createQuery(queryHQL).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSQLDynamic(String querySQL) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(querySQL).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();
	}

	@Override
	public void evict(Object object) throws Exception {
		validSessionFactory();
		sessionFactory.getCurrentSession().evict(object);
	}

	@Override
	public Session getSession() throws Exception {
		validSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDynamic(String querySQL) throws Exception {
		validSessionFactory();
		List<?> list = sessionFactory.getCurrentSession().createSQLQuery(querySQL).list();
		return list;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplateImplementation;
	}

	@Override
	public SimpleJdbcTemplate geSimpleJdbcTemplate() {
		return simpleJdbcTemplateImplementation;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		return simpleJdbcInsertImplementation;
	}

	@Override
	public Long totalRecords(String table) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) from ").append(table);
		return jdbcTemplateImplementation.queryForLong(sql.toString());
	}

	@Override
	public Query getQuery(String query) throws Exception {
		validSessionFactory();
		Query queryLocal = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryLocal;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no parâmentro firstResult e obetendo o máximo de resultados
	 * passado em maximumResult
	 * 
	 * @param query
	 * @param firstResult
	 * @param maximumResult
	 * @return List<T>
	 * @throws Exception
	 */
	@Override
	public List<T> findListByQueryDynamic(String query, int firstResult, int maximumResult) throws Exception {
		validSessionFactory();
		List<T> list = new ArrayList<T>();
		list = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maximumResult).list();
		return list;
	}

	private void validTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}

	private void commitProcessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	private void rollBackProcessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}

	private void validSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		validTransaction();
	}

	/**
	 * Executa um SQL para verificar se a operação terá sucesso
	 */
	private void executeFlushSession() {
		sessionFactory.getCurrentSession().flush();
	}

	public List<Object[]> getListSQLDynamicArray(String querySQL) throws Exception {
		validSessionFactory();
		List<Object[]> list = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(querySQL).list();
		return list;
	}

	public T findUniqueByQueryDynamic(String query) throws Exception {
		validSessionFactory();
		T object = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
		return object;
	}

	public T findUniqueByProperty(Class<T> entity, Object value, String attribute, String condition) throws Exception {
		validSessionFactory();

		StringBuilder query = new StringBuilder();
		query.append(" SELECT entity FROM ").append(entity.getSimpleName()).append(" entity WHERE entity.")
				.append(attribute).append(" = '").append(value).append("' ").append(condition);

		T object = (T) this.findUniqueByQueryDynamic(query.toString());

		return object;
	}
}
