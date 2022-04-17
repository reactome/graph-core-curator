package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class PathwayDiagram  extends DatabaseObject {

    public PathwayDiagram() {}

    @ReactomeProperty
    private Integer width;
    @ReactomeProperty
    private Integer height;
    @ReactomeProperty
    private String storedATXML;

    @Relationship(type = "representedPathway")
    private List<Pathway> representedPathway;

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getStoredATXML() {
        return this.storedATXML;
    }

    public void setStoredATXML(String height) {
        this.storedATXML = storedATXML;
    }

    public List<Pathway> getAuthored() {
        return representedPathway;
    }

    public void setAuthored(List<Pathway> authored) {
        this.representedPathway = representedPathway;
    }
}
