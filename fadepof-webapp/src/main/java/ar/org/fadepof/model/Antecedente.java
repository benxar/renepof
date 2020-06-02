package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "antecedentes")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Antecedente {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonProperty("tieneFamiliaresDiagnosticados")
    private boolean familiaresConEnfermedad;

    @JsonProperty("pesoNacimiento")
    private float pesoNacimiento;

    @JsonProperty("tuveNacimientoEnTermino")
    private boolean nacimientoEnTermino;

    @JsonProperty("cantidadSemanas")
    private int numeroSemanas;

    private boolean provincia01 = false;
    private boolean provincia02 = false;
    private boolean provincia03 = false;
    private boolean provincia04 = false;
    private boolean provincia05 = false;
    private boolean provincia06 = false;
    private boolean provincia07 = false;
    private boolean provincia08 = false;
    private boolean provincia09 = false;
    private boolean provincia10 = false;
    private boolean provincia11 = false;
    private boolean provincia12 = false;
    private boolean provincia13 = false;
    private boolean provincia14 = false;
    private boolean provincia15 = false;
    private boolean provincia16 = false;
    private boolean provincia17 = false;
    private boolean provincia18 = false;
    private boolean provincia19 = false;
    private boolean provincia20 = false;
    private boolean provincia21 = false;
    private boolean provincia22 = false;
    private boolean provincia23 = false;
    private boolean provincia24 = false;

    private boolean problemas01 = false;
    private boolean problemas02 = false;
    private boolean problemas03 = false;
    private boolean problemas04 = false;
    private boolean problemas05 = false;
    private boolean problemas06 = false;
    private boolean problemas07 = false;
    private boolean problemas08 = false;
    private boolean problemas09 = false;
    private boolean problemas10 = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isFamiliaresConEnfermedad() {
        return familiaresConEnfermedad;
    }

    public void setFamiliaresConEnfermedad(boolean familiaresConEnfermedad) {
        this.familiaresConEnfermedad = familiaresConEnfermedad;
    }

    public float getPesoNacimiento() {
        return pesoNacimiento;
    }

    public void setPesoNacimiento(float pesoNacimiento) {
        this.pesoNacimiento = pesoNacimiento;
    }

    public boolean isNacimientoEnTermino() {
        return nacimientoEnTermino;
    }

    public void setNacimientoEnTermino(boolean nacimientoEnTermino) {
        this.nacimientoEnTermino = nacimientoEnTermino;
    }

    public int getNumeroSemanas() {
        return numeroSemanas;
    }

    public void setNumeroSemanas(int numeroSemanas) {
        this.numeroSemanas = numeroSemanas;
    }

    public boolean isProvincia01() {
        return provincia01;
    }

    public void setProvincia01(boolean provincia01) {
        this.provincia01 = provincia01;
    }

    public boolean isProvincia02() {
        return provincia02;
    }

    public void setProvincia02(boolean provincia02) {
        this.provincia02 = provincia02;
    }

    public boolean isProvincia03() {
        return provincia03;
    }

    public void setProvincia03(boolean provincia03) {
        this.provincia03 = provincia03;
    }

    public boolean isProvincia04() {
        return provincia04;
    }

    public void setProvincia04(boolean provincia04) {
        this.provincia04 = provincia04;
    }

    public boolean isProvincia05() {
        return provincia05;
    }

    public void setProvincia05(boolean provincia05) {
        this.provincia05 = provincia05;
    }

    public boolean isProvincia06() {
        return provincia06;
    }

    public void setProvincia06(boolean provincia06) {
        this.provincia06 = provincia06;
    }

    public boolean isProvincia07() {
        return provincia07;
    }

    public void setProvincia07(boolean provincia07) {
        this.provincia07 = provincia07;
    }

    public boolean isProvincia08() {
        return provincia08;
    }

    public void setProvincia08(boolean provincia08) {
        this.provincia08 = provincia08;
    }

    public boolean isProvincia09() {
        return provincia09;
    }

    public void setProvincia09(boolean provincia09) {
        this.provincia09 = provincia09;
    }

    public boolean isProvincia10() {
        return provincia10;
    }

    public void setProvincia10(boolean provincia10) {
        this.provincia10 = provincia10;
    }

    public boolean isProvincia11() {
        return provincia11;
    }

    public void setProvincia11(boolean provincia11) {
        this.provincia11 = provincia11;
    }

    public boolean isProvincia12() {
        return provincia12;
    }

    public void setProvincia12(boolean provincia12) {
        this.provincia12 = provincia12;
    }

    public boolean isProvincia13() {
        return provincia13;
    }

    public void setProvincia13(boolean provincia13) {
        this.provincia13 = provincia13;
    }

    public boolean isProvincia14() {
        return provincia14;
    }

    public void setProvincia14(boolean provincia14) {
        this.provincia14 = provincia14;
    }

    public boolean isProvincia15() {
        return provincia15;
    }

    public void setProvincia15(boolean provincia15) {
        this.provincia15 = provincia15;
    }

    public boolean isProvincia16() {
        return provincia16;
    }

    public void setProvincia16(boolean provincia16) {
        this.provincia16 = provincia16;
    }

    public boolean isProvincia17() {
        return provincia17;
    }

    public void setProvincia17(boolean provincia17) {
        this.provincia17 = provincia17;
    }

    public boolean isProvincia18() {
        return provincia18;
    }

    public void setProvincia18(boolean provincia18) {
        this.provincia18 = provincia18;
    }

    public boolean isProvincia19() {
        return provincia19;
    }

    public void setProvincia19(boolean provincia19) {
        this.provincia19 = provincia19;
    }

    public boolean isProvincia20() {
        return provincia20;
    }

    public void setProvincia20(boolean provincia20) {
        this.provincia20 = provincia20;
    }

    public boolean isProvincia21() {
        return provincia21;
    }

    public void setProvincia21(boolean provincia21) {
        this.provincia21 = provincia21;
    }

    public boolean isProvincia22() {
        return provincia22;
    }

    public void setProvincia22(boolean provincia22) {
        this.provincia22 = provincia22;
    }

    public boolean isProvincia23() {
        return provincia23;
    }

    public void setProvincia23(boolean provincia23) {
        this.provincia23 = provincia23;
    }

    public boolean isProvincia24() {
        return provincia24;
    }

    public void setProvincia24(boolean provincia24) {
        this.provincia24 = provincia24;
    }

    public boolean isProblemas01() {
        return problemas01;
    }

    public void setProblemas01(boolean problemas01) {
        this.problemas01 = problemas01;
    }

    public boolean isProblemas02() {
        return problemas02;
    }

    public void setProblemas02(boolean problemas02) {
        this.problemas02 = problemas02;
    }

    public boolean isProblemas03() {
        return problemas03;
    }

    public void setProblemas03(boolean problemas03) {
        this.problemas03 = problemas03;
    }

    public boolean isProblemas04() {
        return problemas04;
    }

    public void setProblemas04(boolean problemas04) {
        this.problemas04 = problemas04;
    }

    public boolean isProblemas05() {
        return problemas05;
    }

    public void setProblemas05(boolean problemas05) {
        this.problemas05 = problemas05;
    }

    public boolean isProblemas06() {
        return problemas06;
    }

    public void setProblemas06(boolean problemas06) {
        this.problemas06 = problemas06;
    }

    public boolean isProblemas07() {
        return problemas07;
    }

    public void setProblemas07(boolean problemas07) {
        this.problemas07 = problemas07;
    }

    public boolean isProblemas08() {
        return problemas08;
    }

    public void setProblemas08(boolean problemas08) {
        this.problemas08 = problemas08;
    }

    public boolean isProblemas09() {
        return problemas09;
    }

    public void setProblemas09(boolean problemas09) {
        this.problemas09 = problemas09;
    }

    public boolean isProblemas10() {
        return problemas10;
    }

    public void setProblemas10(boolean problemas10) {
        this.problemas10 = problemas10;
    }
}