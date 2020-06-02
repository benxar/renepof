package ar.org.fadepof.service;

import ar.org.fadepof.model.Especialidad;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author csostari
 */
@Stateless
public class EspecialidadService {

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public List<Especialidad> getAll() {
        return em.createQuery("from Especialidad as e order by e.nombre asc", Especialidad.class).getResultList();
    }

}
