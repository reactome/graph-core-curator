package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeAllowedClasses;
import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class _UpdateTracker extends DatabaseObject {

    public _UpdateTracker() {}

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @ReactomeProperty
    private List<String> action;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "_release")
    private _Release _release;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @Relationship(type = "updatedInstance")
    @ReactomeAllowedClasses(allowed = {Event.class, PhysicalEntity.class})
    private List<DatabaseObject> updatedInstance;

    public List<String> getSpecies() {
        return action;
    }

    public void setSpecies(List<String> action) {
        this.action = action;
    }

    public _Release get_release() {
        return this._release;
    }

    public void set_release(_Release releaseDate) {
        this._release = releaseDate;
    }

    public List<DatabaseObject> getUpdatedInstance() {
        return updatedInstance;
    }

    public void setUpdatedInstance(List<DatabaseObject> replacementInstances) {
        this.updatedInstance = replacementInstances;
    }

}
