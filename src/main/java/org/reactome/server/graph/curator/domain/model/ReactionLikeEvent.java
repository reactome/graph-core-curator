package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

/**
 * Has four subclasses: Reaction, BlackBoxEvent, Polymerisation and Depolymerisation. All involve the conversion of one or more input molecular entities to an output entity, possibly facilitated by a catalyst.
 * <p>
 * Logic in getter/setter of input and output is needed for retrieving data import using the GKInstance.
 * This is still used for testing if graph and sql produce the same data import
 */
@SuppressWarnings("unused")
@Node
public abstract class ReactionLikeEvent extends Event {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @ReactomeProperty
    private Boolean isChimeric;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @ReactomeProperty
    private String systematicName;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED);
    @Relationship(type = "catalystActivity")
    private List<CatalystActivity> catalystActivity;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "catalystActivityReference")
    private CatalystActivityReference catalystActivityReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "entityFunctionalStatus")
    private List<EntityFunctionalStatus> entityFunctionalStatus;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "entityOnOtherCell")
    private List<PhysicalEntity> entityOnOtherCell;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "input")
    private List<PhysicalEntity> input;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "output")
    private List<PhysicalEntity> output;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "normalReaction")
    private ReactionLikeEvent normalReaction;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "regulatedBy")
    private List<Regulation> regulatedBy;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "regulationReference")
    private List<RegulationReference> regulationReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "requiredInputComponent")
    private Set<PhysicalEntity> requiredInputComponent;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "hasInteraction")
    private List<InteractionEvent> hasInteraction;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "reactionType")
    private List<ReactionType> reactionType;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    public ReactionLikeEvent() {
    }

    public ReactionLikeEvent(Long dbId) {
        super(dbId);
    }

    public Boolean getIsChimeric() {
        return isChimeric;
    }

    public void setIsChimeric(Boolean isChimeric) {
        this.isChimeric = isChimeric;
    }

    public String getSystematicName() {
        return systematicName;
    }

    public void setSystematicName(String systematicName) {
        this.systematicName = systematicName;
    }

    public List<CatalystActivity> getCatalystActivity() {
        return catalystActivity;
    }

    public void setCatalystActivity(List<CatalystActivity> catalystActivity) {
        this.catalystActivity = catalystActivity;
    }

    public CatalystActivityReference getCatalystActivityReference() {
        return catalystActivityReference;
    }

    public void setCatalystActivityReference(CatalystActivityReference catalystActivityReference) {
        this.catalystActivityReference = catalystActivityReference;
    }

    public List<EntityFunctionalStatus> getEntityFunctionalStatus() {
        return entityFunctionalStatus;
    }

    public void setEntityFunctionalStatus(List<EntityFunctionalStatus> entityFunctionalStatus) {
        this.entityFunctionalStatus = entityFunctionalStatus;
    }

    public List<PhysicalEntity> getEntityOnOtherCell() {
        return entityOnOtherCell;
    }

    public void setEntityOnOtherCell(List<PhysicalEntity> entityOnOtherCell) {
        this.entityOnOtherCell = entityOnOtherCell;
    }

    public ReactionLikeEvent getNormalReaction() {
        return normalReaction;
    }

    public void setNormalReaction(ReactionLikeEvent normalReaction) {
        this.normalReaction = normalReaction;
    }

    public List<Regulation> getRegulatedBy() {
        return regulatedBy;
    }

    public void setRegulatedBy(List<Regulation> regulatedBy) {
        this.regulatedBy = regulatedBy;
    }

    public List<RegulationReference> getRegulationReference() {
        return regulationReference;
    }

    public void setRegulationReference(List<RegulationReference> regulationReference) {
        this.regulationReference = regulationReference;
    }

    public Set<PhysicalEntity> getRequiredInputComponent() {
        return requiredInputComponent;
    }

    public void setRequiredInputComponent(Set<PhysicalEntity> requiredInputComponent) {
        this.requiredInputComponent = requiredInputComponent;
    }

    public List<InteractionEvent> getHasInteraction() {
        return hasInteraction;
    }

    public void setHasInteraction(List<InteractionEvent> hasInteraction) {
        this.hasInteraction = hasInteraction;
    }

    public List<ReactionType> getReactionType() {
        return reactionType;
    }

    public void setReactionType(List<ReactionType> reactionType) {
        this.reactionType = reactionType;
    }

    public List<Compartment> getCompartment() {
        return compartment;
    }

    public void setCompartment(List<Compartment> compartment) {
        this.compartment = compartment;
    }

    public List<PhysicalEntity> getInput() {
        return input;
    }

    public void setInput(List<PhysicalEntity> input) {
        this.input = input;
    }

    public void setOutput(List<PhysicalEntity> output) {
        this.output = output;
    }

    public List<PhysicalEntity> getOutput() {
        return output;
    }

    @ReactomeSchemaIgnore
    @Override
    public String getClassName() {
        return "Reaction";
    }
}
