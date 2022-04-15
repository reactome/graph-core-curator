package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@SuppressWarnings("unused")
@Node
public class FunctionalStatus extends DatabaseObject {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "functionalStatusType")
    private FunctionalStatusType functionalStatusType;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "structuralVariant")
    private SequenceOntology structuralVariant;
    
    public FunctionalStatus() {}

    public FunctionalStatusType getFunctionalStatusType() {
        return functionalStatusType;
    }

    public void setFunctionalStatusType(FunctionalStatusType functionalStatusType) {
        this.functionalStatusType = functionalStatusType;
    }

    public SequenceOntology getStructuralVariant() {
        return structuralVariant;
    }

    public void setStructuralVariant(SequenceOntology structuralVariant) {
        this.structuralVariant = structuralVariant;
    }
}
