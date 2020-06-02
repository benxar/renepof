package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Enfermedad;
import ar.org.fadepof.model.Paciente;
import ar.org.fadepof.service.EnfermedadService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/enfermedades")
@RequestScoped
@Transactional
public class EnfermedadEndpoint {

    @Inject
    EnfermedadService enfermedadService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnfermedades(@QueryParam("nombre") String nombre) {
        if (nombre !=null && !"".equals(nombre.trim())) {
            return Response.status(200).entity(enfermedadService.getEnfermedadByNombre(nombre)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Enfermedad enfermedad) {
        try {
            return Response.status(200).entity(enfermedadService.update(enfermedad)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Enfermedad enfermedad) {
        try {
            return Response.status(200).entity(enfermedadService.create(enfermedad)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }
}
