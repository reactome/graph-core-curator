package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class ReferenceRNASequence extends ReferenceSequence {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "referenceGene")
    private List<ReferenceDNASequence> referenceGene;
    
    public ReferenceRNASequence() {}

    public List<ReferenceDNASequence> getReferenceGene() {
        return referenceGene;
    }

    public void setReferenceGene(List<ReferenceDNASequence> referenceGene) {
        this.referenceGene = referenceGene;
    }
    
}
