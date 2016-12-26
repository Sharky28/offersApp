package com.sharky.spring.web.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("offersDAO")
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Offer> getOffers() {
		return jdbc.query("select * from offers,users where offers.username=users.username",
				new OfferRowMapper());

	}

	public List<Offer> getOffers(String username) {

		MapSqlParameterSource params = new MapSqlParameterSource("username", username);
		return jdbc.query(
				"Select* from offers, users where offers.username=users.username and users.enabled=true and offers.username= :username",
				params, new OfferRowMapper());
	}

	public boolean create(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("insert into offers(username,text)values(:username,:text)", params) == 1;
	}

	public boolean update(Offer offer) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		return jdbc.update("update offers set text=:text where id =:id", params) == 1;
	}

	public boolean delete(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource("id", id);
		return jdbc.update("delete from offers where id = :id", param) == 1;
	}

	public Offer getOffer(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		return jdbc.queryForObject(
				"Select* from offers, users where offers.username=users.username and users.enabled=true and id = :id;",
				params, new OfferRowMapper()

		);
	}

}
