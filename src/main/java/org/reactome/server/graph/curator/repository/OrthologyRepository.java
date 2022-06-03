package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.DatabaseObject;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OrthologyRepository extends Neo4jRepository<DatabaseObject, Long> {

    //The relationship do not have direction because that's what is needed in this case
    @Query(" MATCH (:DatabaseObject{DB_ID:$dbId})<-[:inferredTo]-()-[:inferredTo]->(o:DatabaseObject)-[:species]->(:Species{DB_ID:$speciesId}) RETURN DISTINCT o " +
            "UNION " +
            "MATCH (:DatabaseObject{DB_ID:$dbId})-[:inferredTo]-(o:DatabaseObject)-[:species]->(:Species{DB_ID:$speciesId}) RETURN DISTINCT o")
    Collection<DatabaseObject> getOrthology(@Param("dbId") Long dbId, @Param("speciesId") Long speciesId);

    //The relationship do not have direction because that's what is needed in this case
    @Query(" MATCH (n:DatabaseObject)<-[:inferredTo]-()-[:inferredTo]->(o:DatabaseObject)-[:species]->(:Species{DB_ID:$speciesId}) " +
            "WITH n,o MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId RETURN DISTINCT o " +
            "UNION " + "" +
            "MATCH (n:DatabaseObject)-[:inferredTo]-(o:DatabaseObject)-[:species]->(:Species{DB_ID:$speciesId}) " +
            "WITH n,o MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId RETURN DISTINCT o")
    Collection<DatabaseObject> getOrthology(@Param("stId") String stId, @Param("speciesId") Long speciesId);
}
