package ar.org.fadepof.service;

import ar.org.fadepof.external.SISAClient;
import ar.org.fadepof.external.SISATypesFactory;
import ar.org.fadepof.model.*;

import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author csostari
 */
@Stateless
public class EstablecimientoService {

    private static Logger log = Logger.getLogger(EstablecimientoService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    @Inject
    private SearchIndexService searchIndexService;


    public List<Establecimiento> getAll() {
        return em.createQuery("from Establecimiento", Establecimiento.class).getResultList();
    }

    private Establecimiento find(Long codigo) {

        Establecimiento establecimiento = null;

        List<Establecimiento> results = em.createQuery("from Establecimiento where codigo = :codigo", Establecimiento.class)
                .setParameter("codigo", codigo).getResultList();
        if (results != null && !results.isEmpty()) {
            establecimiento = results.get(0);
        }
        return establecimiento;
    }

    public List<Establecimiento> getByNombre(String nombre) {
        SISAClient sisa = new SISAClient();
        return SISATypesFactory.getEstablecimiento(sisa.getEstablecimientosByNombre(nombre));

    }

    public Establecimiento getByCodigo(Long codigo) {

        log.info("buscando por codigo SISA: "+codigo);

        Establecimiento establecimiento = null;

        if ( codigo != null ) {
            SISAClient sisa = new SISAClient();
            establecimiento = SISATypesFactory.getEstablecimiento(sisa.getEstablecimientosByCodigo(codigo));
            Establecimiento establecimientoLocal = find(codigo);
            if (establecimientoLocal != null) {
                establecimiento.setId(establecimientoLocal.getId());
            }
        }
        return establecimiento;
    }

    /*
    public List<Establecimiento> getByNombre(String nombre) {
        List<Establecimiento> result = new ArrayList<Establecimiento>();
        if (nombre != null && !"".equals(nombre.trim())) {
            searchIndexService.index();
            log.info("buscando por: " + nombre);
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder disorderDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Establecimiento.class).get();
            Query fullTextQuery = disorderDB.keyword().onField("nombre").matching(nombre).createQuery();
            result = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();
        }
        return result;
    }*/

    public List<Establecimiento> getByProvincia(String provincia) {
        return em.createQuery("from Establecimiento where provincia =:provincia", Establecimiento.class)
                .setParameter("provincia", provincia).getResultList();
    }

    public List <Establecimiento> getOrganizacionesByOrphaNumber(Long orphaNumber) {
        Set<Establecimiento> establecimientos = new LinkedHashSet<>();
        List<Diagnostico> diagnosticos = em.createQuery("from Diagnostico d where d.enfermedad.orphaNumber =:orphaNumber", Diagnostico.class)
                .setParameter("orphaNumber", orphaNumber).getResultList();
        for (Diagnostico diagnostico: diagnosticos) {
            Establecimiento establecimiento = diagnostico.getEstablecimiento();
            try {
                if (establecimiento != null) {
                    populate(establecimiento.getDireccion());
                    Profesional profesional = diagnostico.getProfesional();
                    if (profesional != null && profesional.getApellidos() != null && !profesional.getApellidos().trim().equals("")) {
                        establecimiento.getProfesionales().add(profesional);
                    }
                    if (establecimiento.getNombre() != null && !establecimiento.getNombre().trim().equals("")) {
                        if (!exist(establecimiento, establecimientos)) {
                            establecimientos.add(establecimiento);
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return new ArrayList<>(establecimientos);
    }

    public List <Establecimiento> getOrganizacionesByFadepofNumber(String fadepofNumber) {
        Set<Establecimiento> establecimientos = new LinkedHashSet<>();
        List<Diagnostico> diagnosticos = em.createQuery("from Diagnostico d where d.enfermedad.fadepofNumber =:fadepofNumber", Diagnostico.class)
                .setParameter("fadepofNumber", fadepofNumber).getResultList();
        for (Diagnostico diagnostico: diagnosticos) {
            Establecimiento establecimiento = diagnostico.getEstablecimiento();
            try {
                if (establecimiento != null) {
                    populate(establecimiento.getDireccion());
                    Profesional profesional = diagnostico.getProfesional();
                    if (profesional != null && profesional.getApellidos() != null && !profesional.getApellidos().trim().equals("")) {
                        establecimiento.getProfesionales().add(profesional);
                    }
                    if (establecimiento.getNombre() != null && !establecimiento.getNombre().trim().equals("")) {
                        if (!exist(establecimiento, establecimientos)) {
                            establecimientos.add(establecimiento);
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return new ArrayList<>(establecimientos);
    }


    private boolean exist(Establecimiento establecimiento, Set<Establecimiento> establecimientos) {
        for (Establecimiento aux: establecimientos) {
            if (establecimiento != null && establecimiento.getNombre() != null && aux != null && aux.getNombre() != null &&
                establecimiento.getNombre().equals(aux.getNombre())) {
                return true;
            }
        }
        return false;
    }

    public Establecimiento create(Establecimiento establecimiento) {
        em.persist(establecimiento);
        return establecimiento;
    }

    public Establecimiento update(Establecimiento establecimiento) {
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
