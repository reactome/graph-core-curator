package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

public class _DeletedInstance extends DatabaseObject{

    public _DeletedInstance() {}

    @ReactomeProperty
    private String className;
    @ReactomeProperty
    private Long deletedInstanceDB_ID;
    @ReactomeProperty
    private String name;

    @Relationship(type = "deletedStableIdentifier")
    private StableIdentifier deletedStableIdentifier;
    @Relationship(type = "species")
    private List<Taxon> species;

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDeletedInstanceDB_ID() {
        return deletedInstanceDB_ID;
    }

    public void setDeletedInstanceDB_ID(Long deletedInstanceDB_ID) {
        this.deletedInstanceDB_ID = deletedInstanceDB_ID;
    }

    public StableIdentifier getDeletedStableIdentifier() {
        return deletedStableIdentifier;
    }

    public void setDeletedStableIdentifier(StableIdentifier deletedStableIdentifier) {
        this.deletedStableIdentifier = deletedStableIdentifier;
    }

    public List<Taxon> getSpecies() {
        return species;
    }

    public void setSpecies(List<Taxon> species) {
        this.species = species;
    }
}
