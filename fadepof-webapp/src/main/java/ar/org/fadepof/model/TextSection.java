package ar.org.fadepof.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "TextSection")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection {

    @XmlElement(name = "TextSectionType")
    private TextSectionType sectionType;

    @XmlElement(name = "Contents")
    private String contents;

    public TextSectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(TextSectionType sectionType) {
        this.sectionType = sectionType;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}