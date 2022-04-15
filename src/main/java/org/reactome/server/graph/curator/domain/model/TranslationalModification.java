package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@SuppressWarnings("unused")
@Node
public abstract class TranslationalModification extends AbstractModifiedResidue {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED);
    @ReactomeProperty
    private Integer coordinate;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "psiMod")
    private PsiMod psiMod;
    
    public TranslationalModification() {}

    public TranslationalModification(Long dbId) {
        super(dbId);
    }

    public Integer getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Integer coordinate) {
        this.coordinate = coordinate;
    }

    public PsiMod getPsiMod() {
        return psiMod;
    }

    public void setPsiMod(PsiMod psiMod) {
        this.psiMod = psiMod;
    }
    
}
