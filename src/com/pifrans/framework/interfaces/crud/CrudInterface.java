package com.pifrans.framework.interfaces.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface CrudInterface<T> extends Serializable {

	// Salvar dados
	public void save(T object) throws Exception;

	public void persist(T object) throws Exception;

	public void saveOrUpdate(T object) throws Exception;

	public void update(T object) throws Exception;

	public void delete(T object) throws Exception;

	// Salva ou atualiza e retorna o objeto em estado persistente
	public T merge(T object) throws Exception;

	// Carrega a lista de dados de determinada classe
	public List<T> findList(Class<T> entity) throws Exception;

	public Object findById(Class<T> entity, Long id) throws Exception;

	public T findForId(Class<T> entity, Long id) throws Exception;

	public List<T> findListByQueryDynamic(String query) throws Exception;

	public void executeUpdateHQLDynamic(String queryHQL) throws Exception;

	public void executeUpdateSQLDynamic(String querySQL) throws Exception;

	// Limpa a sessão do Hibernate
	public void clearSession() throws Exception;

	// Retira um objeto da sessão do Hibernate
	public void evict(Object object) throws Exception;

	public Session getSession() throws Exception;

	public List<?> getListSQLDynamic(String querySQL) throws Exception;

	public JdbcTemplate getJdbcTemplate();

	public SimpleJdbcTemplate geSimpleJdbcTemplate();

	public SimpleJdbcInsert getSimpleJdbcInsert();

	public Long totalRecords(String table) throws Exception;

	public Query getQuery(String query) throws Exception;

	// Carregamento dinâmico com JSF e Primefaces
	public List<T> findListByQueryDynamic(String query, int firstResult, int maximumResult) throws Exception;
}
