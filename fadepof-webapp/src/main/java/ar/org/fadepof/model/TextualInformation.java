package ar.org.fadepof.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "TextSection")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextualInformation {

    @XmlAttribute
    private Long id;

    @XmlElementWrapper(name = "TextSectionList")
    @XmlElement(name = "TextSection")
    private List<TextSection> sections;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TextSection> getSections() {
        return sections;
    }

    public void setSections(List<TextSection> sections) {
        this.sections = sections;
    }
}
