package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class StableIdentifierReleaseStatus extends DatabaseObject {

    private static enum ReleaseStatus {
        EXISTS,
        CREATED,
        INCREMENTED,
        REPLACED,
        ORTHO
    }

    @ReactomeProperty
    private Integer releaseNumber;
    @ReactomeProperty
    private ReleaseStatus status;

    public Integer getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(Integer releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public String getReleaseStatus() {
        return status.toString();
    }

    public void setReleaseStatus(String releaseStatus) {
        this.status = ReleaseStatus.valueOf(releaseStatus);
    }
}
