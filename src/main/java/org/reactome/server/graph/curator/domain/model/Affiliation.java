package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeConstraint;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class Affiliation extends  DatabaseObject{

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED)
    @ReactomeProperty
    private String address;
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY)
    @ReactomeProperty
    private List<String> name;

    public Affiliation() {}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
