package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tratamientos")
@JsonPropertyOrder({"id"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tratamiento {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="diagnostico_id")
    private Diagnostico diagnostico;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name="tratamiento_establecimiento",
            joinColumns=@JoinColumn(name="tratamiento_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="establecimiento_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Establecimiento> establecimientos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="tratamiento_profesional",
            joinColumns=@JoinColumn(name="tratamiento_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="profesional_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Profesional> profesionales;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name="tratamiento_medicamento",
            joinColumns=@JoinColumn(name="tratamiento_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="medicamento_id", referencedColumnName="id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Medicamento> medicamentos;

    private Boolean sigueDieta;
    private Boolean tomaMedicamentosExtra;
    private Integer internaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<Profesional> getProfesionales() {
        return profesionales;
    }

    public void setProfesionales(List<Profesional> profesionales) {
        this.profesionales = profesionales;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public Boolean getSigueDieta() {
        return sigueDieta;
    }

    public void setSigueDieta(Boolean sigueDieta) {
        this.sigueDieta = sigueDieta;
    }

    public Boolean getTomaMedicamentosExtra() {
        return tomaMedicamentosExtra;
    }

    public void setTomaMedicamentosExtra(Boolean tomaMedicamentosExtra) {
        this.tomaMedicamentosExtra = tomaMedicamentosExtra;
    }

    public Integer getInternaciones() {
        return internaciones;
    }

    public void setInternaciones(Integer internaciones) {
        this.internaciones = internaciones;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
}
