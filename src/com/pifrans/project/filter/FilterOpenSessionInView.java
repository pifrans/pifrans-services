package com.pifrans.project.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.pifrans.framework.hibernate.session.HibernateUtil;
import com.pifrans.framework.util.FrameworkUtil;
import com.pifrans.project.listener.ContextLoadListenerServicesUtil;
import com.pifrans.project.model.classes.Entity;

@WebFilter(filterName = "connectionFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {
	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory;

	// É executado apenas uma vez, quando a aplicação está sendo iniciada
	@Override
	protected void initFilterBean() throws ServletException {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// JDBC do Spring
		BasicDataSource basicDataSource = (BasicDataSource) ContextLoadListenerServicesUtil.getBean("springDataSource");
		DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
		PlatformTransactionManager platformTransactionManager = new DataSourceTransactionManager(basicDataSource);
		TransactionStatus transactionStatus = platformTransactionManager.getTransaction(defaultTransactionDefinition);

		try {
			request.setCharacterEncoding("UTF-8"); // Define a codificação

			// Captura usuário que faz a operação
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpSession httpSession = httpServletRequest.getSession();
			Entity userLoggedIn = (Entity) httpSession.getAttribute("userLoggedIn");

			if (userLoggedIn != null) {
				FrameworkUtil.geThreadLocal().set(userLoggedIn.getEnt_id());
			}

			sessionFactory.getCurrentSession().beginTransaction();

			// Antes de executar ação (request)
			filterChain.doFilter(request, response);

			// Após a executar ação (response)
			platformTransactionManager.commit(transactionStatus);

			if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
				sessionFactory.getCurrentSession().flush();
				sessionFactory.getCurrentSession().getTransaction().commit();
			}

			if (sessionFactory.getCurrentSession().isOpen()) {
				sessionFactory.getCurrentSession().close();
			}

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
		} catch (Exception e) {
			platformTransactionManager.rollback(transactionStatus);
			e.printStackTrace();

			if (sessionFactory.getCurrentSession().getTransaction().isActive()) {
				sessionFactory.getCurrentSession().getTransaction().rollback();
			}

			if (sessionFactory.getCurrentSession().isOpen()) {
				sessionFactory.getCurrentSession().close();
			}
		} finally {
			if (sessionFactory.getCurrentSession().isOpen()) {
				if (sessionFactory.getCurrentSession().beginTransaction().isActive()) {
					sessionFactory.getCurrentSession().flush();
					sessionFactory.getCurrentSession().clear();
				}

				if (sessionFactory.getCurrentSession().isOpen()) {
					sessionFactory.getCurrentSession().close();
				}
			}
		}
	}
}
