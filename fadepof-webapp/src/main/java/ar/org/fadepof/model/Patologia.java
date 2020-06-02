package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patologias")
@JsonPropertyOrder({"id", "diagnostico","sintomas", "tratamiento", "antecedentes", "investigaciones", "discapacidad", "historial" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Patologia {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="diagnostico_id")
    @JsonProperty("diagnostico")
    private Diagnostico diagnostico;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="tratamiento_id")
    @JsonProperty("tratamiento")
    private Tratamiento tratamiento;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="antecedente_id")
    @JsonProperty("antecedentes")
    private Antecedente antecedente;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="participacion_id")
    @JsonProperty("investigacion")
    private Participacion participacion;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="dificultad_id")
    @JsonProperty("discapacidad")
    private Dificultad dificultad;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name="patologia_id")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonProperty("historial")
    private List<Diagnostico> diagnosticos;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name="patologia_id")
    private List<Sintoma> sintomas = new ArrayList<>();

    private Integer edadPrimerosSintomas;

    @Transient
    private String orphaNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Diagnostico getDiagnostico() {
        if ( this.diagnostico == null ) {
            this.diagnostico = new Diagnostico();
        }
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Antecedente getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Antecedente antecedente) {
        this.antecedente = antecedente;
    }

    public Participacion getParticipacion() {
        return participacion;
    }

    public void setParticipacion(Participacion participacion) {
        this.participacion = participacion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<Diagnostico> getDiagnosticos() {
        if ( this.diagnosticos == null ) {
            this.diagnosticos = new ArrayList<Diagnostico>();
        }
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public String getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(String orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public List<Sintoma> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<Sintoma> sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getEdadPrimerosSintomas() {
        return edadPrimerosSintomas;
    }

    public void setEdadPrimerosSintomas(Integer edadPrimerosSintomas) {
        this.edadPrimerosSintomas = edadPrimerosSintomas;
    }
}
