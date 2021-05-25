package by.pomidor.domain.svg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SvgGroup {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "path")
    private List<SvgPath> pathes;
    @JacksonXmlProperty(isAttribute = true, localName = "class")
    private String clazz;

    public List<SvgPath> getPathes() {
        return pathes;
    }

    public void setPathes(List<SvgPath> pathes) {
        this.pathes = pathes;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
