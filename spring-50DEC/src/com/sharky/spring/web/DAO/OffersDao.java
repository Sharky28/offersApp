package com.sharky.spring.web.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("offersDAO")
public class OffersDao {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		return sessionFactory.getCurrentSession();

	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers() {
		Criteria criteria = session().createCriteria(Offer.class);
		criteria.createAlias("user", "u").add(Restrictions.eq("u.enabled", true));
		return criteria.list();

	}

	@SuppressWarnings("unchecked")
	public List<Offer> getOffers(String username) {
		Criteria criteria = session().createCriteria(Offer.class);
		criteria.createAlias("user", "u");
		criteria.add(Restrictions.eq("u.enabled", true));
		criteria.add(Restrictions.eq("u.username", username));
		return criteria.list();

	}

	public void create(Offer offer) {
		session().save(offer);
	}

	public void update(Offer offer) {
		session().update(offer);
		
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
