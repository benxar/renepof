package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Establecimiento;
import ar.org.fadepof.service.EstablecimientoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/establecimientos")
@RequestScoped
@Transactional
public class EstablecimientoEndpoint {

    @Inject
    EstablecimientoService establecimientoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstablecimientos(@QueryParam("nombre") String nombre) {
        if (nombre !=null && !"".equals(nombre.trim())) {
            return Response.status(200).entity(establecimientoService.getByNombre(nombre)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstablecimientos(@PathParam("codigo") Long codigo) {
        if (codigo !=null && codigo > 0) {
            return Response.status(200).entity(establecimientoService.getByCodigo(codigo)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Establecimiento establecimiento) {
        try {
            return Response.status(200).entity(establecimientoService.update(establecimiento)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Establecimiento establecimiento) {
        try {
            return Response.status(200).entity(establecimientoService.create(establecimiento)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }
}
