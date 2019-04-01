package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProductRepository {
    @PersistenceContext(name = "contourDB")
    private EntityManager em;

    public List<Product> findAll() {
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }
}
