package ar.org.fadepof.service;

import ar.org.fadepof.model.*;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class UsuarioService {

    @PersistenceContext(unitName = "FadepofPU")
    private EntityManager em;

    public Long create(Usuario usuario) {
        if (usuario.getEspecialidad() != null && usuario.getEspecialidad().getId() == null) {
            usuario.setEspecialidad(null);
        }
        em.persist(usuario);
        return usuario.getId();
    }

    public Long update(Usuario usuario) {
        em.merge(usuario);
        return usuario.getId();
    }

    public Usuario find(Long id) {
        return em.find(Usuario.class, id);
    }

    public Usuario getUsuarioByMail(String mail) {
        Usuario usuario = null;
        try {
            usuario = em.createQuery("from Usuario where email = :mail", Usuario.class)
                .setParameter("mail", mail).getSingleResult();
        } catch (Exception ex) {
            System.out.println("no se encuentra usuario para el mail "+mail);
        }
        return usuario;

    }

    public Usuario getSecureUser(HttpServletRequest request) {

        final KeycloakPrincipal principal = (KeycloakPrincipal) request.getUserPrincipal();
        AccessToken token = principal.getKeycloakSecurityContext().getToken();
        String mail = token.getEmail();
        String name = token.getGivenName();
        String lastName = token.getFamilyName();

        Usuario usuario = this.getUsuarioByMail(mail);

        if (usuario == null) {
            usuario = new Usuario();
            usuario.setContacto(new Contacto());
            usuario.getContacto().setEmail(mail);
            usuario.setNombres(name);
            usuario.setApellidos(lastName);
        }

        return usuario;
    }

}
