package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@AnalyzerDef(name = "pacienteAnalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Spanish")
                })
        })
@JsonPropertyOrder({"id", "nombres", "apellidos", "documento"})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Paciente {

    @Id
    @GeneratedValue
    private Long id;

    @Field(name = "nombre")
    @Analyzer(definition = "pacienteAnalyzer")
    private String nombres;

    @Field(name = "apellidos")
    @Analyzer(definition = "pacienteAnalyzer")
    private String apellidos;

    private String obraSocial;

    private String fechaNacimiento;

    public enum Sexo {
        MASCULINO,
        FEMENINO
    }

    public enum Etnia {
        CAUCASICO,
        AMERINDIO,
        ASIATICA,
        AFRICANO,
        OTRAS
    }

    public enum TipoCobertura {
        PUBLICA,
        OBRA_SOCIAL,
        PREPAGA
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Sexo sexo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Etnia etnia;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private TipoCobertura tipoCobertura;

    @Embedded
    private Documento documento;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "pais", column = @Column(name = "pais_nacimiento")),
        @AttributeOverride(name = "provincia", column = @Column(name = "provincia_nacimiento")),
        @AttributeOverride(name = "localidad", column = @Column(name = "localidad_nacimiento")),
        @AttributeOverride(name = "direccion", column = @Column(name = "direccion_nacimiento")),
        @AttributeOverride(name = "departamento", column = @Column(name = "departamento_nacimiento")),
        @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigopostal_nacimiento")),
        @AttributeOverride(name = "provinciaLibre", column = @Column(name = "provinciaLibre_nacimiento")),
        @AttributeOverride(name = "localidadLibre", column = @Column(name = "localidadLibre_nacimiento"))
    })
    private Direccion lugarNacimiento;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "pais", column = @Column(name = "pais_actual")),
        @AttributeOverride(name = "provincia", column = @Column(name = "provincia_actual")),
        @AttributeOverride(name = "localidad", column = @Column(name = "localidad_actual")),
        @AttributeOverride(name = "direccion", column = @Column(name = "direccion_actual")),
        @AttributeOverride(name = "departamento", column = @Column(name = "departamentoLibre_actual")),
        @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigopostal_actual")),
        @AttributeOverride(name = "provinciaLibre", column = @Column(name = "provinciaLibre_actual")),
        @AttributeOverride(name = "localidadLibre", column = @Column(name = "localidadLibre_actual"))
    })
    private Direccion lugarResidenciaActual;


    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.MERGE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patologia> patologias;

    @Transient
    private Long orphaNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public TipoCobertura getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(TipoCobertura tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Etnia getEtnia() {
        return etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
    }

    public Direccion getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(Direccion lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Direccion getLugarResidenciaActual() {
        return lugarResidenciaActual;
    }

    public void setLugarResidenciaActual(Direccion lugarResidenciaActual) {
        this.lugarResidenciaActual = lugarResidenciaActual;
    }

    public Long getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(Long orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public List<Patologia> getPatologias() {
        if (this.patologias == null) {
            this.patologias = new ArrayList<Patologia>();
        }
        return patologias;
    }

    public void setPatologias(List<Patologia> patologias) {
        this.patologias = patologias;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
