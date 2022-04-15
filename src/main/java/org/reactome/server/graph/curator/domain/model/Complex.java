package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * An entity formed by the association of two or more component entities (these components can themselves be complexes). Complexes represent all experimentally verified components and their stoichiometry where this is known but may not include as yet unidentified components. At least one component must be specified.
 */
@SuppressWarnings("unused")
@Node
public class Complex extends PhysicalEntity {

    @ReactomeProperty
    private Boolean isChimeric;

    @Relationship(type = "compartment")
    private Compartment compartment;

    @Relationship(type = "hasComponent")
    private List<PhysicalEntity> hasComponent;

    @ReactomeProperty
    private Boolean stoichiometryKnown;

    @Relationship(type = "entityOnOtherCell")
    private List<PhysicalEntity> entityOnOtherCell;

    @Relationship(type = "includedLocation")
    private List<Compartment> includedLocation;

    @Relationship(type = "species")
    private List<Species> species;

    @Relationship(type = "relatedSpecies")
    private List<Species> relatedSpecies;

    public Complex() {}

    public Complex(Long dbId) {
        super(dbId);
    }

    public Boolean getIsChimeric() {
        return isChimeric;
    }

    public void setIsChimeric(Boolean isChimeric) {
        this.isChimeric = isChimeric;
    }

    public List<PhysicalEntity> getHasComponent(){
        return hasComponent;
    }

    public void setHasComponent(List<PhysicalEntity> hasComponent) {
        this.hasComponent = hasComponent;
    }

    public Boolean getStoichiometryKnown() {
        return stoichiometryKnown;
    }

    public void setStoichiometryKnown(Boolean stoichiometryKnown) {
        this.stoichiometryKnown = stoichiometryKnown;
    }

    public List<PhysicalEntity> getEntityOnOtherCell() {
        return entityOnOtherCell;
    }

    public void setEntityOnOtherCell(List<PhysicalEntity> entityOnOtherCell) {
        this.entityOnOtherCell = entityOnOtherCell;
    }

    public List<Compartment> getIncludedLocation() {
        return includedLocation;
    }

    public void setIncludedLocation(List<Compartment> includedLocation) {
        this.includedLocation = includedLocation;
    }

    public Compartment getCompartment() {
        return compartment;
    }

    public void setCompartment(Compartment compartment) {
        this.compartment = compartment;
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

    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "An entity formed by the association of two or more component entities (these components can themselves be complexes). " +
                "At least one component must be specified. Complexes represent all experimentally verified components and their stoichiometry where this is known but may not include as yet unidentified components";
    }
}
