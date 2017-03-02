package com.example.hibernate;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import com.example.hibernate.model.ZDoubanFilm;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testApp() {
		try {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			SessionFactory factory = new MetadataSources().buildMetadata(registry).buildSessionFactory();
			Session session = factory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
			Root<ZDoubanFilm> root = query.from(ZDoubanFilm.class);
			Path<String> filmId = root.get("filmId");
			Path<String> filmName = root.get("filmName");
			query.multiselect(filmId, filmName);
			query.where(builder.equal(root.get("year"), "2017"), builder.isNotNull(root.get("durations")));
			query.orderBy(builder.desc(filmId));
			List<Tuple> list = session.createQuery(query).getResultList();
			list.stream().forEach(a -> {
				System.out.println(a.get(filmId) + "\t" + a.get(filmName));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
