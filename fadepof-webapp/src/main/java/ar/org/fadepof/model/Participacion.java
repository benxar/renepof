package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "participaciones")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Participacion {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonProperty("participoEnsayoClinico") private boolean participoEnsayoClinico;
    @JsonProperty("estaParticipando") private boolean estaParticipando;
    @JsonProperty("quiereSerContactado") private boolean quiereSerContactado;
    @JsonProperty("quiereDonarMuestras") private boolean quiereDonarMuestras;
    @JsonProperty("donoMuestras") private boolean donoMuestras;
    @JsonProperty("muestras") private String muestras;
    @JsonProperty("institucionMuestra") private String institucionMuestra;
    @JsonProperty("contacto_1") private boolean contacto_1;
    @JsonProperty("contacto_2") private boolean contacto_2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isParticipoEnsayoClinico() {
        return participoEnsayoClinico;
    }

    public void setParticipoEnsayoClinico(boolean participoEnsayoClinico) {
        this.participoEnsayoClinico = participoEnsayoClinico;
    }

    public boolean isEstaParticipando() {
        return estaParticipando;
    }

    public void setEstaParticipando(boolean estaParticipando) {
        this.estaParticipando = estaParticipando;
    }

    public boolean isQuiereSerContactado() {
        return quiereSerContactado;
    }

    public void setQuiereSerContactado(boolean quiereSerContactado) {
        this.quiereSerContactado = quiereSerContactado;
    }

    public boolean isQuiereDonarMuestras() {
        return quiereDonarMuestras;
    }

    public void setQuiereDonarMuestras(boolean quiereDonarMuestras) {
        this.quiereDonarMuestras = quiereDonarMuestras;
    }

    public boolean isDonoMuestras() {
        return donoMuestras;
    }

    public void setDonoMuestras(boolean donoMuestras) {
        this.donoMuestras = donoMuestras;
    }

    public String getMuestras() {
        return muestras;
    }

    public void setMuestras(String muestras) {
        this.muestras = muestras;
    }

    public String getInstitucionMuestra() {
        return institucionMuestra;
    }

    public void setInstitucionMuestra(String institucionMuestra) {
        this.institucionMuestra = institucionMuestra;
    }

    public boolean isContacto_1() {
        return contacto_1;
    }

    public void setContacto_1(boolean contacto_1) {
        this.contacto_1 = contacto_1;
    }

    public boolean isContacto_2() {
        return contacto_2;
    }

    public void setContacto_2(boolean contacto_2) {
        this.contacto_2 = contacto_2;
    }
}
