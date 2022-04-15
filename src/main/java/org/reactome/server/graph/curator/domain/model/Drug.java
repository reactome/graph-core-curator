package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
@Node
public abstract class Drug extends PhysicalEntity {

    @Relationship(type = "referenceEntity")
    private ReferenceTherapeutic referenceEntity;

    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    public Drug() {}

    public ReferenceTherapeutic getReferenceEntity() {
        return referenceEntity;
    }

    public void setReferenceEntity(ReferenceTherapeutic referenceEntity) {
        this.referenceEntity = referenceEntity;
    }

    public List<Compartment> getCompartment() {
        return compartment;
    }

    public void setCompartment(List<Compartment> compartment) {
        this.compartment = compartment;
    }
}
