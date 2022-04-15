package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeAllowedClasses;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Cell extends PhysicalEntity {
    @Relationship(type = "rnaMarker")
    private List<EntityWithAccessionedSequence> RNAMarker;

    @Relationship(type = "markerReference")
    private List<MarkerReference> markerReference;

    @Relationship(type = "organ")
    private Anatomy organ;

    @Relationship(type = "proteinMarker")
    @ReactomeAllowedClasses(allowed = {Complex.class, EntityWithAccessionedSequence.class})
    private List<PhysicalEntity> proteinMarker;

    @Relationship(type = "species")
    private List<Taxon> species;

    @Relationship(type = "tissue")
    private Anatomy tissue;

    @Relationship(type = "tissueLayer")
    private Anatomy tissueLayer;

    public Cell() {

    }

    public List<EntityWithAccessionedSequence> getRNAMarker() {
        return RNAMarker;
    }

    public void setRNAMarker(List<EntityWithAccessionedSequence> rNAMarker) {
        RNAMarker = rNAMarker;
    }

    public List<MarkerReference> getMarkerReference() {
        return markerReference;
    }

    public void setMarkerReference(List<MarkerReference> markerReference) {
        this.markerReference = markerReference;
    }

    public Anatomy getOrgan() {
        return organ;
    }

    public void setOrgan(Anatomy organ) {
        this.organ = organ;
    }

    @ReactomeAllowedClasses(allowed = {Complex.class, EntityWithAccessionedSequence.class})
    public List<PhysicalEntity> getProteinMarker() {
        return proteinMarker;
    }

    public void setProteinMarker(List<PhysicalEntity> proteinMarker) {
        this.proteinMarker = proteinMarker;
    }

    public List<Taxon> getSpecies() {
        return species;
    }

    public void setSpecies(List<Taxon> species) {
        this.species = species;
    }

    public Anatomy getTissue() {
        return tissue;
    }

    public void setTissue(Anatomy tissue) {
        this.tissue = tissue;
    }

    public Anatomy getTissueLayer() {
        return tissueLayer;
    }

    public void setTissueLayer(Anatomy tissueLayer) {
        this.tissueLayer = tissueLayer;
    }
}
