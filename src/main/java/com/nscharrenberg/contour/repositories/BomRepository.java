package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Bom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BomRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    public BomRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        this.em = emf.createEntityManager();
    }

    /**
     * Get all Products including their Boms and BomLines.
     * @return a list of products with their boms and bomlines
     */
    public List<Bom> findAll() throws Exception {
        return em.createNamedQuery("bom.findAll", Bom.class).getResultList();
    }

    /**
     * Get a bom by its ID including the Boms and BomLines it has.
     * @param id of the bom
     * @return a bom with its boms and bomlines
     */
    public Bom findById(int id) throws Exception {
        return em.createNamedQuery("bom.findById", Bom.class).setParameter("id", id).getSingleResult();
    }

    public List<Bom> findByDefaultCode(String defaultCode) throws Exception {
        return em.createNamedQuery("bom.findByDefaultCode", Bom.class).setParameter("id", defaultCode).getResultList();
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
