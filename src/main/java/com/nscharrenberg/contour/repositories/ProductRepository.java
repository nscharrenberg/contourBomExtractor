package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepository {
    EntityManagerFactory emf;
    EntityManager em;

    public ProductRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        this.em = emf.createEntityManager();
    }

    public List<Product> findAll() {
        try {
            return em.createNamedQuery("product.findAll", Product.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Product findById(int id) {
        try {
            return em.createNamedQuery("product.findById", Product.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
