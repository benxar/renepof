package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {

    public enum TipoUsuario {
        MEDICO,
        REPRESENTANTE,
        PACIENTE
    }

    public enum Sexo {
        MASCULINO,
        FEMENINO
    }

    public enum Etnia {
        CAUCASICO,
        AMERINDIO,
        ASIATICO,
        AFRICANO,
        OTRAS
    }

    @Id
    @GeneratedValue
    private Long id;

    private String nombres;
    private String apellidos;
    private String matricula;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Sexo sexo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Etnia etnia;

    private TipoUsuario tipoUsuario;

    @Embedded
    private Documento documento;

    @Embedded
    private Contacto contacto;

    @Embedded
    private Direccion direccion;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="especialidad_id")
    private Especialidad especialidad;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private List<Paciente> pacientes = new ArrayList<Paciente>();


    public String fechaNacimiento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Etnia getEtnia() {
        return etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public String getFechaNacimiento() { return fechaNacimiento; }

    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}
