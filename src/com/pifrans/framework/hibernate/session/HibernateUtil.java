package com.pifrans.framework.hibernate.session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.faces.bean.ApplicationScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionFactoryImplementor;

import com.pifrans.framework.implementations.crud.JNDIConnection;

/**
 * Responsável por estabelecer a conexão com o Hibernate
 * 
 * @author Lucas
 *
 */
@ApplicationScoped
public class HibernateUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	public static String JAVA_COMP_ENV_DATA_SOURCE = "java:/comp/env/jdbc/datasource";
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Responsável por ler o arquivo de configuração hibernate.cfg.xml
	 * 
	 * @return {@link SessionFactory}
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			}
			return sessionFactory;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao criar conexão SessionFactory!");
		}
	}

	/**
	 * Retorna o {@link SessionFactory} corrente
	 * 
	 * @return {@link SessionFactory}
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Retorna a sessão do {@link SessionFactory}
	 * 
	 * @return {@link SessionFactory}
	 */
	public static Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}

	/**
	 * Abre uma nova sessão no {@link SessionFactory}
	 * 
	 * @return {@link SessionFactory}
	 */
	public static Session openSession() {
		if (sessionFactory == null) {
			buildSessionFactory();
		}
		return sessionFactory.openSession();
	}

	/**
	 * Obtem a connection do provedor de conexões configurado
	 * 
	 * @return {@link Connection}
	 * @throws SQLException
	 */
	public static Connection getConnectionProvider() throws SQLException {
		return ((SessionFactoryImplementor) sessionFactory).getConnectionProvider().getConnection();
	}

	/**
	 * Connection no {@link InitialContext} java:/comp/env/jdbc/datasource
	 * 
	 * @return {@link Connection}
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		InitialContext initialContext = new InitialContext();
		DataSource dataSource = (DataSource) initialContext.lookup(JAVA_COMP_ENV_DATA_SOURCE);
		return dataSource.getConnection();
	}

	/**
	 * {@link DataSource} JNDI Tomcat
	 * 
	 * @return {@link DataSource}
	 * @throws NamingException
	 */
	public DataSource getDataSource() throws NamingException {
		InitialContext initialContext = new InitialContext();
		return (DataSource) initialContext.lookup(JNDIConnection.JAVA_COMP_ENV_DATA_SOURCE);
	}
}
