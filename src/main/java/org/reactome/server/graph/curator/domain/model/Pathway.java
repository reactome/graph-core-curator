package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

/**
 * A collection of related Events. These events can be ReactionLikeEvents or Pathways
 */
@SuppressWarnings("unused")
@Node
public class Pathway extends Event {

    @ReactomeProperty
    private String doi;

    @ReactomeProperty
    private Boolean hasEHLD = false;

    @ReactomeProperty
    private Boolean isCanonical;

    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    @Relationship(type = "hasEvent")
    private List<Event> hasEvent;

    @Relationship(type = "normalPathway")
    private Pathway normalPathway;

    public Pathway() {
    }

    public Pathway(Long dbId) {
        super(dbId);
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public Boolean getHasEHLD() {
        return hasEHLD;
    }

    public void setHasEHLD(Boolean hasEHLD) {
        this.hasEHLD = hasEHLD;
    }

    public Boolean getIsCanonical() {
        return isCanonical;
    }

    public void setIsCanonical(Boolean isCanonical) {
        this.isCanonical = isCanonical;
    }

    public List<Compartment> getCompartment() {
        return compartment;
    }

    public void setCompartment(List<Compartment> compartment) {
        this.compartment = compartment;
    }

    public List<Event> getHasEvent() {
        return hasEvent;

    }

    public void setHasEvent(List<Event> hasEvent) {
        this.hasEvent = hasEvent;
    }

    public Pathway getNormalPathway() {
        return normalPathway;
    }

    public void setNormalPathway(Pathway normalPathway) {
        this.normalPathway = normalPathway;
    }

    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "A collection of related Events. These events can be ReactionLikeEvents or Pathways";
    }
}
