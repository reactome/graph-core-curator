package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class _Deleted extends DatabaseObject{

    public _Deleted() {}

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @ReactomeProperty
    private String curatorComment;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @ReactomeProperty
    private List<Long> deletedInstanceDB_ID;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "reason")
    private DeletedControlledVocabulary reason;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "deletedInstance")
    private List<_DeletedInstance> deletedInstance;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "replacementInstances")
    private List<DatabaseObject> replacementInstances;

    public String getCuratorComment() { return this.curatorComment; }

    public void setCuratorComment(String curatorComment) {
        this.curatorComment = curatorComment;
    }

    public DeletedControlledVocabulary getReason() { return this.reason; }

    public void setReason(DeletedControlledVocabulary curatorComment) {
        this.reason = reason;
    }

    public List<Long> getDeletedInstanceDB_ID() {
        return deletedInstanceDB_ID;
    }

    public void setDeletedInstanceDB_ID(List<Long> deletedInstanceDB_ID) {
        this.deletedInstanceDB_ID = deletedInstanceDB_ID;
    }

    public List<_DeletedInstance> getDeletedInstance() {
        return deletedInstance;
    }

    public void setDeletedInstance(List<_DeletedInstance> deletedInstance) {
        this.deletedInstance = deletedInstance;
    }

    public List<DatabaseObject> getReplacementInstances() {
        return replacementInstances;
    }

    public void setReplacementInstances(List<DatabaseObject> replacementInstances) {
        this.replacementInstances = replacementInstances;
    }
}
