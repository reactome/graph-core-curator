package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class FrontPage extends DatabaseObject {

    @Relationship(type = "frontPageItem")
    private List<Event> frontPageItem;

    public FrontPage() {}

    public List<Event> getFrontPageItem() {
        return this.frontPageItem;
    }

    public void setFrontPageItem(List<Event> frontPageItem) {
        this.frontPageItem = frontPageItem;
    }
}
