package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * Two or more entities grouped because of a shared molecular feature. The superclass for CandidateSet and DefinedSet.
 */
@SuppressWarnings("unused")
@Node
public abstract class EntitySet extends PhysicalEntity {

    @ReactomeProperty
    private Boolean isOrdered;

    @Relationship(type = "compartment")
    private Compartment compartment;

    @Relationship(type = "hasMember")
    private List<PhysicalEntity> hasMember;

    @Relationship(type = "species")
    private List<Species> species;

    @Relationship(type = "relatedSpecies")
    private List<Species> relatedSpecies;

    public EntitySet() {}

    public Boolean getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(Boolean isOrdered) {
        this.isOrdered = isOrdered;
    }

    public List<PhysicalEntity> getHasMember() {
        return hasMember;
    }

    public void setHasMember(List<PhysicalEntity> hasMember) {
        this.hasMember = hasMember;
    }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    public List<Species> getRelatedSpecies() {
        return relatedSpecies;
    }

    public void setRelatedSpecies(List<Species> relatedSpecies) {
        this.relatedSpecies = relatedSpecies;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
    }


    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "Two or more entities grouped because of a shared molecular feature. " +
                "The superclass for CandidateSet and DefinedSet";
    }

    @ReactomeSchemaIgnore
    @Override
    public String getClassName() {
        return "Set";
    }
}
