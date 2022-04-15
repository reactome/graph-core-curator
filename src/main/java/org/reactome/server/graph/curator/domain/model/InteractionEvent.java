package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class InteractionEvent extends Event {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "partners")
    private List<PhysicalEntity> partners;

    public InteractionEvent() { }

    public List<PhysicalEntity> getPartners() {
        return partners;
    }

    public void setPartners(List<PhysicalEntity> partners) {
        this.partners = partners;
    }

}
