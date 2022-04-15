package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.List;

@SuppressWarnings("unused")
@Node
public abstract class Publication extends DatabaseObject {

    @ReactomeProperty
    private String title;

    @Relationship(type = "author", direction = Relationship.Direction.INCOMING)
    private List<Person> author;

    public Publication() {}

    public Publication(Long dbId) {
        super(dbId);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Person> getAuthor() {
        return author;
    }

    public void setAuthor(List<Person> author) {
        this.author = author;
    }
}
