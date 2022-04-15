package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * Molecules that consist of an indeterminate number of repeated units. Includes complexes whose stoichiometry is variable or unknown. The repeated unit(s) is(are) identified in the repeatedUnit slot.
 *
 * Logic in getter/setter of input and output is needed for retrieving data import using the GKInstance.
 * This is still used for testing if graph and sql produce the same data import
 */
@SuppressWarnings("unused")
@Node
public class Polymer extends PhysicalEntity {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Integer maxUnitCount;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Integer minUnitCount;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "repeatedUnit")
    private List<PhysicalEntity> repeatedUnit;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @Relationship(type = "species")
    private List<Species> species;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    public Polymer() {}

    public Polymer(Long dbId) {
        super(dbId);
    }

    public Integer getMaxUnitCount() {
        return maxUnitCount;
    }

    public void setMaxUnitCount(Integer maxUnitCount) {
        this.maxUnitCount = maxUnitCount;
    }

    public Integer getMinUnitCount() {
        return minUnitCount;
    }

    public void setMinUnitCount(Integer minUnitCount) {
        this.minUnitCount = minUnitCount;
    }

    public List<PhysicalEntity> getRepeatedUnit() {
        return repeatedUnit;
    }

    public void setRepeatedUnit(List<PhysicalEntity> repeatedUnit) {
        this.repeatedUnit = repeatedUnit;
   }

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    public List<Compartment> getCompartment() {
        return compartment;
    }

    public void setCompartment(List<Compartment> compartment) {
        this.compartment = compartment;
    }

    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "Molecules that consist of an indeterminate number of repeated units. Includes complexes whose stoichiometry is variable or unknown. " +
                "The repeated unit(s) is(are) identified in the repeatedUnit slot";
    }
}
