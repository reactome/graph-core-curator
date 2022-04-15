package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

@SuppressWarnings("unused")
@Node
public class ReferenceGroup extends ReferenceEntity {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @ReactomeProperty
    private String formula;

    public ReferenceGroup() {}

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

}
