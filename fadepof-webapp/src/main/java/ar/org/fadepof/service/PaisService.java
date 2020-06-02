package ar.org.fadepof.service;

import ar.org.fadepof.model.Pais;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author csostari
 */
@Stateless
public class PaisService {

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public List<Pais> getAll() {
        return em.createQuery("from Pais as p order by p.nombre asc", Pais.class).getResultList();
    }

    public Integer create(Pais pais) {
        em.persist(pais);
        return pais.getId();
    }
}
