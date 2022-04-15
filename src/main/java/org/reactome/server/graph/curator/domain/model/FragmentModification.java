package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

@SuppressWarnings("unused")
@Node
public abstract class FragmentModification extends GeneticallyModifiedResidue {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @ReactomeProperty
    private Integer endPositionInReferenceSequence;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @ReactomeProperty
    private Integer startPositionInReferenceSequence;
    
    public FragmentModification() {}

    public Integer getEndPositionInReferenceSequence() {
        return endPositionInReferenceSequence;
    }

    public void setEndPositionInReferenceSequence(Integer endPositionInReferenceSequence) {
        this.endPositionInReferenceSequence = endPositionInReferenceSequence;
    }

    public Integer getStartPositionInReferenceSequence() {
        return startPositionInReferenceSequence;
    }

    public void setStartPositionInReferenceSequence(Integer startPositionInReferenceSequence) {
        this.startPositionInReferenceSequence = startPositionInReferenceSequence;
    }
    
    
    
}
