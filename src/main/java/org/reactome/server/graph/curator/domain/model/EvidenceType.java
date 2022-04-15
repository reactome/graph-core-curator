package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class EvidenceType extends DatabaseObject {

    @ReactomeProperty
    private String definition;
    @ReactomeProperty
    private List<String> name;

    @Relationship(type = "instanceOf")
    private List<EvidenceType> instanceOf;

    public EvidenceType() {}

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<EvidenceType> getInstanceOf() {
        return instanceOf;
    }

    public void setInstanceOf(List<EvidenceType> instanceOf) {
        this.instanceOf = instanceOf;
    }
}
