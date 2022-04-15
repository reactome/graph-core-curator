package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
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

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Boolean isOrdered;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "compartment")
    private Compartment compartment;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "hasMember")
    private List<PhysicalEntity> hasMember;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @Relationship(type = "species")
    private List<Species> species;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
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
