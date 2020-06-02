package ar.org.fadepof.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "JDBOR")
@XmlAccessorType(XmlAccessType.FIELD)
public class Database {

    @XmlElementWrapper(name = "DisorderList")
    @XmlElement(name = "Disorder")
    private List<Disorder> disorders;

    public List<Disorder> getDisorders() {
        return disorders;
    }

    public void setDisorders(List<Disorder> disorders) {
        this.disorders = disorders;
    }
}
