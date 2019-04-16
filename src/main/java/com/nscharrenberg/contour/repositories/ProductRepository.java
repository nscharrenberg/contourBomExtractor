package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ProductRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        this.em = emf.createEntityManager();
    }

    /**
     * Get all Products including their Boms and BomLines.
     * @return a list of products with their boms and bomlines
     */
    public List<Product> findAll() throws Exception {
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }

    /**
     * Get a product by its ID including the Boms and BomLines it has.
     * @param id of the product
     * @return a product with its boms and bomlines
     */
    public Product findById(int id) throws Exception {
        return em.createNamedQuery("product.findById", Product.class).setParameter("id", id).getSingleResult();
    }

    /**
     * Write a BillOfMaterial to the database
     * @param billOfMaterial that has to be saved on the database
     */
    public void createBillOfMaterial(BillOfMaterial billOfMaterial) throws Exception {
        em.getTransaction().begin();
        if(!em.contains(billOfMaterial)) {
            em.persist(billOfMaterial);
            em.flush();
        }

        em.getTransaction().commit();
    }
}
