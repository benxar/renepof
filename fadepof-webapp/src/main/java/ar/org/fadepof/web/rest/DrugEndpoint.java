package ar.org.fadepof.web.rest;

import ar.org.fadepof.service.DrugService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/drugs")
@RequestScoped
@Transactional
public class DrugEndpoint {

    @Inject
    DrugService drugService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrugsByName(@QueryParam("name") String name) {
        if (name == null || "".equals(name.trim())) {
            return Response.status(400).entity("invalid parameter").build();
        } else {
            return Response.status(200).entity(drugService.getDrugBy(name)).build();
        }
    }

}
