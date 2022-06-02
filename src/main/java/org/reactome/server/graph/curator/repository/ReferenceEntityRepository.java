package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.ReferenceEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReferenceEntityRepository extends Neo4jRepository<ReferenceEntity, Long> {

    @Query(" MATCH (rd:ReferenceDatabase)<--(n{identifier:$identifier})<-[:referenceEntity|referenceSequence|crossReference|referenceGene*]-(pe:PhysicalEntity) " +
            "WITH DISTINCT pe " +
            "MATCH (pe)-[:referenceEntity]->(n:ReferenceEntity)" +
            "RETURN DISTINCT n")
    Collection<ReferenceEntity> getReferenceEntitiesFor(@Param("identifier") String identifier);

    @Query(" MATCH (n:DatabaseObject{DB_ID:$dbId})-[:hasEvent|input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator|hasComponent|hasMember|hasCandidate|repeatedUnit|referenceEntity*]->(m:ReferenceEntity) " +
            "RETURN DISTINCT m")
    Collection<ReferenceEntity> getParticipatingReferenceEntities(@Param("DB_ID") Long dbId);

    @Query(" MATCH (n:DatabaseObject)-[:hasEvent|input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator|hasComponent|hasMember|hasCandidate|repeatedUnit|referenceEntity*]->(m:ReferenceEntity) " +
            "WITH n,m MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
            "RETURN DISTINCT m")
    Collection<ReferenceEntity> getParticipatingReferenceEntities(@Param("stId") String stId);

}
