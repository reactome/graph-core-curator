package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeInstanceDefiningValue;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.reactome.server.graph.curator.domain.relationship.Input;
import org.reactome.server.graph.curator.domain.relationship.Output;
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
public abstract class ReactionlikeEvent extends Event {

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private Boolean isChimeric;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeProperty
    private String systematicName;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "catalystActivity")
    private List<CatalystActivity> catalystActivity;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "catalystActivityReference")
    private CatalystActivityReference catalystActivityReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "entityFunctionalStatus")
    private List<EntityFunctionalStatus> entityFunctionalStatus;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "entityOnOtherCell")
    private List<PhysicalEntity> entityOnOtherCell;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "input")
    private Set<Input> input;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "output")
    private Set<Output> output;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "normalReaction")
    private ReactionlikeEvent normalReaction;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "regulatedBy")
    private List<Regulation> regulatedBy;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "regulationReference")
    private List<RegulationReference> regulationReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "requiredInputComponent")
    private Set<PhysicalEntity> requiredInputComponent;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "hasInteraction")
    private List<InteractionEvent> hasInteraction;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "reactionType")
    private List<ReactionType> reactionType;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @Relationship(type = "compartment")
    private List<Compartment> compartment;

    public ReactionlikeEvent() {
    }

    public ReactionlikeEvent(Long dbId) {
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

    public ReactionlikeEvent getNormalReaction() {
        return normalReaction;
    }

    public void setNormalReaction(ReactionlikeEvent normalReaction) {
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
        List<PhysicalEntity> rtn = null;
        if (input != null) {
            rtn = new ArrayList<>();
            for (Input aux : input) {
                for (int i = 0; i < aux.getStoichiometry(); i++) {
                    rtn.add(aux.getPhysicalEntity());
                }
            }
        }
        return rtn;
    }

    public void setInput(List<PhysicalEntity> inputs) {
        if (inputs == null) return;
        // Using LinkedHashMap in order to keep the Collection Sorted previously by AOP
        Map<Long, Input> map = new LinkedHashMap<>();
        for (PhysicalEntity physicalEntity : inputs) {
            Input input = map.get(physicalEntity.getDB_ID());
            if (input == null) {
                input = new Input();
//                input.setReactionLikeEvent(this);
                input.setPhysicalEntity(physicalEntity);
                map.put(physicalEntity.getDB_ID(), input);
            } else {
                input.setStoichiometry(input.getStoichiometry() + 1);
            }
        }
        this.input = new HashSet<>(map.values());
    }

    public void setOutput(Set<Output> output) {
        this.output = output;
    }

    public List<PhysicalEntity> getOutput() {
        List<PhysicalEntity> rtn = null;
        if (output != null) {
            rtn = new ArrayList<>();
            for (Output aux : output) {
                for (int i = 0; i < aux.getStoichiometry(); i++) {
                    rtn.add(aux.getPhysicalEntity());
                }
            }
        }
        return rtn;
    }

    @ReactomeSchemaIgnore
    @Override
    public String getClassName() {
        return "Reaction";
    }
}
