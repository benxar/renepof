package ar.org.fadepof.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipacionesSISA {

    private String ERC;
    private String planNacer;
    private String programaMedicosComunitarios;
    private String programaRemediar;
    private String REDES;
    private String RESAM;
    private String redDirectoresHospitales;
    private String redEstablecimientosCCC;
    private String redNOMIVAC;
    private String registroHPGD;
    private String registroSIVILE;
    private String sistemaNacionalSangre;
    private String sistemaNacionalVigilanciaSalud;

    public String getERC() {
        return ERC;
    }

    public void setERC(String ERC) {
        this.ERC = ERC;
    }

    public String getPlanNacer() {
        return planNacer;
    }

    public void setPlanNacer(String planNacer) {
        this.planNacer = planNacer;
    }

    public String getProgramaMedicosComunitarios() {
        return programaMedicosComunitarios;
    }

    public void setProgramaMedicosComunitarios(String programaMedicosComunitarios) {
        this.programaMedicosComunitarios = programaMedicosComunitarios;
    }

    public String getProgramaRemediar() {
        return programaRemediar;
    }

    public void setProgramaRemediar(String programaRemediar) {
        this.programaRemediar = programaRemediar;
    }

    public String getREDES() {
        return REDES;
    }

    public void setREDES(String REDES) {
        this.REDES = REDES;
    }

    public String getRESAM() {
        return RESAM;
    }

    public void setRESAM(String RESAM) {
        this.RESAM = RESAM;
    }

    public String getRedDirectoresHospitales() {
        return redDirectoresHospitales;
    }

    public void setRedDirectoresHospitales(String redDirectoresHospitales) {
        this.redDirectoresHospitales = redDirectoresHospitales;
    }

    public String getRedEstablecimientosCCC() {
        return redEstablecimientosCCC;
    }

    public void setRedEstablecimientosCCC(String redEstablecimientosCCC) {
        this.redEstablecimientosCCC = redEstablecimientosCCC;
    }

    public String getRedNOMIVAC() {
        return redNOMIVAC;
    }

    public void setRedNOMIVAC(String redNOMIVAC) {
        this.redNOMIVAC = redNOMIVAC;
    }

    public String getRegistroHPGD() {
        return registroHPGD;
    }

    public void setRegistroHPGD(String registroHPGD) {
        this.registroHPGD = registroHPGD;
    }

    public String getRegistroSIVILE() {
        return registroSIVILE;
    }

    public void setRegistroSIVILE(String registroSIVILE) {
        this.registroSIVILE = registroSIVILE;
    }

    public String getSistemaNacionalSangre() {
        return sistemaNacionalSangre;
    }

    public void setSistemaNacionalSangre(String sistemaNacionalSangre) {
        this.sistemaNacionalSangre = sistemaNacionalSangre;
    }

    public String getSistemaNacionalVigilanciaSalud() {
        return sistemaNacionalVigilanciaSalud;
    }

    public void setSistemaNacionalVigilanciaSalud(String sistemaNacionalVigilanciaSalud) {
        this.sistemaNacionalVigilanciaSalud = sistemaNacionalVigilanciaSalud;
    }
}
