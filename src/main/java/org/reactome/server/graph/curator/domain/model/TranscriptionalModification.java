package org.reactome.server.graph.curator.domain.model;

import org.springframework.data.neo4j.core.schema.Node;

@SuppressWarnings("unused")
@Node
public abstract class TranscriptionalModification extends AbstractModifiedResidue {

    public TranscriptionalModification() { }
}