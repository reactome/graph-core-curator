package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
public class CellLineagePath extends Pathway {

    @Relationship(type = "tissue")
    private Anatomy tissue;

    public CellLineagePath() {
    }

    public Anatomy getTissue() {
        return tissue;
    }

    public void setTissue(Anatomy tissue) {
        this.tissue = tissue;
    }
}