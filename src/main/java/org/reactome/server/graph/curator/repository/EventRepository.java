package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.Event;
import org.reactome.server.graph.curator.domain.result.SimpleEventProjection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class EventRepository {

    private final Neo4jClient neo4jClient;
    private final Neo4jTemplate neo4jTemplate;

    @Value("${spring.data.neo4j.database:graph.db}")
    private String databaseName;

    public EventRepository(Neo4jClient neo4jClient, Neo4jTemplate neo4jTemplate) {
        this.neo4jClient = neo4jClient;
        this.neo4jTemplate = neo4jTemplate;
    }

    public Collection<Event> getContainedEventsByStId(String stId){
        String query = "MATCH (p:Pathway)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
                "MATCH (p:Pathway)-[r:hasEvent*]->(e:Event) RETURN e, COLLECT(r), COLLECT(p)";
        return neo4jTemplate.findAll(query, Map.of("stId", stId), Event.class);
    }


    public Collection<Event> getContainedEventsByDbId(Long dbId) {
        String query  = "MATCH (p:Pathway)-[r:hasEvent*]->(e:Event) WHERE p.DB_ID = $dbId RETURN e, COLLECT(r), COLLECT(p)";
        return neo4jTemplate.findAll(query, Map.of("dbId", dbId), Event.class);
    }

    public Collection<SimpleEventProjection> getAuthoredReactionsByDbId(Long dbId){
        String query  = " " +
                "MATCH (pe:Person{DB_ID:$dbId})-[:author]->(ie:InstanceEdit)-[:authored]->(obj:ReactionLikeEvent) " +
                "OPTIONAL MATCH (obj)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WITH DISTINCT ie, pe, obj, s ORDER BY ie.dateTime DESC " +
                "RETURN obj.DB_ID as dbId, s.identifier as stId, obj._displayName as displayName, obj.schemaClass as schemaClass, obj.speciesName as speciesName, pe.DB_ID as authorDbId, ie.dateTime as dateTime, obj.doi as doi, labels(obj) as labels";
        return neo4jClient.query(query).bindAll(Map.of("dbId", dbId)).fetchAs(SimpleEventProjection.class).mappedBy( (type, record) -> SimpleEventProjection.build(record)).all();
    }

    public Collection<SimpleEventProjection> getReviewedReactionsByDbId(Long dbId){
        String query  = " " +
                "MATCH (pe:Person{DB_ID:$dbId})-[:author]->(ie:InstanceEdit)-[:reviewed]->(obj:ReactionLikeEvent) " +
                "OPTIONAL MATCH (obj)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WITH DISTINCT ie, pe, obj, s ORDER BY ie.dateTime DESC " +
                "RETURN obj.DB_ID as dbId, s.identifier as stId, obj._displayName as displayName, obj.schemaClass as schemaClass, obj.speciesName as speciesName, pe.DB_ID as authorDbId, ie.dateTime as dateTime, obj.doi as doi, labels(obj) as labels";
        return neo4jClient.query(query).bindAll(Map.of("dbId", dbId)).fetchAs(SimpleEventProjection.class).mappedBy( (type, record) -> SimpleEventProjection.build(record)).all();
    }

    public Collection<SimpleEventProjection> getAuthoredPathwaysByDbId(Long dbId){
        String query  = " " +
                "MATCH (pe:Person{DB_ID:$dbId})-[:author]->(ie:InstanceEdit)-[:authored]->(obj:Pathway) " +
                "OPTIONAL MATCH (obj)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WITH DISTINCT ie, pe, obj, s ORDER BY ie.dateTime DESC " +
                "RETURN obj.DB_ID as dbId, s.identifier as stId, obj._displayName as displayName, obj.schemaClass as schemaClass, obj.speciesName as speciesName, pe.DB_ID as authorDbId, ie.dateTime as dateTime, obj.doi as doi, labels(obj) as labels";
        return neo4jClient.query(query).bindAll(Map.of("dbId", dbId)).fetchAs(SimpleEventProjection.class).mappedBy( (type, record) -> SimpleEventProjection.build(record)).all();
    }

    public Collection<SimpleEventProjection> getReviewedPathwaysByDbId(Long dbId){
        String query  = " " +
                "MATCH (pe:Person{DB_ID:$dbId})-[:author]->(ie:InstanceEdit)-[:reviewed]->(obj:Pathway) " +
                "OPTIONAL MATCH (obj)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WITH DISTINCT ie, pe, obj, s ORDER BY ie.dateTime DESC " +
                "RETURN obj.DB_ID as dbId, s.identifier as stId, obj._displayName as displayName, obj.schemaClass as schemaClass, obj.speciesName as speciesName, pe.DB_ID as authorDbId, ie.dateTime as dateTime, obj.doi as doi, labels(obj) as labels";
        return neo4jClient.query(query).bindAll(Map.of("dbId", dbId)).fetchAs(SimpleEventProjection.class).mappedBy( (type, record) -> SimpleEventProjection.build(record)).all();
    }

}
