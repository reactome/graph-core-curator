package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class ReferenceGroup extends ReferenceEntity {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private String formula;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT)
    @ReactomeProperty
    private List<String> otherIdentifier;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeProperty
    private List<String> name;

    public ReferenceGroup() {}

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public List<String> getOtherIdentifier() {
        return otherIdentifier;
    }

    public void setOtherIdentifier(List<String> otherIdentifier) {
        this.otherIdentifier = otherIdentifier;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

}
