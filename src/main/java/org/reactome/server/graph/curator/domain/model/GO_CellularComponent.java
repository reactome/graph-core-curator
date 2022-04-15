package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class GO_CellularComponent extends DatabaseObject {

    @ReactomeProperty
    private List<String> name;
    @ReactomeProperty
    private String accession;
    @ReactomeProperty
    private String definition;

    @Relationship(type = "referenceDatabase")
    private ReferenceDatabase referenceDatabase;

    @Relationship(type = "componentOf")
    private List<GO_CellularComponent> componentOf;

    @Relationship(type = "hasPart")
    private List<GO_CellularComponent> hasPart;

    @Relationship(type = "instanceOf")
    private List<GO_CellularComponent> instanceOf;

    @Relationship(type = "surroundedBy")
    private List<GO_CellularComponent> surroundedBy;

    public GO_CellularComponent() {}

    public GO_CellularComponent(Long dbId) {
        super(dbId);
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getAccession() {
        return this.accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<GO_CellularComponent> getComponentOf() {
        return componentOf;
    }

    public void setComponentOf(List<GO_CellularComponent> componentOf) {
        this.componentOf = componentOf;
    }

    public List<GO_CellularComponent> getHasPart() {
        return hasPart;
    }

    public void setHasPart(List<GO_CellularComponent> hasPart) {
        this.hasPart = hasPart;
    }

    public List<GO_CellularComponent> getInstanceOf() {
        return instanceOf;
    }

    public void setInstanceOf(List<GO_CellularComponent> instanceOf) {
        this.instanceOf = instanceOf;
    }

    public List<GO_CellularComponent> getSurroundedBy() {
        return surroundedBy;
    }

    public void setSurroundedBy(List<GO_CellularComponent> surroundedBy) {
        this.surroundedBy = surroundedBy;
    }

    public ReferenceDatabase getReferenceDatabase() {
        return referenceDatabase;
    }

    public void setReferenceDatabase(ReferenceDatabase referenceDatabase) {
        this.referenceDatabase = referenceDatabase;
    }
}
