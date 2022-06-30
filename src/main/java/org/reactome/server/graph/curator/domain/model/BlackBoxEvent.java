package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/**
 * Shortcut reactions that make the connection between input and output, but don't provide complete mechanistic detail. Used for reactions that do not balance, or complicated processes for which we either don't know all the details, or we choose not to represent every step. (e.g. degradation of a protein)
 */
@SuppressWarnings("unused")
@Node
public class BlackBoxEvent extends ReactionlikeEvent {

    @ReactomeSchemaIgnore
    @Override
    public String getExplanation() {
        return "Shortcut reactions that make the connection between input and output, but don't provide complete mechanistic detail. " +
                "Used for reactions that do not balance, or complicated processes for which we either don't know all the details, or we choose not to represent every step. (e.g. degradation of a protein)";

    }

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL)
    @Relationship(type = "templateEvent")
    private Event templateEvent;

    public BlackBoxEvent() {}

    public Event getTemplateEvent() {
        return templateEvent;
    }

    public void setTemplateEvent(Event templateEvent) {
        this.templateEvent = templateEvent;
    }
}
