package ar.org.fadepof.web.rest;

import ar.org.fadepof.service.ProfesionalService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/profesionales")
@RequestScoped
@Transactional
public class ProfesionalEndpoint {

    @Inject
    ProfesionalService profesionalService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcedureByName(@QueryParam("matricula") String codigoMatricula) {
        if (codigoMatricula == null || "".equals(codigoMatricula.trim())) {
            return Response.status(400).entity("invalid parameter").build();
        } else {
            return Response.status(200).entity(profesionalService.getProfesionaleslBy(codigoMatricula)).build();
        }
    }

}
