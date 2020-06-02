package ar.org.fadepof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "Spanish")
                })
        })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonPropertyOrder({ "name", "orphaId", "orphaNumber", "link"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Disorder implements Serializable {

    @Transient
    @JsonIgnore
    public static final String OMIM = "OMIM";

    @Transient
    @JsonIgnore
    public static final String ICD10= "ICD-10";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlAttribute(name = "id")
    @JsonProperty("orphaId")
    private Long orphaId;

    @XmlElement(name = "OrphaNumber")
    @JsonProperty("orphaNumber")
    private Long orphaNumber;

    @XmlElement(name = "FadepofNumber")
    @JsonProperty("fadepofNumber")
    private String fadepofNumber;

    @XmlElement(name = "ExpertLink")
    @JsonProperty("link")
    private URL expertLink;

    @Field(name = "name")
    @XmlElement(name = "Name")
    @JsonProperty("name")
    @Analyzer(definition = "customanalyzer")
    private String name;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @JsonProperty("omim")
    private String omim;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @JsonProperty("icd-10")
    private String icd10;

    @Transient
    @XmlElementWrapper(name = "SynonymList")
    @XmlElement(name = "Synonym")
    private List<String> synonyms;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @JsonIgnore
    private String synonymsText;

    @Transient
    @XmlElementWrapper(name = "TextualInformationList")
    @XmlElement(name = "TextualInformation")
    private List<TextualInformation> information;

    @Transient
    @XmlElementWrapper(name = "ExternalReferenceList")
    @XmlElement(name = "ExternalReference")
    private List<ExternalReference> externalReferences;

    @Embedded
    @XmlElement(name = "DisorderType")
    private DisorderType disorderType;


    @Embedded
    @XmlElement(name = "GroupOfType")
    private GroupOfType groupOfType;

    @Transient
    private String type;

    @Transient
    private String group;

    /**
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="enfermedades_establecimiento",
            joinColumns={ @JoinColumn(name="enfermedad_id", referencedColumnName="id") },
            inverseJoinColumns={ @JoinColumn(name="establecimiento_id", referencedColumnName="id", unique=true) })
     **/
    @Transient
    @JsonIgnore
    private List<Establecimiento> establecimientos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrphaId() {
        return orphaId;
    }

    public void setOrphaId(Long orphaId) {
        this.orphaId = orphaId;
    }

    public Long getOrphaNumber() {
        return orphaNumber;
    }

    public void setOrphaNumber(Long orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public URL getExpertLink() {
        return expertLink;
    }

    public void setExpertLink(URL expertLink) {
        this.expertLink = expertLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<TextualInformation> getInformation() {
        return information;
    }

    public void setInformation(List<TextualInformation> information) {
        this.information = information;
    }

    public List<Establecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<ExternalReference> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences(List<ExternalReference> externalReferences) {
        this.externalReferences = externalReferences;
    }

    public String getOmim() {
        return omim;
    }

    public void setOmim(String omim) {
        this.omim = omim;
    }

    public String getIcd10() {
        return icd10;
    }

    public void setIcd10(String icd10) {
        this.icd10 = icd10;
    }

    public static String getOMIM() {
        return OMIM;
    }

    public static String getICD10() {
        return ICD10;
    }

    public String getSynonymsText() {
        return synonymsText;
    }

    public void setSynonymsText(String synonymsText) {
        this.synonymsText = synonymsText;
    }

    public String getFadepofNumber() {
        return fadepofNumber;
    }

    public void setFadepofNumber(String fadepofNumber) {
        this.fadepofNumber = fadepofNumber;
    }

    public DisorderType getDisorderType() {
        return disorderType;
    }

    public void setDisorderType(DisorderType disorderType) {
        this.disorderType = disorderType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GroupOfType getGroupOfType() {
        return this.groupOfType;
    }

    public void setGroupOfType(GroupOfType groupOfType) {
        this.groupOfType = groupOfType;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
