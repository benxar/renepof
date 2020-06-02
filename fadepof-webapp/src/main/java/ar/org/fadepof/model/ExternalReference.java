package ar.org.fadepof.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ExternalReference ")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalReference {

    @XmlElement(name = "Source")
    private String source;

    @XmlElement(name = "Reference")
    private String reference;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
