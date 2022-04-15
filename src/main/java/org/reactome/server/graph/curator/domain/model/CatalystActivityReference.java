package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class CatalystActivityReference extends ControlReference {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "catalystActivity")
    private CatalystActivity catalystActivity;

    public CatalystActivityReference() {
    }

    public CatalystActivity getCatalystActivity() {
        return catalystActivity;
    }

    public void setCatalystActivity(CatalystActivity catalystActivity) {
        this.catalystActivity = catalystActivity;
    }

}
