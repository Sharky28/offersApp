package com.sharky.spring.web.DAO;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@org.springframework.transaction.annotation.Transactional
@Component("usersDAO")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SessionFactory sessionFcatory;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public org.hibernate.Session session(){
		return sessionFcatory.getCurrentSession();
	}
	
	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
			
	}


	public boolean exists(String username) {
		
		return jdbc.queryForObject("select count(*) from users where username=:username", 
				new MapSqlParameterSource("username",username),Integer.class)>0;
	}


	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		return session().createQuery("from User").list();
	}
	


}
