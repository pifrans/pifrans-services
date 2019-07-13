package com.pifrans.dao.implementations;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.project.model.classes.Entity;
import com.pifrans.repository.interfaces.RepositoryEntity;

@Repository
public class DaoEntity extends CrudImplementation<Entity> implements RepositoryEntity {
	private static final long serialVersionUID = 1L;

	@Override
	public Date getLastAccessEntityLogged(String name) {
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(
				"SELECT ent_lastAccess FROM entity WHERE ent_inactive is false AND ent_user = ?",
				new Object[] { name });
		return sqlRowSet.next() ? sqlRowSet.getDate("ent_lastAccess") : null;
	}

	@Override
	public void updateLastAccessUser(String name) {
		String sql = "UPDATE entity SET ent_lastAccess = current_timestamp WHERE ent_inactive is false AND ent_user = ?";
		super.getSimpleJdbcTemplateImplementation().update(sql, name);
	}

	@Override
	public boolean userExists(String username) {
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT count(1) >=1 FROM entity WHERE ent_user = '").append(username).append("' ");
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
