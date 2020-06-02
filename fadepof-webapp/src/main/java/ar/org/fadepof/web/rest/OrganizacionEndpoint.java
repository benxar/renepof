package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Organizacion;
import ar.org.fadepof.service.OrganizacionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/organizaciones")
@RequestScoped
@Transactional
public class OrganizacionEndpoint {

    @Inject
    OrganizacionService organizacionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizaciones(@QueryParam("nombre") String nombre, @QueryParam("orphaNumber") Long orphaNumber) {
        if (nombre != null && !"".equals(nombre.trim())) {
            return Response.status(200).entity(organizacionService.getOrganizacionByNombre(nombre)).build();
        } else if (orphaNumber !=null && orphaNumber != 0) {
            return Response.status(200).entity(organizacionService.getOrganizacionesByOrphaNumber(orphaNumber)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizaciones(@PathParam("codigo") Long codigo) {
        if (codigo !=null && codigo > 0) {
            return Response.status(200).entity(organizacionService.getOrganizacionByCodigo(codigo)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Organizacion organizacion) {
        try {
            return Response.status(200).entity(organizacionService.update(organizacion)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Organizacion organizacion) {
        try {
            return Response.status(200).entity(organizacionService.create(organizacion)).build();
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }
}
