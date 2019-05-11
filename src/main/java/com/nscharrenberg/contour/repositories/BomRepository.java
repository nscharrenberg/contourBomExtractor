package com.nscharrenberg.contour.repositories;

import com.nscharrenberg.contour.domain.BillOfMaterial;
import com.nscharrenberg.contour.domain.Bom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BomRepository {

    private EntityManagerFactory emf;
    private EntityManager em;

    private static final Logger LOGGER = LogManager.getLogger("ContourBomExporter");

    public BomRepository() {
        this.emf = Persistence.createEntityManagerFactory("contourDB");
        LOGGER.info("Entity Manager Factory has been created with persistenceUnitName: contourDB");

        this.em = emf.createEntityManager();
        LOGGER.info("Entity Manager has been created");
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
        if(!em.getTransaction().isActive()) {
            em.getTransaction().begin();
            LOGGER.info("Entitymanager Transaction activated during creation of BillOfMaterial");
        }

        if(!em.contains(billOfMaterial)) {
            em.persist(billOfMaterial);
            LOGGER.info("Bom persisted");

            em.flush();
            LOGGER.info("Bom Flushed");
        }

        em.getTransaction().commit();
        LOGGER.info("Bom Transaction Committed");
    }
}
