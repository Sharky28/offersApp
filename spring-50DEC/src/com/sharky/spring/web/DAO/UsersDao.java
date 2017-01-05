package com.sharky.spring.web.DAO;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@org.springframework.transaction.annotation.Transactional
@Component("usersDAO")
public class UsersDao {



	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFcatory;


	public org.hibernate.Session session() {
		return sessionFcatory.getCurrentSession();
	}

	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);

	}

	public boolean exists(String username) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.idEq(username));
		User user = (User) criteria.uniqueResult();
		return user!=null;

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		return session().createQuery("from User").list();
	}

	public User getUser(String username) {
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.idEq(username));
		return (User) criteria.uniqueResult();
		
	}

}
