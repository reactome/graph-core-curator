package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class DatabaseIdentifier extends DatabaseObject {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeProperty
    private String identifier;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "crossReference")
    private List<DatabaseIdentifier> crossReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "referenceDatabase")
    private ReferenceDatabase referenceDatabase;

    public DatabaseIdentifier() {}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public void setCrossReference(List<DatabaseIdentifier> crossReference) {
        this.crossReference = crossReference;
    }

    public ReferenceDatabase getReferenceDatabase() {
        return referenceDatabase;
    }

    public void setReferenceDatabase(ReferenceDatabase referenceDatabase) {
        this.referenceDatabase = referenceDatabase;
    }
}
