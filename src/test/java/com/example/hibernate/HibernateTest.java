package com.example.hibernate;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.example.hibernate.model.ZDoubanFilm;

/**
 * Unit test for simple App.
 */
public class HibernateTest {
	@Test
	public void testApp() {
		try {
			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure();
			StandardServiceRegistry registry = registryBuilder.build();
			MetadataSources sources = new MetadataSources();
			Metadata metadata = sources.buildMetadata(registry);
			SessionFactory factory = metadata.buildSessionFactory();
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

	private static final String local = "D:/TDDOWNLOAD/hibernate/userguide/";
	private static final String remote = "http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/";

	@Test
	public void downDocs() {
		search(remote);
		System.out.println("下载完成！");
	}

	private void search(String url) {
		Document doc = null;
		try {
			System.out.println(url);
			doc = Jsoup.connect(url).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}
		Elements body = doc.body().select("table > tbody > tr > td > a");
		if (body.isEmpty()) {
			return;
		}
		body.stream().filter(a -> !a.text().contentEquals("Parent Directory")).forEach(b -> {
			String path = b.attr("href");
			String fullPath = url + path;
			if (FilenameUtils.getExtension(path).isEmpty()) {
				search(fullPath);
			} else {
				try {
					URL source = new URL(fullPath);
					File destination = new File(fullPath.replace(remote, local));
					FileUtils.copyURLToFile(source, destination);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
