package org.reactome.server.graph.curator.domain.model;

import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@SuppressWarnings("unused")
@Node
public class ReferenceGeneProduct extends ReferenceSequence {

    @ReactomeProperty
    private List<String> chain;

    @ReactomeProperty
    private String chainChangeLog;

    @Relationship(type = "referenceGene")
    private List<ReferenceDNASequence> referenceGene;

    @Relationship(type = "referenceTranscript")
    private List<ReferenceRNASequence> referenceTranscript;

    public ReferenceGeneProduct() {}

    public String getChainChangeLog() {
        return chainChangeLog;
    }

    public void setChainChangeLog(String chainChangeLog) {
        this.chainChangeLog = chainChangeLog;
    }

    public List<String> getChain() {
        return chain;
    }

    public void setChain(List<String> chain) {
        this.chain = chain;
    }

    public List<ReferenceDNASequence> getReferenceGene() {
        return referenceGene;
    }

    public void setReferenceGene(List<ReferenceDNASequence> referenceGene) {
        this.referenceGene = referenceGene;
    }

    public List<ReferenceRNASequence> getReferenceTranscript() {
        return referenceTranscript;
    }

    public void setReferenceTranscript(List<ReferenceRNASequence> referenceTranscript) {
        this.referenceTranscript = referenceTranscript;
    }

    
}
