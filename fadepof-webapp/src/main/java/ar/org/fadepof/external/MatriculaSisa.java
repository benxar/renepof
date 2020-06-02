package ar.org.fadepof.external;

import java.util.List;

public class MatriculaSisa {

    private String matricula;
    private String profesion;
    private String jurisdiccion;
    private String estado;
    private String fechaRegistro;
    private String fechaMatricula;
    private List<EspecialidadSISA> especialidades;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getJurisdiccion() {
        return jurisdiccion;
    }

    public void setJurisdiccion(String jurisdiccion) {
        this.jurisdiccion = jurisdiccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public List<EspecialidadSISA> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadSISA> especialidades) {
        this.especialidades = especialidades;
    }
}
