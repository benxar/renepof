package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "registro_diagnostico")
@JsonPropertyOrder({"id", "enfermedad", "fecha", "tipoDiagnostico", "establecimiento", "profesional"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegistroDiagnostico {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @ManyToOne(fetch= FetchType.EAGER,  optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name="enfermedad_id")
    private Enfermedad enfermedad;

    private Diagnostico.TipoDiagnostico tipoDiagnostico;

    private Date fecha;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="establecimiento_id")
    private Establecimiento establecimiento;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="profesional_id")
    private Profesional profesional;

    public RegistroDiagnostico() {}

    public RegistroDiagnostico(Diagnostico diagnostico) {
        this.enfermedad = diagnostico.getEnfermedad();
        this.establecimiento = diagnostico.getEstablecimiento();
        this.tipoDiagnostico = diagnostico.getTipoDiagnostico();
        this.profesional = diagnostico.getProfesional();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
    }

    public Diagnostico.TipoDiagnostico getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(Diagnostico.TipoDiagnostico tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }
}
