package by.pomidor.domain.svg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "svg")
@JsonIgnoreProperties(ignoreUnknown = true)//если добавится новый элемент, то игнориуем его
public class SvgRootElement {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "g")
    private List<SvgGroup> svgGroups;
    //это группы svg (страны) это который мы создали (содержит список)
    @JacksonXmlProperty(isAttribute = true, localName = "baseprofile")
    //если атрибут то хорошо
    private String baseProfile;
    @JacksonXmlProperty(isAttribute = true, localName = "fill")
    private String fill;
    @JacksonXmlProperty(isAttribute = true, localName = "height")
    private String height;
    @JacksonXmlProperty(isAttribute = true, localName = "stroke")
    private String stroke;
    @JacksonXmlProperty(isAttribute = true, localName = "stroke-linecap")
    private String strokeLinecap;
    @JacksonXmlProperty(isAttribute = true, localName = "stroke-linejoin")
    private String strokeLinejoin;
    @JacksonXmlProperty(isAttribute = true, localName = "stroke-width")
    private String strokeWidth;
    @JacksonXmlProperty(isAttribute = true, localName = "version")
    private String version;
    @JacksonXmlProperty(isAttribute = true, localName = "viewbox")
    private String viewbox;
    @JacksonXmlProperty(isAttribute = true, localName = "width")
    private String width;
    @JacksonXmlProperty(isAttribute = true, localName = "xmlns")
    private String xmlns;

    public List<SvgGroup> getGroups() {
        return svgGroups;
    }

    public void setGroups(List<SvgGroup> svgGroups) {
        this.svgGroups = svgGroups;
    }

    public String getBaseProfile() {
        return baseProfile;
    }

    public void setBaseProfile(String baseProfile) {
        this.baseProfile = baseProfile;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public String getStrokeLinecap() {
        return strokeLinecap;
    }

    public void setStrokeLinecap(String strokeLinecap) {
        this.strokeLinecap = strokeLinecap;
    }

    public String getStrokeLinejoin() {
        return strokeLinejoin;
    }

    public void setStrokeLinejoin(String strokeLinejoin) {
        this.strokeLinejoin = strokeLinejoin;
    }

    public String getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(String strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
}