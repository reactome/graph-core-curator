package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@Node
public class ReferenceTherapeutic extends ReferenceEntity{

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private String abbreviation;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private List<String> approvalSource;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private List<String> activeDrugIds;
    @ReactomeProperty
    private List<String> proDrugIds;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeProperty
    private Boolean approved;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Boolean withdrawn;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeProperty
    private String inn;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeProperty
    private String type;

    public ReferenceTherapeutic() {
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public List<String> getApprovalSource() {
        return approvalSource;
    }

    public void setApprovalSource(List<String> approvalSource) {
        this.approvalSource = approvalSource;
    }

    public List<String> getActiveDrugIds() {
        return activeDrugIds;
    }

    public void setActiveDrugIds(List<String> activeDrugIds) {
        this.activeDrugIds = activeDrugIds;
    }

    public List<String> getProDrugIds() {
        return activeDrugIds;
    }

    public void setProDrugIds(List<String> proDrugIds) {
        this.proDrugIds = proDrugIds;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Boolean getWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(Boolean approved) {
        this.withdrawn = withdrawn;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
