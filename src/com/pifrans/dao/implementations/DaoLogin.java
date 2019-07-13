package com.pifrans.dao.implementations;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.pifrans.framework.implementations.crud.CrudImplementation;
import com.pifrans.repository.interfaces.RepositoryLogin;

@Repository
public class DaoLogin extends CrudImplementation<Object> implements RepositoryLogin {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isAuthentic(String user, String password) throws Exception {
		String sql = "SELECT count(1) as authentic FROM entity WHERE ent_user = ? AND ent_password = ? ";
		SqlRowSet sqlRowSet = super.getJdbcTemplate().queryForRowSet(sql, new Object[] { user, password });
		return sqlRowSet.next() ? sqlRowSet.getInt("authentic") > 0 : false;
	}

}
