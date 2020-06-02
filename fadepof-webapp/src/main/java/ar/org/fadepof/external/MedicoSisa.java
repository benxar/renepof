package ar.org.fadepof.external;

import java.util.List;

public class MedicoSisa {


    private String resultado;
    private String codigo;
    private List<MatriculaSisa> matriculas;
    private String nombre;
    private String apellido;
    private String numeroDodumento;
    private String tipoDocumento;
    private String fechaRegistro;
    private String fechaModificacion;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<MatriculaSisa> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<MatriculaSisa> matriculas) {
        this.matriculas = matriculas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDodumento() {
        return numeroDodumento;
    }

    public void setNumeroDodumento(String numeroDodumento) {
        this.numeroDodumento = numeroDodumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
