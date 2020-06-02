package ar.org.fadepof.service;

import ar.org.fadepof.model.Localidad;
import ar.org.fadepof.model.Provincia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author csostari
 */
@Stateless
public class ProvinciaService {

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public List<Provincia> getBy(String codigoPais) {
        return em.createQuery("from Provincia as p where codigo_pais = :codigoPais order by p.nombre asc", Provincia.class)
                .setParameter("codigoPais", codigoPais).getResultList();
    }

    public List<Localidad> getLocalidadesBy(Integer id) {
        return em.find(Provincia.class, id).getLocalidades();
    }
}
