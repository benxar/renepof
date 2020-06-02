package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matriculas")
@JsonPropertyOrder({"matricula", "especialidad", "jurisdiccion", "matriculas"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Matricula {

    @Id
    @JsonIgnore
    @GeneratedValue
    private Long id;
    private String matricula;

    @Transient
    private List<Especialidad> especialidades;
    private String jurisdiccion;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="profesional_id")
    private Profesional profesional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Especialidad> getEspecialidades() {
        if (this.especialidades == null) this.especialidades = new ArrayList<Especialidad>();
        return especialidades;
    }

    public void setEspecialidades(List<Especialidad> especialidades) {
        this.especialidades = especialidades;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public String getJurisdiccion() {
        return jurisdiccion;
    }

    public void setJurisdiccion(String jurisdiccion) {
        this.jurisdiccion = jurisdiccion;
    }
}
