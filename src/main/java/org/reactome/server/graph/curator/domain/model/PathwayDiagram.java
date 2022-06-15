package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeInstanceDefiningValue;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.relationship.HasComponent;
import org.reactome.server.graph.curator.domain.relationship.RepresentedPathway;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.*;

public class PathwayDiagram  extends DatabaseObject {

    public PathwayDiagram() {}

    @ReactomeProperty
    private Integer width;
    @ReactomeProperty
    private Integer height;
    @ReactomeProperty
    private String storedATXML;

    @ReactomeInstanceDefiningValue(category = ReactomeInstanceDefiningValue.Category.all)
    @Relationship(type = "representedPathway")
    private SortedSet<RepresentedPathway> representedPathway;

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return this.height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getStoredATXML() {
        return this.storedATXML;
    }

    public void setStoredATXML(String height) {
        this.storedATXML = storedATXML;
    }

    public List<Pathway> getRepresentedPathway(){
        List<Pathway> rtn = null;
        if (this.representedPathway != null) {
            rtn = new ArrayList<>();
            for (RepresentedPathway representedPathway : this.representedPathway) {
                for (int i = 0; i < representedPathway.getStoichiometry(); i++) {
                    rtn.add(representedPathway.getPathway());
                }
            }
        }
        return rtn;
    }

    public void setRepresentedPathway(List<Pathway> representedPathway) {
        if (representedPathway == null) return;
        Map<Long, RepresentedPathway> representedPathways = new LinkedHashMap<>();
        int order = 0;
        for (Pathway pathway : representedPathway) {
            RepresentedPathway rp = representedPathways.get(pathway.getDB_ID());
            if (rp != null) {
                rp.setStoichiometry(rp.getStoichiometry() + 1);
            } else {
                rp = new RepresentedPathway();
                rp.setPathway(pathway);
                rp.setOrder(order++);
                representedPathways.put(pathway.getDB_ID(), rp);
            }
        }
        this.representedPathway = new TreeSet<>(representedPathways.values());
    }
}
