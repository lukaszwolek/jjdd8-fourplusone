package com.infoshareacademy.dao;

import com.infoshareacademy.domain.entity.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class AuthorDao {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @PersistenceContext
    private EntityManager em;

    public void addAuthor(Author author) {
        em.persist(author);

        logger.debug("new author was created {}", author);
    }

    public Author findAuthorByName(String name) {

        Query query = em.createNamedQuery("Author.findAuthorByName");
        query.setParameter("name", name);

        List<Author> resultList = query.getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
