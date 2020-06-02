package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.Contacto;
import ar.org.fadepof.model.Usuario;
import ar.org.fadepof.service.UsuarioService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/usuario")
//@RequestScoped
@Stateless
@Transactional
public class UsuarioEndpoint {

    @Resource
    private SessionContext sessionContext;

    @Inject
    UsuarioService usuarioService;

    @Context
    private HttpServletRequest servletRequest;

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        return Response.status(200).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response create(Usuario usuario, @Context HttpServletRequest request) {
        usuario.getContacto().setEmail(this.usuarioService.getSecureUser(request).getContacto().getEmail());
        Long id = usuarioService.create(usuario);
        return Response.status(200).entity(id).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response update(Usuario updates, @Context HttpServletRequest request) {
        Usuario usuario = this.usuarioService.getSecureUser(request);
        if (updates.getEspecialidad() != null && updates.getEspecialidad().getId() != null) {
            usuario.setEspecialidad(updates.getEspecialidad());
        }
        usuario.setDocumento(updates.getDocumento());
        usuario.setContacto(updates.getContacto());
        usuario.setTipoUsuario(updates.getTipoUsuario());
        usuario.setNombres(updates.getNombres());
        usuario.setApellidos(updates.getApellidos());
        usuario.setDireccion(updates.getDireccion());
        usuario.setEtnia(updates.getEtnia());
        usuario.setMatricula(updates.getMatricula());
        usuario.setSexo(updates.getSexo());
        usuario.setFechaNacimiento(updates.getFechaNacimiento());
        Long id = usuarioService.update(usuario);
        return Response.status(200).entity(id).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@Context HttpServletRequest request) {
        return Response.status(200).entity(this.usuarioService.getSecureUser(request)).build();
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogout(@Context HttpServletRequest request) {
        try {
            request.logout();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }

}