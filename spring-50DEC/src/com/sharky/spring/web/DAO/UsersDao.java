package com.sharky.spring.web.DAO;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("usersDAO")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;

	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	
	@Transactional
	public boolean create(User user) {
		 BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		 
		 jdbc.update("insert into users(username,password,email,enabled)values(:username,:password,:email,:enabled)",params);
		 
		 return jdbc.update("insert into authorities(username,authority)values(:username,:authority)",params)==1;		
	}
	


}
