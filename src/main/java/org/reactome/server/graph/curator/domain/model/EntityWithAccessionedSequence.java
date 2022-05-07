package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeInstanceDefiningValue;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * A protein, RNA, or DNA molecule or fragment thereof in a specified cellular compartment and specific post-translational state. Must be linked to an external database reference, given as the value of referenceSequence. An EWAS typically corresponds to the entire protein or polynucleotide described in the external database. Fragments are defined by setting the first and last residue using the numbering scheme of the external database, entered as startCoordinate and endCoordinate values. Values of 1 and -1 respectively indicate that the true start and end are unconfirmed. EWAS instances are specific to a subcellular compartment; if the same molecule is found in two cellular components it will have two EWASes. EWAS instances by default define an unmodified protein sequence, any post-translational modification (PTM), such as phosphorylation, requires a new EWAS instance. The location and type of any PTM are defined in the hasModifiedResidue slot
 */
@SuppressWarnings("unused")
@Node
public class EntityWithAccessionedSequence extends GenomeEncodedEntity {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @ReactomeProperty
    private Integer endCoordinate;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @ReactomeProperty
    private Integer startCoordinate;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "hasModifiedResidue")
    private List<AbstractModifiedResidue> hasModifiedResidue;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "referenceEntity")
    private ReferenceSequence referenceEntity;

    public EntityWithAccessionedSequence() {}

    public EntityWithAccessionedSequence(Long dbId) {
        super(dbId);
    }

    public Integer getEndCoordinate() {
        return endCoordinate;
    }

    public void setEndCoordinate(Integer endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public Integer getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(Integer startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public List<AbstractModifiedResidue> getHasModifiedResidue() {
        return hasModifiedResidue;
    }

    public void setHasModifiedResidue(List<AbstractModifiedResidue> hasModifiedResidue) {
        this.hasModifiedResidue = hasModifiedResidue;
    }

    public ReferenceSequence getReferenceEntity() {
        return referenceEntity;
    }

    public void setReferenceEntity(ReferenceSequence referenceEntity) {
        this.referenceEntity = referenceEntity;
    }

    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "A protein, RNA, or DNA molecule or fragment thereof in a specified cellular compartment and specific post-translational state. " +
                "Must be linked to an external database reference, given as the value of referenceSequence. An EWAS typically corresponds to the entire protein or polynucleotide described in the external database. " +
                "Fragments are defined by setting the first and last residue using the numbering scheme of the external database, entered as startCoordinate and endCoordinate values. Values of 1 and -1 respectively indicate that the true start and end are unconfirmed. " +
                "EWAS instances are specific to a subcellular compartment; if the same molecule is found in two cellular components it will have two EWASes. " +
                "EWAS instances by default define an unmodified protein sequence, any post-translational modification (PTM), such as phosphorylation, requires a new EWAS instance. " +
                "The location and type of any PTM are defined in the hasModifiedResidue slot";
    }
}
