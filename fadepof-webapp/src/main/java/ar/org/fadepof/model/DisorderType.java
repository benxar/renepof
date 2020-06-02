package ar.org.fadepof.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
@XmlRootElement(name = "DisorderType ")
@XmlAccessorType(XmlAccessType.FIELD)
public class DisorderType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    @JsonIgnore
    private String orphaNumber;

    
    @XmlElement(name = "Name")
    private String disorderType;

    public String getOrphaNumber() {
        return this.orphaNumber;
    }    

    public void setOrphaNumber(String orphaNumber) {
        this.orphaNumber = orphaNumber;
    }

    public String getDisorderType() {
        return disorderType;
    }

    public void setDisorderType(String disorderType) {
        this.disorderType = disorderType;
    }


}