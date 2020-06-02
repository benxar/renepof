package ar.org.fadepof.service;

import ar.org.fadepof.model.Database;
import ar.org.fadepof.model.Disorder;
import ar.org.fadepof.model.Enfermedad;
import ar.org.fadepof.model.Establecimiento;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Stateless
public class EnfermedadService {

    private static Logger log = Logger.getLogger(EnfermedadService.class.getName());

    @Inject
    private SearchIndexService searchIndexService;

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public List<Enfermedad> getEnfermedadByNombre(String nombre) {

        List<Enfermedad> result = new ArrayList<Enfermedad>();

        if (nombre != null && !"".equals(nombre.trim())) {
            searchIndexService.index();
            log.info("buscando por: " + nombre);
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder enfermedadDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Enfermedad.class).get();
            Query fullTextQuery = enfermedadDB.keyword().onField("nombre").matching(nombre).createQuery();
            result = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();
        }

        return result;
    }

    public Enfermedad update(Enfermedad enfermedad) {
        return em.merge(enfermedad);
    }

    public Enfermedad create(Enfermedad enfermedad) {
        em.persist(enfermedad);
        return enfermedad;
    }
}

