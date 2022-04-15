package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class ReferenceIsoform extends ReferenceGeneProduct {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @ReactomeProperty
    private String variantIdentifier;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "isoformParent")
    private List<ReferenceGeneProduct> isoformParent;

    public ReferenceIsoform() {}

    public String getVariantIdentifier() {
        return this.variantIdentifier;
    }

    public void setVariantIdentifier(String variantIdentifier) {
        this.variantIdentifier = variantIdentifier;
    }

    public List<ReferenceGeneProduct> getIsoformParent() {
        return isoformParent;
    }

    public void setIsoformParent(List<ReferenceGeneProduct> isoformParent) {
        this.isoformParent = isoformParent;
    }

}
