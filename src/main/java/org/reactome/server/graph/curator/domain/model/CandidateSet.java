package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * A set of entities that are interchangeable in function, with two subclasses, members that are hypothetical and members that have been demonstrated. Hypothetical members are identified as values of the hasCandidate slot. Members that have been demonstrated are identified in the hasMember slot. At least one hasCandidate value is required; hasMember values are optional.
 */
@SuppressWarnings("unused")
@Node
public class CandidateSet extends EntitySet {

    @Relationship(type = "hasCandidate")
    private List<PhysicalEntity> hasCandidate;

    public CandidateSet() {}

    public List<PhysicalEntity> getHasCandidate() {
        return hasCandidate;
    }

    public void setHasCandidate(List<PhysicalEntity> hasCandidate) {
        this.hasCandidate = hasCandidate;
    }

    @ReactomeSchemaIgnore
    @Override
    @JsonIgnore
    public String getExplanation() {
        return "A set of entities that are interchangeable in function, with two subclasses, members that are hypothetical and members that have been demonstrated. " +
                "Hypothetical members are identified as values of the hasCandidate slot. Members that have been demonstrated are identified in the hasMember slot. " +
                "At least one hasCandidate value is required; hasMember values are optional";

    }
}
