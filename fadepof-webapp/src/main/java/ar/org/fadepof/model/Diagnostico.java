package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "diagnosticos")
@JsonPropertyOrder({"id", "tipoDiagnostico", "paciente", "enfermedad", "establecimiento", "profesional", "tratamientos", "sintomas", "fechaMes", "fechaAnio", "edadPrimerosSintomas", "fechaDiagnosticoStr", "fechaActualizacionStr"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Diagnostico {

    public enum TipoDiagnostico {
        CONFIRMADO,
        PRESUNTIVO,
        ERRONEO
    }

    @Id
    @GeneratedValue
    private Long id;

    private TipoDiagnostico tipoDiagnostico;
    private Integer fechaMes;
    private Integer fechaAnio;
    private Date fechaActualizacion;

    @NotNull
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE , optional = false)
    @JoinColumn(name="enfermedad_id")
    private Enfermedad enfermedad;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="establecimiento_id")
    private Establecimiento establecimiento;

    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="profesional_id")
    private Profesional profesional;

    @Transient
    private Long orphaNumber;

    @Transient
    private Long codigoEstablecimiento;

    @Transient
    private String codigoProfesional;

    @Transient
    private String matriculaMedico;

    @Transient
    private String servicio;

    @Transient
    private String especialidad;

    @Transient
    private Date fechaDiagnostico;

    @JsonProperty("fechaDiagnosticoStr")
    private String fechaDiagnosticoStr;

    @JsonProperty("fechaActualizacionStr")
    private String fechaActualizacionStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoDiagnostico getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(TipoDiagnostico tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public Integer getFechaMes() {
        return fechaMes;
    }

    public void setFechaMes(Integer fechaMes) {
        this.fechaMes = fechaMes;
    }

    public Integer getFechaAnio() {
        return fechaAnio;
    }

    public void setFechaAnio(Integer fechaAnio) {
        this.fechaAnio = fechaAnio;
    }

    public Enfermedad getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(Enfermedad enfermedad) {
        this.enfermedad = enfermedad;
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

    public Long getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(Long orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public Long getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(Long codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public String getCodigoProfesional() {
        return codigoProfesional;
    }

    public void setCodigoProfesional(String codigoProfesional) {
        this.codigoProfesional = codigoProfesional;
    }

    public String getMatriculaMedico() {
        return matriculaMedico;
    }

    public void setMatriculaMedico(String matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
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

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaDiagnostico() {
        Date date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.MONTH, this.getFechaMes());
            calendar.set(Calendar.YEAR, this.fechaAnio);
            date = calendar.getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return date;
    }

    public String getFechaDiagnosticoStr() {
        String result = null;
        try {
            if (this.getFechaDiagnostico() != null) {
                result = new java.text.SimpleDateFormat("MMMM yyyy", new Locale("es","ES")).format(this.getFechaDiagnostico());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public String getFechaActualizacionStr() {
        String result = null;
        try {
            if (this.getFechaActualizacion() != null) {
                result = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(this.getFechaActualizacion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}
