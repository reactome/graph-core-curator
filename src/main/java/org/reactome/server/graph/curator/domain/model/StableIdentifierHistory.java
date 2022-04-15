package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class StableIdentifierHistory extends DatabaseObject {

    @Relationship(type = "historyStatus")
    private List<StableIdentifierReleaseStatus> historyStatus;
    @ReactomeProperty
    private String identifier;
    @ReactomeProperty
    private String identifierVersion;

    public List<StableIdentifierReleaseStatus> getHistoryStatus() {
        return historyStatus;
    }

    public void setHistoryStatus(List<StableIdentifierReleaseStatus> historyStatus) {
        this.historyStatus = historyStatus;
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
}
