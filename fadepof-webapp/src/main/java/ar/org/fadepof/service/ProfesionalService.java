package ar.org.fadepof.service;

import ar.gob.msal.sisa.services._1.ProfesionalFilters;
import ar.gob.msal.sisa.services._1.SingleProfesionalFilters;
import ar.org.fadepof.model.*;
import ar.org.fadepof.util.WSHelper;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProfesionalService {

    private static Logger log = Logger.getLogger(ProfesionalService.class.getName());

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public Profesional getProfesionalBy(String codigoProfesional, String servicio, String esp) {

        String user = "lescati";
        String password = "f0d3p0f2018";
        SingleProfesionalFilters filter = new SingleProfesionalFilters();
        filter.setCodigo(codigoProfesional);

        ar.gob.msal.sisa.services._1.Profesional aux = WSHelper.getInstanceSISA()
            .getProfesional(user, password, filter);

        Profesional profesional = new Profesional();
        profesional.setCodigo(aux.getCodigo());
        profesional.setNombres(aux.getNombre());
        profesional.setApellidos(aux.getApellido());

        if ( aux != null && aux.getMatriculas() != null ) {
            for (ar.gob.msal.sisa.services._1.Matricula maux : aux.getMatriculas().getMatricula()) {
                Matricula matricula = new Matricula();
                matricula.setMatricula(maux.getMatricula());
                if (maux.getEspecialidades() != null) {
                    for (ar.gob.msal.sisa.services._1.Especialidad eaux : maux.getEspecialidades().getEspecialidad()) {
                        Especialidad especialidad = new Especialidad();
                        especialidad.setNombre(eaux.getEspecialidad());
                        matricula.getEspecialidades().add(especialidad);
                    }
                }
                matricula.setJurisdiccion(maux.getJurisdiccion());
                profesional.getMatriculas().add(matricula);
            }
        }

        profesional.setEspecialidad(esp);
        profesional.setServicio(servicio);

        Profesional profesionalLocal = find(aux);
        if (profesionalLocal == null) {
            em.persist(profesional);
        } else {
            profesional.setId(profesionalLocal.getId());
//            em.merge(profesional);
        }

        return profesional;
    }

    public List<Profesional> getProfesionaleslBy(String codigoMatricula) {

        String user = "lescati";
        String password = "f0d3p0f2018";
        ProfesionalFilters filter = new ProfesionalFilters();
        filter.setMatricula(codigoMatricula);

        List<Profesional> profesionales = new ArrayList<Profesional>();

        List<ar.gob.msal.sisa.services._1.Profesional> results = WSHelper.getInstanceSISA()
                .getProfesionales(user, password, filter)
                .getProfesionales().getProfesional();

        for (ar.gob.msal.sisa.services._1.Profesional aux: results) {
            Profesional profesional = new Profesional();
            profesionales.add(profesional);
            profesional.setCodigo(aux.getCodigo());
            profesional.setNombres(aux.getNombre());
            profesional.setApellidos(aux.getApellido());

            for (ar.gob.msal.sisa.services._1.Matricula maux: aux.getMatriculas().getMatricula()) {
                Matricula matricula = new Matricula();
                matricula.setMatricula(maux.getMatricula());
                if (maux.getEspecialidades() != null) {
                    for (ar.gob.msal.sisa.services._1.Especialidad eaux : maux.getEspecialidades().getEspecialidad()) {
                        Especialidad especialidad = new Especialidad();
                        especialidad.setNombre(eaux.getEspecialidad());
                        matricula.getEspecialidades().add(especialidad);
                    }
                }
                matricula.setJurisdiccion(maux.getJurisdiccion());
                profesional.getMatriculas().add(matricula);
            }
        }

        return profesionales;
    }

    private Profesional find(ar.gob.msal.sisa.services._1.Profesional aux) {
        Profesional profesionalLocal = null;
        try {
            if (aux != null && aux.getCodigo() != null) {
                profesionalLocal = em.createQuery("from Profesional where codigo = :codigo", Profesional.class)
                        .setParameter("codigo", aux.getCodigo()).getSingleResult();
            }
        } catch (Exception ex) {
            log.warn(ex.getCause());
        }
        return profesionalLocal;
    }
}
