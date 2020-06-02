package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Diagnostico;
import ar.org.fadepof.model.Usuario;
import ar.org.fadepof.service.DiagnosticoService;
import ar.org.fadepof.service.UsuarioService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/diagnosticos")
@RequestScoped
@Transactional
public class DiagnosticoEndpoint {

    @Inject
    DiagnosticoService diagnosticoService;

    @Inject
    UsuarioService usuarioService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Diagnostico diagnostico) {

        try {
            if (diagnostico !=null && diagnostico.getCodigoEstablecimiento() != null && diagnostico.getOrphaNumber() != null) {
                return Response.status(200).entity(diagnosticoService.create(diagnostico)).build();
            } else {
                return Response.status(400).entity("invalid parameter").build();
            }
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Diagnostico diagnostico, @Context HttpServletRequest request) {
        Usuario usuario = usuarioService.getSecureUser(request);
        try {
//            if (diagnostico !=null && diagnostico.getCodigoEstablecimiento() != null && diagnostico.getOrphaNumber() != null) {
            if (diagnostico !=null) {
                return Response.status(200).entity(diagnosticoService.update(diagnostico)).build();
            } else {
                return Response.status(400).entity("invalid parameter").build();
            }
        } catch (Exception ex) {
            return Response.status(500).build();
        }
    }
}
