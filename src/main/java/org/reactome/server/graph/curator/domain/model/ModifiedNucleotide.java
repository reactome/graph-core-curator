package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeAllowedClasses;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class ModifiedNucleotide extends TranscriptionalModification {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeProperty
    private Integer coordinate;

    @Relationship(type = "modification")
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeAllowedClasses(allowed = {EntitySet.class, Polymer.class, ReferenceMolecule.class, ReferenceGroup.class})
    private DatabaseObject modification;

    public ModifiedNucleotide() {
    }

    public Integer getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Integer coordinate) {
        this.coordinate = coordinate;
    }

    public DatabaseObject getModification() {
        return modification;
    }

    public void setModification(DatabaseObject modification) {
        this.modification = modification;
    }

}
