package ar.org.fadepof.web.rest;

import ar.org.fadepof.service.ProcedureService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/procedures")
@RequestScoped
@Transactional
public class ProcedureEndpoint {

    @Inject
    ProcedureService procedureService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcedureByName(@QueryParam("name") String name) {
        if (name == null || "".equals(name.trim())) {
            return Response.status(400).entity("invalid parameter").build();
        } else {
            return Response.status(200).entity(procedureService.getProcedureBy(name)).build();
        }
    }

}
