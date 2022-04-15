package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class StableIdentifier extends DatabaseObject {

    @ReactomeProperty
    private String identifier;
    @ReactomeProperty
    private String identifierVersion;
    @ReactomeProperty
    private String oldIdentifier;
    @ReactomeProperty
    private String oldIdentifierVersion;
    @ReactomeProperty
    private Boolean released;

    @Relationship(type = "history")
    private List<StableIdentifierHistory> history;

    public StableIdentifier() {
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifierVersion() {
        return this.identifierVersion;
    }

    public void setIdentifierVersion(String identifierVersion) {
        this.identifierVersion = identifierVersion;
    }

    public String getOldIdentifier() {
        return oldIdentifier;
    }

    public void setOldIdentifier(String oldIdentifier) {
        this.oldIdentifier = oldIdentifier;
    }

    public String getOldIdentifierVersion() {
        return oldIdentifierVersion;
    }

    public void setOldIdentifierVersion(String oldIdentifierVersion) {
        this.oldIdentifierVersion = oldIdentifierVersion;
    }

    public Boolean getReleased() {
        return released;
    }

    public void setReleased(Boolean released) {
        this.released = released;
    }

    public List<StableIdentifierHistory> getHistory() {
        return history;
    }

    public void setHistoryStatus(List<StableIdentifierHistory> historyStatus) {
        this.history = historyStatus;
    }

}