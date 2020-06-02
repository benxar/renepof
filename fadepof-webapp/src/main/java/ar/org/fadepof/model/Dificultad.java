package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "dificultades")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dificultad {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonProperty("discapacidad01") private boolean dificultad01; //caminar, subir o bajar escaleras
    @JsonProperty("discapacidad02") private boolean dificultad02; //mover uno o los dos brazos o las manos
    @JsonProperty("discapacidad03") private boolean dificultad03; //agarrar objetos y/o sostener peso con una o las dos manos
    @JsonProperty("discapacidad04") private boolean dificultad04; //levantarse, acostarse, mantenerse de pie o sentado
    @JsonProperty("discapacidad05") private boolean dificultad05; //ver, aún con anteojos o lentes puestos
    @JsonProperty("discapacidad06") private boolean dificultad06; //oir aún usando audífonos o aparatos
    @JsonProperty("discapacidad07") private boolean dificultad07; //hablar o comunicarse, aún usando lengua de señas. (Entender lo que dice otra persona o que otra persona entienda lo que el paciente está diciendo).
    @JsonProperty("discapacidad08") private boolean dificultad08; //entender o aprender indicaciones sencillas. (Memorizar, comprender, reproducir y/o ejecutar indicaciones de distinta índole, por ej. Como llegar a un lugar nuevo). (Por ej. Pueden ser consideradas aquí las personas con cualquier deficiencia mental o intelectual).
    @JsonProperty("discapacidad09") private boolean dificultad09; //concentrarse y/o recordar cosas que le interesan.
    @JsonProperty("discapacidad10") private boolean dificultad10; //atender por sí mismo su cuidado personal, como lavarse o vestirse o comer, o realizar las tareas del hogar.
    @JsonProperty("discapacidad11") private boolean dificultad11; //realizar tareas diarias (domésticas, laborales o sociales) por más de 3 horas sin interrupciones a causa de su deficiencia de salud.
    @JsonProperty("discapacidad12") private boolean dificultad12; //realizar actividades no descriptas anteriormente.


    @JsonProperty("necesidadAyuda01") private boolean necesidadAyuda01;
    @JsonProperty("necesidadAyuda02") private boolean necesidadAyuda02;
    @JsonProperty("necesidadAyuda03") private boolean necesidadAyuda03;
    @JsonProperty("necesidadAyuda04") private boolean necesidadAyuda04;
    @JsonProperty("necesidadAyuda05") private boolean necesidadAyuda05;
    @JsonProperty("necesidadAyuda06") private boolean necesidadAyuda06;

    @JsonProperty("obstaculo01") private boolean obstaculo01;
    @JsonProperty("obstaculo02") private boolean obstaculo02;
    @JsonProperty("obstaculo03") private boolean obstaculo03;
    @JsonProperty("obstaculo04") private boolean obstaculo04;
    @JsonProperty("obstaculo05") private boolean obstaculo05;
    @JsonProperty("obstaculo06") private boolean obstaculo06;
    @JsonProperty("obstaculo07") private boolean obstaculo07;
    @JsonProperty("obstaculo08") private boolean obstaculo08;

    @JsonProperty("esfuerzoFisicoLimitado") private boolean esfuerzoFisicoLimitado;
    @JsonProperty("estaDeprimido") private boolean estaDeprimido;
    @JsonProperty("afectadoSicologicamente") private boolean afectadoSicologicamente;
    @JsonProperty("poseeCertificado") private boolean poseeCertificado;
    @JsonProperty("motivoFaltaCertificado") private String motivoFaltaCertificado;
    @JsonProperty("necesitaEstimulacionTemprana") private boolean necesitaEstimulacionTemprana;
    @JsonProperty("recibeEstimulacionTemprana") private boolean recibeEstimulacionTemprana;
    @JsonProperty("necesitaAyudaTecnica") private boolean necesitaAyudaTecnica;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDificultad01() {
        return dificultad01;
    }

    public void setDificultad01(boolean dificultad01) {
        this.dificultad01 = dificultad01;
    }

    public boolean isDificultad02() {
        return dificultad02;
    }

    public void setDificultad02(boolean dificultad02) {
        this.dificultad02 = dificultad02;
    }

    public boolean isDificultad03() {
        return dificultad03;
    }

    public void setDificultad03(boolean dificultad03) {
        this.dificultad03 = dificultad03;
    }

    public boolean isDificultad04() {
        return dificultad04;
    }

    public void setDificultad04(boolean dificultad04) {
        this.dificultad04 = dificultad04;
    }

    public boolean isDificultad05() {
        return dificultad05;
    }

    public void setDificultad05(boolean dificultad05) {
        this.dificultad05 = dificultad05;
    }

    public boolean isDificultad06() {
        return dificultad06;
    }

    public void setDificultad06(boolean dificultad06) {
        this.dificultad06 = dificultad06;
    }

    public boolean isDificultad07() {
        return dificultad07;
    }

    public void setDificultad07(boolean dificultad07) {
        this.dificultad07 = dificultad07;
    }

    public boolean isDificultad08() {
        return dificultad08;
    }

    public void setDificultad08(boolean dificultad08) {
        this.dificultad08 = dificultad08;
    }

    public boolean isDificultad09() {
        return dificultad09;
    }

    public void setDificultad09(boolean dificultad09) {
        this.dificultad09 = dificultad09;
    }

    public boolean isDificultad10() {
        return dificultad10;
    }

    public void setDificultad10(boolean dificultad10) {
        this.dificultad10 = dificultad10;
    }

    public boolean isDificultad11() {
        return dificultad11;
    }

    public void setDificultad11(boolean dificultad11) {
        this.dificultad11 = dificultad11;
    }

    public boolean isDificultad12() {
        return dificultad12;
    }

    public void setDificultad12(boolean dificultad12) {
        this.dificultad12 = dificultad12;
    }

    public boolean isNecesidadAyuda01() {
        return necesidadAyuda01;
    }

    public void setNecesidadAyuda01(boolean necesidadAyuda01) {
        this.necesidadAyuda01 = necesidadAyuda01;
    }

    public boolean isNecesidadAyuda02() {
        return necesidadAyuda02;
    }

    public void setNecesidadAyuda02(boolean necesidadAyuda02) {
        this.necesidadAyuda02 = necesidadAyuda02;
    }

    public boolean isNecesidadAyuda03() {
        return necesidadAyuda03;
    }

    public void setNecesidadAyuda03(boolean necesidadAyuda03) {
        this.necesidadAyuda03 = necesidadAyuda03;
    }

    public boolean isNecesidadAyuda04() {
        return necesidadAyuda04;
    }

    public void setNecesidadAyuda04(boolean necesidadAyuda04) {
        this.necesidadAyuda04 = necesidadAyuda04;
    }

    public boolean isNecesidadAyuda05() {
        return necesidadAyuda05;
    }

    public void setNecesidadAyuda05(boolean necesidadAyuda05) {
        this.necesidadAyuda05 = necesidadAyuda05;
    }

    public boolean isNecesidadAyuda06() {
        return necesidadAyuda06;
    }

    public void setNecesidadAyuda06(boolean necesidadAyuda06) {
        this.necesidadAyuda06 = necesidadAyuda06;
    }

    public boolean isObstaculo01() {
        return obstaculo01;
    }

    public void setObstaculo01(boolean obstaculo01) {
        this.obstaculo01 = obstaculo01;
    }

    public boolean isObstaculo02() {
        return obstaculo02;
    }

    public void setObstaculo02(boolean obstaculo02) {
        this.obstaculo02 = obstaculo02;
    }

    public boolean isObstaculo03() {
        return obstaculo03;
    }

    public void setObstaculo03(boolean obstaculo03) {
        this.obstaculo03 = obstaculo03;
    }

    public boolean isObstaculo04() {
        return obstaculo04;
    }

    public void setObstaculo04(boolean obstaculo04) {
        this.obstaculo04 = obstaculo04;
    }

    public boolean isObstaculo05() {
        return obstaculo05;
    }

    public void setObstaculo05(boolean obstaculo05) {
        this.obstaculo05 = obstaculo05;
    }

    public boolean isObstaculo06() {
        return obstaculo06;
    }

    public void setObstaculo06(boolean obstaculo06) {
        this.obstaculo06 = obstaculo06;
    }

    public boolean isObstaculo07() {
        return obstaculo07;
    }

    public void setObstaculo07(boolean obstaculo07) {
        this.obstaculo07 = obstaculo07;
    }

    public boolean isObstaculo08() {
        return obstaculo08;
    }

    public void setObstaculo08(boolean obstaculo08) {
        this.obstaculo08 = obstaculo08;
    }

    public boolean isEsfuerzoFisicoLimitado() {
        return esfuerzoFisicoLimitado;
    }

    public void setEsfuerzoFisicoLimitado(boolean esfuerzoFisicoLimitado) {
        this.esfuerzoFisicoLimitado = esfuerzoFisicoLimitado;
    }

    public boolean isEstaDeprimido() {
        return estaDeprimido;
    }

    public void setEstaDeprimido(boolean estaDeprimido) {
        this.estaDeprimido = estaDeprimido;
    }

    public boolean isAfectadoSicologicamente() {
        return afectadoSicologicamente;
    }

    public void setAfectadoSicologicamente(boolean afectadoSicologicamente) {
        this.afectadoSicologicamente = afectadoSicologicamente;
    }

    public boolean isPoseeCertificado() {
        return poseeCertificado;
    }

    public void setPoseeCertificado(boolean poseeCertificado) {
        this.poseeCertificado = poseeCertificado;
    }

    public String getMotivoFaltaCertificado() {
        return motivoFaltaCertificado;
    }

    public void setMotivoFaltaCertificado(String motivoFaltaCertificado) {
        this.motivoFaltaCertificado = motivoFaltaCertificado;
    }

    public boolean isRecibeEstimulacionTemprana() {
        return recibeEstimulacionTemprana;
    }

    public void setRecibeEstimulacionTemprana(boolean recibeEstimulacionTemprana) {
        this.recibeEstimulacionTemprana = recibeEstimulacionTemprana;
    }

    public boolean isNecesitaEstimulacionTemprana() {
        return necesitaEstimulacionTemprana;
    }

    public void setNecesitaEstimulacionTemprana(boolean necesitaEstimulacionTemprana) {
        this.necesitaEstimulacionTemprana = necesitaEstimulacionTemprana;
    }

    public boolean isNecesitaAyudaTecnica() {
        return necesitaAyudaTecnica;
    }

    public void setNecesitaAyudaTecnica(boolean necesitaAyudaTecnica) {
        this.necesitaAyudaTecnica = necesitaAyudaTecnica;
    }
}
