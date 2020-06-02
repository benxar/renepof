package ar.org.fadepof.service;

import ar.org.fadepof.model.Disorder;
import ar.org.fadepof.model.Enfermedad;
import ar.org.fadepof.model.Organizacion;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * @author csostari
 */
@Stateless
public class OrganizacionService {

    private static Logger log = Logger.getLogger(OrganizacionService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    @Inject
    private SearchIndexService searchIndexService;

    @Inject
    private DisorderService disorderService;

    public List<Organizacion> getAll() {
        return em.createQuery("from Organizacion", Organizacion.class).getResultList();
    }

    public Organizacion getOrganizacionByCodigo(Long codigo) {
        return em.find(Organizacion.class, codigo);
    }

    public List<Organizacion> getOrganizacionByNombre(String nombre) {

        List<Organizacion> organizaciones = new ArrayList<Organizacion>();

        log.info("buscando por: " + nombre);
        searchIndexService.index();
        FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
        QueryBuilder organizacionesDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Organizacion.class).get();

        StringTokenizer tokenizer = new StringTokenizer(nombre);

        Query fullTextQuery = organizacionesDB.keyword().fuzzy().onField("organizacion").matching(nombre).createQuery();
        List<Organizacion> aux = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();

        List<List> allMatches = new ArrayList();
        while (tokenizer.hasMoreElements()) {
            fullTextQuery = organizacionesDB.keyword().fuzzy().onField("organizacion").matching(tokenizer.nextElement()).createQuery();
            allMatches.add(fullTextEm.createFullTextQuery(fullTextQuery).getResultList());
        }

        log.info("pre filtro: "+aux.size()+" resultados");
        int i = 0;
        for (List<Organizacion> matches: allMatches) {
            log.info("resultado "+ i++ +" tuvo "+matches.size() + " hits");
            aux.retainAll(matches);
            log.info("filtro: "+ i + " " +organizaciones.size()+" resultados");
        }
        log.info("post filtro: "+organizaciones.size()+" resultados");
        for (Organizacion organizacion: aux) {
            log.info("resultado: "+organizacion.getNombre());
        }

        organizaciones.addAll(aux);
        return organizaciones;
    }

    public List <Organizacion> getOrganizacionesByOrphaNumber(Long orphaNumber) {
        List<Organizacion> results = new ArrayList<>();
        Enfermedad enfermedad = disorderService.getDiseaseByOrphaNumber(orphaNumber);
        if (enfermedad != null) {
            results = enfermedad.getOrganizaciones();
        }
        return results;
    }

    public List <Organizacion> getOrganizacionesByFadepofNumber(String fadepofNumber) {
        List<Organizacion> results = new ArrayList<>();
        Enfermedad enfermedad = disorderService.getDiseaseByFadepofNumber(fadepofNumber);
        if (enfermedad != null) {
            results = enfermedad.getOrganizaciones();
        }
        return results;
    }

    public Organizacion create(Organizacion organizacion) {
        if (organizacion.getCodigosEnfermedad() != null && !organizacion.getCodigosEnfermedad().isEmpty()) {
            for (Long orpha: organizacion.getCodigosEnfermedad()) {
                try {
                    organizacion.getEnfermedades().add(disorderService.getDiseaseByOrphaNumber(orpha));
                } catch (Exception ex) {
                    log.warn(ex.getCause());
                }
            }
        }
        em.merge(organizacion);
        return organizacion;
    }

    public Organizacion update(Organizacion organizacion) {
        em.merge(organizacion);
        return organizacion;
    }

}
