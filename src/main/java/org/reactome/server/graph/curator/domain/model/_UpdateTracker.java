package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeAllowedClasses;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class _UpdateTracker extends DatabaseObject {

    public _UpdateTracker() {}

    @ReactomeProperty
    private List<String> action;

    @Relationship(type = "_release")
    private _Release release;

    @Relationship(type = "updatedInstance")
    @ReactomeAllowedClasses(allowed = {Event.class, PhysicalEntity.class})
    private List<DatabaseObject> updatedInstance;

    public List<String> getSpecies() {
        return action;
    }

    public void setSpecies(List<String> action) {
        this.action = action;
    }

    public _Release getRelease() {
        return this.release;
    }

    public void setRelease(_Release releaseDate) {
        this.release = release;
    }

    public List<DatabaseObject> getUpdatedInstance() {
        return updatedInstance;
    }

    public void setUpdatedInstance(List<DatabaseObject> replacementInstances) {
        this.updatedInstance = replacementInstances;
    }

}
