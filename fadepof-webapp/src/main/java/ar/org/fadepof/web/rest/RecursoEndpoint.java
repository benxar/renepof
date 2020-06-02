package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Recurso;
import ar.org.fadepof.service.EstablecimientoService;
import ar.org.fadepof.service.OrganizacionService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/recursos")
@Stateless
@Transactional
public class RecursoEndpoint {

    @Inject
    EstablecimientoService establecimientoService;

    @Inject
    OrganizacionService organizacionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecursosByOrphaNumber(@QueryParam("fadepofNumber") String fadepofNumber, @QueryParam("orphaNumber") Long orphaNumber) {
        if (fadepofNumber !=null && !"".equals(fadepofNumber.trim())) {
            Recurso recurso = new Recurso();
            recurso.setCentrosSalud(establecimientoService.getOrganizacionesByFadepofNumber(fadepofNumber));
            recurso.setOrganizaciones(organizacionService.getOrganizacionesByFadepofNumber(fadepofNumber));
            return Response.status(200).entity(recurso).build();
        } else if (orphaNumber != null && orphaNumber != 0) {
            Recurso recurso = new Recurso();
            recurso.setCentrosSalud(establecimientoService.getOrganizacionesByOrphaNumber(orphaNumber));
            recurso.setOrganizaciones(organizacionService.getOrganizacionesByOrphaNumber(orphaNumber));
            return Response.status(200).entity(recurso).build();
        } else {
            return Response.status(400).entity("invalid parameters").build();
        }
    }


}
