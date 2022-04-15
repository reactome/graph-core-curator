package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

/**
 * A peptide or polynucleotide whose sequence is unknown and thus cannot be linked to external sequence databases or used for orthology inference.
 */
@SuppressWarnings("unused")
@Node
public class GenomeEncodedEntity extends PhysicalEntity {


    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "species")
    private Taxon species;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    public GenomeEncodedEntity() {}

    public GenomeEncodedEntity(Long dbId) {
        super(dbId);
    }

    public Taxon getSpecies() {
        return species;
    }

    public void setSpecies(Taxon species) {
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
        return "A peptide or polynucleotide whose sequence is unknown and thus cannot be linked to external sequence databases or used for orthology inference";
    }

    @ReactomeSchemaIgnore
    @Override
    public String getClassName() {
        return "Genes and Transcripts";
    }
}
