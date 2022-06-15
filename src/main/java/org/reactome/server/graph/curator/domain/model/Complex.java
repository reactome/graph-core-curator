package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeInstanceDefiningValue;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.reactome.server.graph.curator.domain.relationship.HasCompartment;
import org.reactome.server.graph.curator.domain.relationship.HasComponent;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * An entity formed by the association of two or more component entities (these components can themselves be complexes). Complexes represent all experimentally verified components and their stoichiometry where this is known but may not include as yet unidentified components. At least one component must be specified.
 */
@SuppressWarnings("unused")
@Node
public class Complex extends PhysicalEntity {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Boolean isChimeric;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "compartment")
    private Compartment compartment;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "hasComponent")
    private SortedSet<HasComponent> hasComponent;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Boolean stoichiometryKnown;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "entityOnOtherCell")
    private List<PhysicalEntity> entityOnOtherCell;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "includedLocation")
    private SortedSet<HasCompartment> includedLocation;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @Relationship(type = "species")
    private List<Species> species;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
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
        List<PhysicalEntity> rtn = null;
        if (this.hasComponent != null) {
            rtn = new ArrayList<>();
            for (HasComponent component : this.hasComponent) {
                for (int i = 0; i < component.getStoichiometry(); i++) {
                    rtn.add(component.getPhysicalEntity());
                }
            }
        }
        return rtn;
    }

    public void setHasComponent(List<PhysicalEntity> hasComponent) {
        if (hasComponent == null) return;
        Map<Long, HasComponent> components = new LinkedHashMap<>();
        int order = 0;
        for (PhysicalEntity physicalEntity : hasComponent) {
            HasComponent component = components.get(physicalEntity.getDB_ID());
            if (component != null) {
                component.setStoichiometry(component.getStoichiometry() + 1);
            } else {
                component = new HasComponent();
                component.setPhysicalEntity(physicalEntity);
                component.setOrder(order++);
                components.put(physicalEntity.getDB_ID(), component);
            }
        }
        this.hasComponent = new TreeSet<>(components.values());
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
        if (includedLocation == null) return null;
        List<Compartment> rtn = new ArrayList<>();
        for (HasCompartment c : includedLocation) {
            rtn.add(c.getCompartment());
        }
        return rtn;
    }

    public void setIncludedLocation(SortedSet<HasCompartment> includedLocation) {
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
