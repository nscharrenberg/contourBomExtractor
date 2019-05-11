package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Template;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TemplateRepository {
    private EntityManagerFactory emf;
    private EntityManager em;

    private static final Logger LOGGER = LogManager.getLogger("ContourBomExporter");

    public TemplateRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        LOGGER.info("Entity Manager Factory has been created with persistenceUnitName: contourDB");

        this.em = emf.createEntityManager();
        LOGGER.info("Entity Manager has been created");
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
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            LOGGER.info("Entitymanager Transaction activated during creation of BillOfMaterial");
        }

        if(!em.contains(billOfMaterial)) {
            em.persist(billOfMaterial);
            LOGGER.info("BillOfMaterial persisted");

            em.flush();
            LOGGER.info("BillOfMaterial Flushed");
        }

        em.getTransaction().commit();
        LOGGER.info("BillOfMaterial Transaction Committed");
    }
}
