package ar.org.fadepof.web.rest;

import ar.org.fadepof.model.*;
import ar.org.fadepof.service.PacienteService;
import ar.org.fadepof.service.UsuarioService;
import com.sun.org.apache.regexp.internal.RE;

import javax.annotation.Resource;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/pacientes")
@Stateless
@Transactional
public class PacienteEndpoint {

    @Resource
    private SessionContext sessionContext;

    @Inject
    UsuarioService usuarioService;

    @Inject
    PacienteService pacienteService;

    @Context
    private HttpServletRequest servletRequest;

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
        try {
            return Response.status(200).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long id) {
        //TODO Validar permisos edicion al paciente
        try {
            return Response.status(200).entity(pacienteService.find(id)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Paciente actualizaciones, @Context HttpServletRequest request) {
        try {
            Paciente paciente = this.findPaciente(request, actualizaciones.getId());
            if (paciente != null) {
                paciente.setNombres(actualizaciones.getNombres());
                paciente.setApellidos(actualizaciones.getApellidos());
                paciente.setSexo(actualizaciones.getSexo());
                paciente.setEtnia(actualizaciones.getEtnia());
                paciente.setDocumento(actualizaciones.getDocumento());
                paciente.setLugarNacimiento(actualizaciones.getLugarNacimiento());
                paciente.setLugarResidenciaActual(actualizaciones.getLugarResidenciaActual());
                paciente.setTipoCobertura(actualizaciones.getTipoCobertura());
                paciente.setObraSocial(actualizaciones.getObraSocial());
                paciente.setFechaNacimiento(actualizaciones.getFechaNacimiento());
                return Response.status(200).entity(pacienteService.update(paciente)).build();
            } else {
                return Response.status(500).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Paciente paciente, @Context HttpServletRequest request) {
        try {
            Usuario usuario =  this.usuarioService.getSecureUser(request);
            pacienteService.create(paciente);
            usuario.getPacientes().add(paciente);
            usuarioService.update(usuario);
            return Response.status(200).entity(paciente).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("{id}/patologia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("id") Long pacienteId, Patologia patologia, @Context HttpServletRequest request) {
        try {
            if (patologia.getId() == null) {
                return Response.status(200).entity(pacienteService.create(pacienteId, patologia)).build();
            } else {
                return Response.status(404).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{id}/diagnostico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long pacienteId, Diagnostico diagnostico, @Context HttpServletRequest request) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            this.findPatologia(paciente, diagnostico);
            if (diagnostico.getId() != null && diagnostico.getId() > 0) {
                return Response.status(200).entity(pacienteService.update(pacienteId, diagnostico)).build();
            } else {
                return Response.status(404).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("{id}/diagnostico")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("id") Long pacienteId, Diagnostico diagnostico, @Context HttpServletRequest request) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            if (paciente !=null) {
                Patologia patologia = this.findPatologia(paciente, diagnostico);
                if (patologia != null) {
                    return Response.status(200).entity(pacienteService.create(pacienteId, patologia.getId(), diagnostico)).build();
                }
            }
            return Response.status(500).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{paciente_id}/{patologia_id}/tratamiento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTratamiento(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            @Context HttpServletRequest request,
            Tratamiento tratamiento) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            Patologia patologia = this.findPatologia(paciente, patologiaId);
            if (patologia != null && tratamiento != null && tratamiento.getId() != null &&
                    patologia.getTratamiento().getId().equals(tratamiento.getId())) {
                patologia.setTratamiento(tratamiento);
                return Response.status(200).entity(pacienteService.update(patologia)).build();
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{paciente_id}/{patologia_id}/sintomas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSintomas(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            @Context HttpServletRequest request,
            Patologia updates) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            Patologia patologia = this.findPatologia(paciente, patologiaId);
            if (patologia != null && updates != null) {
                patologia.setEdadPrimerosSintomas(updates.getEdadPrimerosSintomas());
                List<Sintoma> toRemove = new ArrayList();
                for (Sintoma sintoma: patologia.getSintomas()) {
                    if (!updates.getSintomas().contains(sintoma)) {
                        toRemove.add(sintoma);
                    }
                }
                for (Sintoma sintoma: updates.getSintomas()) {
                    if (!patologia.getSintomas().contains(sintoma)) {
                        patologia.getSintomas().add(sintoma);
                    }
                }
                patologia.getSintomas().removeAll(toRemove);
                return Response.status(200).entity(pacienteService.update(patologia)).build();
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{paciente_id}/{patologia_id}/antecedentes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAntecedente(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            @Context HttpServletRequest request,
            Antecedente antecedente) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            Patologia patologia = this.findPatologia(paciente, patologiaId);
            if (patologia != null && antecedente != null) {
                patologia.setAntecedente(antecedente);
                return Response.status(200).entity(pacienteService.update(patologia)).build();
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{paciente_id}/{patologia_id}/dificultades")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDificultad(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            @Context HttpServletRequest request,
            Dificultad dificultad) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            Patologia patologia = this.findPatologia(paciente, patologiaId);
            if (patologia != null && dificultad != null && dificultad != null) {
                patologia.setDificultad(dificultad);
                return Response.status(200).entity(pacienteService.update(patologia)).build();
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @PUT
    @Path("{paciente_id}/{patologia_id}/participaciones")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParticipaciones(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            @Context HttpServletRequest request,
            Participacion participacion) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            Patologia patologia = this.findPatologia(paciente, patologiaId);
            if (patologia != null && participacion != null) {
                patologia.setParticipacion(participacion);
                return Response.status(200).entity(pacienteService.update(patologia)).build();
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @DELETE
    @Path("{paciente_id}/diagnostico/{diagnostico_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("diagnostico_id") Long diagnosticoId,
            @Context HttpServletRequest request) {
        try {
            Paciente paciente = this.findPaciente(request, pacienteId);
            if (diagnosticoId != null && diagnosticoId > 0) {
                Patologia patologia = pacienteService.remove(paciente, diagnosticoId);
                if (patologia != null) {
                    return Response.status(200).entity(patologia).build();
                }
            }
            return Response.status(404).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }


    @POST
    @Path("{paciente_id}/diagnostico/{diagnostico_id}/tratamiento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("diagnostico_id") Long diagnosticoId,
            Tratamiento tratamiento) {
        //TODO Validar permisos edicion al paciente
        try {
            return Response.status(200).entity(pacienteService.create(diagnosticoId, tratamiento)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("{paciente_id}/enfermedad")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("paciente_id") Long pacienteId,
            Patologia patologia) {
        //TODO Validar permisos edicion al paciente
        try {
            return Response.status(200).entity(pacienteService.create(pacienteId, patologia)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @POST
    @Path("{paciente_id}/enfermedad/{patologia_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @PathParam("paciente_id") Long pacienteId,
            @PathParam("patologia_id") Long patologiaId,
            Diagnostico diagnostico) {
        //TODO Validar permisos edicion al paciente
        try {
            return Response.status(200).entity(pacienteService.create(pacienteId, patologiaId, diagnostico)).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(500).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPacientes(@QueryParam("q") String q, @Context HttpServletRequest request) {
        if (q !=null && !"".equals(q.trim())) {
            return Response.status(200).entity(findPaciente(request, q)).build();
        } else {
            return Response.status(400).entity("invalid parameter").build();
        }
    }

    private List<Paciente> findPaciente(HttpServletRequest request, String query) {
        List<Paciente> result  = new ArrayList<>();
        for (Paciente paciente: this.usuarioService.getSecureUser(request).getPacientes()) {
            if (paciente.getDocumento() != null) {
                if (paciente.getDocumento().getNumeroDocumento().equals(query)) {
                    result.add(paciente);
                }
            }
        }
        return result;
    }

    private Paciente findPaciente(HttpServletRequest request, Long id) {
        for (Paciente paciente: this.usuarioService.getSecureUser(request).getPacientes()) {
            if (paciente.getDocumento() != null) {
                if (paciente.getId().equals(id)) {
                    return paciente;
                }
            }
        }
        return null;
    }

    private Patologia findPatologia(Paciente paciente, Long patologiaId) {
        Patologia result = null;
        for (Patologia patologia: paciente.getPatologias()) {
            if (patologia.getId().equals(patologiaId)) {
                return patologia;
            }
        }
        return null;
    }

    private Patologia findPatologia(Paciente paciente, Diagnostico diagnostico) {
        for (Patologia patologia: paciente.getPatologias()) {
            if (patologia.getDiagnostico() != null && patologia.getDiagnostico().getId().equals(diagnostico.getId())) {
                return patologia;
            }
        }
        return null;
    }

}