package com.nscharrenberg.contour.controllers;

import com.nscharrenberg.contour.domain.Bom;
import com.nscharrenberg.contour.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Controller {
    public Controller() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("contourDB");
            EntityManager em = emf.createEntityManager();

            List<Product> products = em.createNamedQuery("product.findAll", Product.class).getResultList();

            System.out.println(products.size());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
