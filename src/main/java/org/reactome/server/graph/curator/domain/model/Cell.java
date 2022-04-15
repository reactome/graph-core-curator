package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeAllowedClasses;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Cell extends PhysicalEntity {
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED);
    @Relationship(type = "rnaMarker")
    private List<EntityWithAccessionedSequence> RNAMarker;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED);
    @Relationship(type = "markerReference")
    private List<MarkerReference> markerReference;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
    @Relationship(type = "organ")
    private Anatomy organ;

    @Relationship(type = "proteinMarker")
    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.REQUIRED);
    @ReactomeAllowedClasses(allowed = {Complex.class, EntityWithAccessionedSequence.class})
    private List<PhysicalEntity> proteinMarker;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "species")
    private List<Taxon> species;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.MANDATORY);
    @Relationship(type = "tissue")
    private Anatomy tissue;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.OPTIONAL);
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
