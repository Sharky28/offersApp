package com.sharky.spring.web.DAO;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	@Autowired
	private SessionFactory sessionFactory;

	private Session session() {
		return sessionFactory.getCurrentSession();

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

	public void saveOrUpdate(Offer offer) {
		session().saveOrUpdate(offer);
	}

	public boolean delete(int id) {

		Query query = session().createQuery("delete from Offer where id = :id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;

	}

	public Offer getOffer(int id) {
		Criteria criteria = session().createCriteria(Offer.class);
		criteria.createAlias("user", "u");
		criteria.add(Restrictions.eq("u.enabled", true));
		criteria.add(Restrictions.idEq(id));

		return (Offer) criteria.uniqueResult();

	}

}
