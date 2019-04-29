package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Template;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TemplateRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TemplateRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        this.em = emf.createEntityManager();
    }

    /**
     * Get all Products including their Boms and BomLines.
     * @return a list of products with their boms and bomlines
     */
    public List<Template> findAll() throws Exception {
        return em.createNamedQuery("template.findAll", Template.class).getResultList();
    }

    /**
     * Get a template by its ID including the Boms and BomLines it has.
     * @param id of the template
     * @return a template with its boms and bomlines
     */
    public Template findById(int id) throws Exception {
        return em.createNamedQuery("template.findById", Template.class).setParameter("id", id).getSingleResult();
    }

    public List<Template> findByDefaultCode(String defaultCode) throws Exception {
        return em.createNamedQuery("template.findByDefaultCode", Template.class).setParameter("id", defaultCode).getResultList();
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
