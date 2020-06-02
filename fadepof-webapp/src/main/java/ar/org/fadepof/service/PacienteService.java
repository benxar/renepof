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
import java.util.Date;
import java.util.List;

@Stateless
public class PacienteService {

    private static Logger log = Logger.getLogger(PacienteService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    @Inject
    private SearchIndexService searchIndexService;

    @Inject
    private DisorderService disorderService;

    @Inject
    private DiagnosticoService diagnosticoService;

    @Inject
    private EstablecimientoService establecimientoService;

    public Paciente find(Long id) {
        return em.find(Paciente.class, id);
    }

    public Paciente update(Paciente paciente) {
        return em.merge(paciente);
    }

    public Paciente create(Paciente paciente) {
        this.purge(paciente);
        try {
            for(Patologia patologia: paciente.getPatologias()) {
                patologia.getDiagnostico().setFechaActualizacion(new Date());
                patologia.getDiagnosticos().add(patologia.getDiagnostico());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        em.persist(paciente);
        return paciente;
    }

    private void validate(Paciente paciente) {
        if (paciente.getPatologias() != null && paciente.getPatologias().get(0) != null) {
            validate(paciente.getPatologias().get(0));
        }
    }

    private void validate(Patologia patologia) {
        if (patologia != null) {
            validate(patologia.getDiagnostico());
            validate(patologia.getTratamiento());
        }
    }

    private void validate(Diagnostico diagnostico) {
        if (diagnostico != null) {
            validate(diagnostico.getEstablecimiento());
            validate(diagnostico.getProfesional());
        }
    }

    private void validate(Tratamiento tratamiento) {
        if (tratamiento != null) {
            for (Establecimiento establecimiento: tratamiento.getEstablecimientos()) {
                validate(establecimiento);
            }
        }
    }

    private void validate(Establecimiento establecimiento) {
        if (establecimiento != null) {
            if (establecimiento.getId() != null) {
                establecimientoService.update(establecimiento);
            } else {
                establecimientoService.create(establecimiento);
            }
        }
    }

    private void validate(Profesional profesional) {
        if (profesional != null) {

        }
    }

    public List<Paciente> getPacientesByCriteria(String criteria) {
        List<Paciente> result = new ArrayList<Paciente>();

        if (criteria != null && !"".equals(criteria.trim())) {
            searchIndexService.index();
            log.info("buscando por: " + criteria);
            FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
            QueryBuilder enfermedadDB = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Paciente.class).get();
            Query fullTextQuery = enfermedadDB.keyword().onField("apellidos").matching(criteria).createQuery();
            result = fullTextEm.createFullTextQuery(fullTextQuery).getResultList();
        }

        return result;
    }

    public Diagnostico update(Long pacienteId, Diagnostico diagnostico) {
        Paciente paciente = em.find(Paciente.class, pacienteId);
        return em.merge(diagnostico);
    }

    public Paciente create(Long pacienteId, Patologia patologia) {
        Paciente paciente = em.find(Paciente.class, pacienteId);
        Diagnostico diagnostico = diagnosticoService.create(patologia.getDiagnostico());
        diagnostico.setFechaActualizacion(new Date());
        patologia.setDiagnostico(diagnostico);
        patologia.getDiagnosticos().add(diagnostico);
        paciente.getPatologias().add(patologia);
        return em.merge(paciente);
    }

    public Paciente create(Long pacienteId, Long patologiaId, Diagnostico diagnostico) {
        Paciente paciente = em.find(Paciente.class, pacienteId);
        Patologia patologia = this.findPatologia(paciente, patologiaId);
        if (patologia != null) {

            diagnostico.setId(null);

            if (diagnostico.getEstablecimiento() != null) {
                diagnostico.setEstablecimiento(null);
            }

            if (diagnostico.getEnfermedad() != null) {
                diagnostico.getEnfermedad().setId(null);
            }

            if (diagnostico.getProfesional() != null) {
                diagnostico.setProfesional(null);
            }

            diagnostico.setFechaActualizacion(new Date());

            /*
            if (patologia.getDiagnostico() != null) {
                patologia.getDiagnosticos().add(patologia.getDiagnostico());
            }
            */

            patologia.setDiagnostico(diagnostico);
            patologia.getDiagnosticos().add(diagnostico);

            /*
            em.find(Diagnostico.class, diagnostico.getId());
            em.persist(diagnostico);
            patologia.getDiagnosticos().add(patologia.getDiagnostico());
            patologia.setDiagnostico(diagnostico);
            */
        }
        return em.merge(paciente);
    }

    private Patologia findPatologia(Paciente paciente, Long patologiaId) {
        for (Patologia patologia: paciente.getPatologias()) {
            if (patologia.getId().equals(patologiaId)) {
                return patologia;
            }
        }
        return null;
    }

    public Diagnostico create(Long pacienteId, Diagnostico diagnostico) {
        Paciente paciente = em.find(Paciente.class, pacienteId);
        if (diagnostico.getEnfermedad() != null && paciente != null) {
            Enfermedad enfermedad = disorderService.getDiseaseByOrphaNumber(diagnostico.getEnfermedad().getOrphaNumber());
            diagnostico.setEnfermedad(enfermedad);
            em.persist(paciente);
        }
        return diagnostico;
    }

    public Patologia update(Patologia patologia) {
        return em.merge(patologia);
    }

    public Tratamiento create(Long diagnosticoId, Tratamiento tratamiento) {
        Diagnostico diagnostico = em.find(Diagnostico.class, diagnosticoId);
        tratamiento.setDiagnostico(diagnostico);
        em.persist(tratamiento);
        return tratamiento;
    }

    private void purge(Paciente paciente) {
        for (Patologia patologia: paciente.getPatologias()) {
            try {
                if (patologia.getDiagnostico().getEstablecimiento() != null) {
                    patologia.getDiagnostico().getEstablecimiento().setId(null);
                }
                if (patologia.getDiagnostico().getEstablecimiento().getNombre() == null || patologia.getDiagnostico().getEstablecimiento().getNombre().trim().equals("")) {
                    patologia.getDiagnostico().setEstablecimiento(null);
                }
            } catch (Exception ex) {
                log.warn(ex.getMessage());
            }
            try {
                for (Establecimiento establecimiento: patologia.getTratamiento().getEstablecimientos()) {
                    if (establecimiento != null) {
                        establecimiento.setId(null);
                    }
                    if (establecimiento.getNombre() == null || establecimiento.getNombre().trim().equals("")) {
                        patologia.getTratamiento().getEstablecimientos().remove(establecimiento);
                    }
                    if (establecimiento.getProfesional() != null) {
                        establecimiento.setProfesional(null);
                    }
                }
            } catch (Exception ex) {
                log.warn(ex.getMessage());
            }
        }
    }

    public Patologia remove(Paciente paciente, Long diagnosticoId) {
        for(Patologia patologia: paciente.getPatologias()) {
            for (Diagnostico diagnostico: patologia.getDiagnosticos()) {
                if (diagnostico.getId().equals(diagnosticoId)) {
                    em.remove(diagnostico);
                    patologia.getDiagnosticos().remove(diagnostico);
                    return patologia;
                }
            }
        }
        return null;
    }
}
