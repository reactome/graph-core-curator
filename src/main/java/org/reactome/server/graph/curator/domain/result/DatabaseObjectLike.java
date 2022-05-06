package org.reactome.server.graph.curator.domain.result;

public interface DatabaseObjectLike {
    Long getDB_ID();
    String getStId();
    String get_displayName();
    String getSchemaClass();
}
