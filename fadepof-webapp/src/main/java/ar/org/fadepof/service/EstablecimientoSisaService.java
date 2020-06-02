package ar.org.fadepof.service;

import ar.org.fadepof.model.*;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author csostari
 */
@Stateless
public class EstablecimientoSisaService {

    private static Logger log = Logger.getLogger(EstablecimientoSisaService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    @Inject
    private SearchIndexService searchIndexService;


    public List<EstablecimientoSisa> getAll() {
        return em.createQuery("from EstablecimientoSisa", EstablecimientoSisa.class).getResultList();
    }

    private EstablecimientoSisa find(Long codigo) {

        EstablecimientoSisa establecimiento = null;

        List<EstablecimientoSisa> results = em
                .createQuery("from EstablecimientoSisa where id = :id", EstablecimientoSisa.class)
                .setParameter("id", codigo)
                .getResultList();

        if (results != null && !results.isEmpty()) {
            establecimiento = results.get(0);
        }
        return establecimiento;
    }

    public List<EstablecimientoSisa> getByNombre(String nombre) {

        List<EstablecimientoSisa> result = new ArrayList<EstablecimientoSisa>();

        if (nombre != null && !"".equals(nombre.trim())) {
            searchIndexService.index();
            log.info("buscando por: " + nombre);
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder establecimientoSisaDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(EstablecimientoSisa.class).get();
            Query fullTextQuery = establecimientoSisaDB.keyword().onField("nombreEstablecimiento").matching(nombre).createQuery();
            result = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();
        }

        return result;
    }

    public EstablecimientoSisa getByCodigo(Long codigo) {

        log.info("buscando por codigo SISA: "+codigo);

        EstablecimientoSisa establecimiento = null;

        if ( codigo != null ) {
            establecimiento = find(codigo);
        }
        return establecimiento;
    }

    public List<EstablecimientoSisa> getByProvincia(String provincia) {
        return em
                .createQuery("from EstablecimientoSisa where provincia =:provincia", EstablecimientoSisa.class)
                .setParameter("provincia", provincia).getResultList();
    }

    public EstablecimientoSisa create(EstablecimientoSisa establecimiento) {
        em.persist(establecimiento);
        return establecimiento;
    }

    public EstablecimientoSisa update(EstablecimientoSisa establecimiento) {
        em.merge(establecimiento);
        return establecimiento;
    }

    private void populate(Direccion direccion) {
        direccion.setLocalidadText(" ");
        direccion.setProvinciaText(direccion.getProvinciaLibre());
    }

    private String getLocalidadBy(String codigo) {
        return em.find(Localidad.class, Integer.valueOf(codigo)).getNombre();
    }

    private String getProvinciaBy(String codigo) {
        return em.find(Provincia.class, Integer.valueOf(codigo)).getNombre();
    }
}
