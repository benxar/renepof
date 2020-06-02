package ar.org.fadepof.service;

import ar.org.fadepof.model.*;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DiagnosticoService {

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    @Inject
    DisorderService disorderService;

    @Inject
    EstablecimientoService establecimientoService;

    @Inject
    ProfesionalService profesionalService;

    public Diagnostico create(Diagnostico diagnostico) {
        Enfermedad enfermedad = disorderService.getDiseaseByOrphaNumber(diagnostico.getEnfermedad().getOrphaNumber());
//        Establecimiento establecimiento = establecimientoService.getByCodigo(diagnostico.getCodigoEstablecimiento());
//        Profesional profesional = profesionalService.getProfesionalBy(diagnostico.getCodigoProfesional(), diagnostico.getServicio(), diagnostico.getEspecialidad());
        diagnostico.setEnfermedad(enfermedad);
        diagnostico.setEstablecimiento(null);
        diagnostico.setProfesional(null);
        em.merge(diagnostico);
        return diagnostico;
    }

    public Diagnostico update(Diagnostico diagnostico) {

        Diagnostico.TipoDiagnostico tipoDiagnostico = diagnostico.getTipoDiagnostico();

        Profesional profesional = diagnostico.getProfesional();
        Enfermedad enfermedad = disorderService.getDiseaseByOrphaNumber(diagnostico.getEnfermedad().getOrphaNumber());
        Establecimiento establecimiento = establecimientoService.getByCodigo(diagnostico.getCodigoEstablecimiento());

        Diagnostico d = em.find(Diagnostico.class, diagnostico.getId());

        if (enfermedad != null) {
            d.setEnfermedad(enfermedad);
            d.setEstablecimiento(establecimiento);
            d.setTipoDiagnostico(tipoDiagnostico);
            d.setProfesional(profesional);
            em.merge(diagnostico);
        }

        return diagnostico;
    }
}
