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
@XmlRootElement(name = "GroupOfType ")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupOfType implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlElement(name = "Name")
    private String groupOfType;

    public String getGroupOfType() {
        return groupOfType;
    }

    public void setGroupOfType(String groupOfType) {
        this.groupOfType = groupOfType;
    }


}