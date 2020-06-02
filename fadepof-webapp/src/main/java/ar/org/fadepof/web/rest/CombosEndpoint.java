package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Pais;
import ar.org.fadepof.service.PaisService;
import ar.org.fadepof.service.EspecialidadService;
import ar.org.fadepof.service.EstablecimientoService;
import ar.org.fadepof.service.ProvinciaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@RequestScoped
@Transactional
public class CombosEndpoint {

    @Inject
    PaisService paisService;

    @Inject
    ProvinciaService provinciaService;

    @Inject
    EstablecimientoService establecimientoService;

    @Inject
    EspecialidadService especialidadService;

    @GET
    @Path("/paises")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200).entity(paisService.getAll()).build();
    }

    @POST
    @Path("/paises")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Pais pais) {
        return Response.status(200).entity(paisService.create(pais)).build();
    }

    @GET
    @Path("/provincias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProvinciasBy(@QueryParam("pais") String codigoPais) {
        return Response.status(200).entity(provinciaService.getBy(codigoPais)).build();
    }

    @GET
    @Path("/localidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocaliadesBy(@QueryParam("provincia") Integer codigoProvincia) {
        return Response.status(200).entity(provinciaService.getLocalidadesBy(codigoProvincia)).build();
    }

    @GET
    @Path("/establecimientos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstablecimientosByProvincia(@QueryParam("provincia") String provincia) {
        return Response.status(200).entity(establecimientoService.getByProvincia(provincia)).build();
    }

    @GET
    @Path("/centrosSalud")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEstablecimientosByNombre(@QueryParam("nombre") String nombre) {
        return Response.status(200).entity(establecimientoService.getByNombre(nombre)).build();
    }

    @GET
    @Path("/especialidades")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEspecialidades() {
        return Response.status(200).entity(especialidadService.getAll()).build();
    }
}
