package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesionales")
@JsonPropertyOrder({"id", "codigo", "nombres", "apellidos", "matriculas"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Profesional {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String servicio;
    private String especialidad;
    private String matricula;
    @Transient
    private String centroSalud;

    @OneToMany(mappedBy="profesional")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Matricula> matriculas;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="tratamiento_profesional",
            joinColumns=@JoinColumn(name="profesional_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="tratamiento_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Tratamiento> tratamientos;

    @Embedded
    private Contacto contacto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public List<Matricula> getMatriculas() {
        if (this.matriculas == null) this.matriculas = new ArrayList<Matricula>();
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Tratamiento> getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(List<Tratamiento> tratamientos) {
        this.tratamientos = tratamientos;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public String getCentroSalud() {
        return centroSalud;
    }

    public void setCentroSalud(String centroSalud) {
        this.centroSalud = centroSalud;
    }
}
