package org.reactome.server.graph.curator.domain.relationship;

import org.reactome.server.graph.curator.domain.model.Pathway;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.util.Objects;

/**
 * representedPathway relationship of PathwayDiagram.
 * It is needed to specify the stoichiometry and order of representedPathway's.
 */
@RelationshipProperties
public class RepresentedPathway implements Comparable<RepresentedPathway> {
    @Id @GeneratedValue private Long id;
    @TargetNode private Pathway pathway;

    private Integer stoichiometry = 1;
    private int order;

    public RepresentedPathway() {}

    public Pathway getPathway() {
        return pathway;
    }

    public void setPathway(Pathway pathway) {
        this.pathway = pathway;
    }

    public Integer getStoichiometry() {
        return stoichiometry;
    }

    public void setStoichiometry(Integer stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(pathway, ((RepresentedPathway) o).pathway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathway);
    }

    @Override
    public int compareTo(RepresentedPathway o) {
        return order - o.order;
    }
}
