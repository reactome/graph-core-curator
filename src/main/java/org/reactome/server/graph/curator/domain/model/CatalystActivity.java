package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;
import java.util.Set;

/**
 * CatalystActivity describes a instance of biological catalysis. With active units it is possible to specify the exact active component of a complex or set.
 */
@SuppressWarnings("unused")
@Node
public class CatalystActivity extends DatabaseObject  {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "activeUnit")
    private Set<PhysicalEntity> activeUnit;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @Relationship(type = "activity")
    private GO_MolecularFunction activity;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "physicalEntity")
    private PhysicalEntity physicalEntity;

    public CatalystActivity() {}

    public CatalystActivity(Long dbId) {
        super(dbId);
    }

    public Set<PhysicalEntity> getActiveUnit() {
        return activeUnit;
    }

    public void setActiveUnit(Set<PhysicalEntity> activeUnit) {
        this.activeUnit = activeUnit;
    }

    public GO_MolecularFunction getActivity() {
        return activity;
    }

    public void setActivity(GO_MolecularFunction activity) {
        this.activity = activity;
    }

    public PhysicalEntity getPhysicalEntity() {
        return physicalEntity;
    }

    public void setPhysicalEntity(PhysicalEntity physicalEntity) {
        this.physicalEntity = physicalEntity;
    }

}
