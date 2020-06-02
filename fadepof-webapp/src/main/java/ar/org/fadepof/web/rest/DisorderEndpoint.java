package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Disorder;
import ar.org.fadepof.service.DisorderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disorders")
@RequestScoped
@Transactional
public class DisorderEndpoint {

    @Inject
    DisorderService diseaseService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiseases(@QueryParam("name") String name, @QueryParam("description") String description) {
        if (name !=null && !"".equals(name.trim())) {
            List<Disorder> disorders = diseaseService.getDiseaseByName(name);
            return Response.status(200).entity(disorders).build();
        } else if (description !=null && !"".equals(description.trim())) {
            return Response.status(200).entity(diseaseService.getDiseaseByDescription(description)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @GET
    @Path("/init")
    @Produces(MediaType.APPLICATION_JSON)
    public Response init(@QueryParam("code") String code) {
        if (code !=null && code.equals("1151")) {
            diseaseService.initDB();
            return Response.status(200).entity("ack").build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }
}
