package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

@SuppressWarnings("unused")
@Node
public class Person extends DatabaseObject {

    @ReactomeProperty
    private String firstname;
    @ReactomeProperty
    private String initial;
    @ReactomeProperty
    private String project;
    @ReactomeProperty
    private String surname;
    @ReactomeProperty
    private String url;

    @Relationship(type = "affiliation")
    private List<Affiliation> affiliation;

    @Relationship(type = "figure")
    private List<Figure> figure;

    @Relationship(type = "crossReference")
    private List<DatabaseIdentifier> crossReference;

    public Person() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Affiliation> getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(List<Affiliation> affiliation) {
        this.affiliation = affiliation;
    }

    public List<Figure> getFigure() {
        return figure;
    }

    public void setFigure(List<Figure> figure) {
        this.figure = figure;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DatabaseIdentifier> getCrossReference() {
        return crossReference;
    }

    public void setCrossReference(List<DatabaseIdentifier> crossReference) {
        this.crossReference = crossReference;
    }
}
