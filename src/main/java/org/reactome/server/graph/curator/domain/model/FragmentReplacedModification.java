package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;

@SuppressWarnings("unused")
@Node
public class FragmentReplacedModification extends FragmentModification {

    @ReactomeProperty
    private String alteredAminoAcidFragment;
    
    public FragmentReplacedModification() {}

    public String getAlteredAminoAcidFragment() {
        return alteredAminoAcidFragment;
    }

    public void setAlteredAminoAcidFragment(String alteredAminoAcidFragment) {
        this.alteredAminoAcidFragment = alteredAminoAcidFragment;
    }
    
    
    
}
