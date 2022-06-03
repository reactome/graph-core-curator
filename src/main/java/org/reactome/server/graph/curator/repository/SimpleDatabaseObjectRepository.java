package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.result.SimpleDatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleDatabaseObjectRepository {

    private final Neo4jClient neo4jClient;

    @Value("${spring.data.neo4j.database:graph.db}")
    private String databaseName;

    @Autowired
    public SimpleDatabaseObjectRepository(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }


    public Collection<SimpleDatabaseObject> getPathwaysForIdentifierByStId(String identifier, Collection<String> pathways){
        String query = " " +
                "MATCH (p:Pathway)-[:regulatedBy|regulator|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|hasMember|hasCandidate|hasComponent|repeatedUnit|input|output|hasEvent*]->(pe:PhysicalEntity) " +
                "MATCH (p)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier IN $stIds " +
                "WITH DISTINCT p, pe " +
                "MATCH (pe)-[:referenceEntity|referenceSequence|crossReference|referenceGene*]->(n)-->(rd:ReferenceDatabase) " +
                "WHERE n.identifier = $identifier OR $identifier IN n.name OR $identifier IN n.geneName " +
                "RETURN DISTINCT p.DB_ID AS dbId, s.identifier AS stId, p._displayName AS displayName, labels(p) AS labels " +
                "UNION " + //The second part is for the cases when identifier is STABLE_IDENTIFIER
                "MATCH (p:Pathway)-[:regulatedBy|regulator|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|hasMember|hasCandidate|hasComponent|repeatedUnit|input|output|hasEvent*]->(pe:PhysicalEntity) " +
                "MATCH (p)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier IN $stIds " +
                "MATCH (pe)-[:stableIdentifier]->(s1:StableIdentifier) WHERE s1.identifier = $identifier " +
                "RETURN DISTINCT p.DB_ID AS dbId, s.identifier AS stId, p._displayName AS displayName, labels(p) AS labels";

        Map<String, Object> map = new HashMap<>(2);
        map.put("identifier", identifier);
        map.put("stIds", pathways);
        return neo4jClient.query(query).in(databaseName).bindAll(map).fetchAs(SimpleDatabaseObject .class).mappedBy( (ts, rec) -> SimpleDatabaseObject.build(rec)).all();
    }

    public Collection<SimpleDatabaseObject> getPathwaysForIdentifierByDbId(String identifier, Collection<Long> pathways){
        String query = " " +
                "MATCH (p:Pathway)-[:regulatedBy|regulator|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|hasMember|hasCandidate|hasComponent|repeatedUnit|input|output|hasEvent*]->(pe:PhysicalEntity) " +
                "OPTIONAL MATCH (p)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WHERE p.DB_ID IN $dbIds " +
                "WITH DISTINCT p, pe " +
                "MATCH (pe)-[:referenceEntity|referenceSequence|crossReference|referenceGene*]->(n)-->(rd:ReferenceDatabase) " +
                "WHERE n.identifier = $identifier OR $identifier IN n.name OR $identifier IN n.geneName " +
                "RETURN DISTINCT p.DB_ID AS dbId, s.identifier AS stId, p._displayName AS displayName, labels(p) AS labels " +
                "UNION " + //The second part is for the cases when identifier is STABLE_IDENTIFIER
                "MATCH (p:Pathway)-[:regulatedBy|regulator|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|hasMember|hasCandidate|hasComponent|repeatedUnit|input|output|hasEvent*]->(pe:PhysicalEntity) " +
                "MATCH (pe)-[:stableIdentifier]->(s1:StableIdentifier) WHERE s1.identifier = $identifier " +
                "OPTIONAL MATCH (p)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WHERE p.DB_ID IN $dbIds " +
                "RETURN DISTINCT p.DB_ID AS dbId, s.identifier AS stId, p._displayName AS displayName, labels(p) AS labels";

        Map<String, Object> map = new HashMap<>(2);
        map.put("identifier", identifier);
        map.put("dbIds", pathways);

        return neo4jClient.query(query).in(databaseName).bindAll(map).fetchAs(SimpleDatabaseObject .class).mappedBy( (ts, rec) -> SimpleDatabaseObject.build(rec)).all();
    }

    public Collection<SimpleDatabaseObject> getDiagramEntitiesForIdentifierByStId(String stId, String identifier) {
        String query = " " +
                "MATCH (t:Pathway) " +
                "MATCH (t)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
                "OPTIONAL MATCH path=(t)-[:hasEvent*]->(p:Pathway{hasDiagram:False}) " +
                "WHERE ALL(n IN TAIL(NODES(path)) WHERE n.hasDiagram = False) " +
                "WITH CASE WHEN path IS NULL THEN t ELSE NODES(path) END AS ps " +
                "UNWIND ps AS p " +
                "MATCH (p)-[:hasEvent]->(rle:ReactionLikeEvent) " +
                "OPTIONAL MATCH (rle)-[:stableIdentifier]->(s1:StableIdentifier) " +
                "WITH DISTINCT rle " +
                "MATCH (rd:ReferenceDatabase)<--(n)<-[:referenceEntity|referenceSequence|crossReference|referenceGene|hasComponent|hasMember|hasCandidate|repeatedUnit*]-(pe)<-[:input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]-(rle) " +
                "WHERE n.identifier = $identifier OR $identifier IN n.name OR $identifier IN n.geneName " +
                "RETURN DISTINCT rle.DB_ID AS dbId, s1.identifier AS stId, rle._displayName AS displayName, labels(rle) AS labels " +
                "UNION " + //The second part is for the cases when identifier is STABLE_IDENTIFIER
                "MATCH (t:Pathway) " +
                "MATCH (t)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
                "OPTIONAL MATCH path=(t)-[:hasEvent*]->(p:Pathway{hasDiagram:False}) " +
                "WHERE ALL(n IN TAIL(NODES(path)) WHERE n.hasDiagram = False) " +
                "WITH CASE WHEN path IS NULL THEN t ELSE NODES(path) END AS ps " +
                "UNWIND ps AS p " +
                "MATCH (p)-[:hasEvent]->(rle:ReactionLikeEvent) " +
                "WITH DISTINCT rle " +
                "MATCH (rle)-[:input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]->(pe:PhysicalEntity) " +
                "WITH DISTINCT pe " +
                "OPTIONAL MATCH (pe)-[:stableIdentifier]->(s1:StableIdentifier) " +
                "OPTIONAL MATCH (pe)-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(a:PhysicalEntity) " +
                "OPTIONAL MATCH (a)-[:stableIdentifier]->(s2:StableIdentifier) " +
                "WITH DISTINCT pe, COLLECT(DISTINCT s2.identifier) AS participants " +
                "WHERE s1.identifier = $identifier OR $identifier IN participants " +
                "RETURN DISTINCT pe.DB_ID AS dbId, s1.identifier AS stId, pe._displayName AS displayName, labels(pe) AS labels";

        Map<String, Object> map = new HashMap<>(2);
        map.put("identifier", identifier);
        map.put("stId", stId);

        return neo4jClient.query(query).in(databaseName).bindAll(map).fetchAs(SimpleDatabaseObject .class).mappedBy( (ts, rec) -> SimpleDatabaseObject.build(rec)).all();
    }

    public Collection<SimpleDatabaseObject> getDiagramEntitiesForIdentifierByDbId(Long dbId, String identifier) {
        String query = " " +
                "MATCH (t:Pathway{DB_ID:$dbId}) " +
                "OPTIONAL MATCH path=(t)-[:hasEvent*]->(p:Pathway{hasDiagram:False}) " +
                "WHERE ALL(n IN TAIL(NODES(path)) WHERE n.hasDiagram = False) " +
                "WITH CASE WHEN path IS NULL THEN t ELSE NODES(path) END AS ps " +
                "UNWIND ps AS p " +
                "MATCH (p)-[:hasEvent]->(rle:ReactionLikeEvent) " +
                "OPTIONAL MATCH (rle)-[:stableIdentifier]->(s:StableIdentifier) " +
                "WITH DISTINCT rle " +
                "MATCH (rd:ReferenceDatabase)<--(n)<-[:referenceEntity|referenceSequence|crossReference|referenceGene|hasComponent|hasMember|hasCandidate|repeatedUnit*]-(pe)<-[:input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]-(rle) " +
                "WHERE n.identifier = $identifier OR $identifier IN n.name OR $identifier IN n.geneName " +
                "RETURN DISTINCT rle.DB_ID AS dbId, s.identifier AS stId, rle._displayName AS displayName, labels(pe) AS labels " +
                "UNION " + //The second part is for the cases when identifier is STABLE_IDENTIFIER
                "MATCH (t:Pathway{DB_ID:$dbId}) " +
                "OPTIONAL MATCH path=(t)-[:hasEvent*]->(p:Pathway{hasDiagram:False}) " +
                "WHERE ALL(n IN TAIL(NODES(path)) WHERE n.hasDiagram = False) " +
                "WITH CASE WHEN path IS NULL THEN t ELSE NODES(path) END AS ps " +
                "UNWIND ps AS p " +
                "MATCH (p)-[:hasEvent]->(rle:ReactionLikeEvent) " +
                "WITH DISTINCT rle " +
                "MATCH (rle)-[:input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]->(pe:PhysicalEntity) " +
                "WITH DISTINCT pe " +
                "OPTIONAL MATCH (pe)-[:stableIdentifier]->(s:StableIdentifier) " +
                "OPTIONAL MATCH (pe)-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(a:PhysicalEntity) " +
                "OPTIONAL MATCH (a)-[:stableIdentifier]->(s1:StableIdentifier) " +
                "WITH DISTINCT pe, COLLECT(DISTINCT s1.identifier) AS participants " +
                "WHERE s.identifier = $identifier OR $identifier IN participants " +
                "RETURN DISTINCT pe.DB_ID AS dbId, s.identifier AS stId, pe._displayName AS displayName, labels(pe) AS labels";

        Map<String, Object> map = new HashMap<>(2);
        map.put("identifier", identifier);
        map.put("dbId", dbId);
        return neo4jClient.query(query).in(databaseName).bindAll(map).fetchAs(SimpleDatabaseObject .class).mappedBy( (ts, rec) -> SimpleDatabaseObject.build(rec)).all();
    }

}
