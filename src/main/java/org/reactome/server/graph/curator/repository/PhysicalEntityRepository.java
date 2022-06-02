package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.Complex;
import org.reactome.server.graph.curator.domain.model.PhysicalEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Florian Korninger (florian.korninger@ebi.ac.uk)
 */
@Repository
public interface PhysicalEntityRepository extends Neo4jRepository<PhysicalEntity, Long> {

    @Query("MATCH (n:PhysicalEntity{DB_ID:$dbId})-[:referenceEntity]->(m:ReferenceEntity)<-[:referenceEntity]-(k) WHERE NOT n=k RETURN k")
    Collection<PhysicalEntity> getOtherFormsOf(@Param("DB_ID") Long dbId);

    @Query(" MATCH (n:PhysicalEntity)-[:referenceEntity]->(m:ReferenceEntity)<-[:referenceEntity]-(k) " +
            "MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId AND NOT n=k RETURN k")
    Collection<PhysicalEntity> getOtherFormsOf(@Param("stId") String stId);

    @Query(" MATCH (rd:ReferenceDatabase)<-[:referenceDatabase]-(n{identifier:$identifier}) " +
            "WHERE rd.displayName =~ $resource " +
            "WITH DISTINCT n " +
            "MATCH (n)<-[:referenceEntity|referenceSequence|crossReference|referenceGene*]-(pe:PhysicalEntity) " +
            "WITH DISTINCT pe " +
            "MATCH (c:Complex)-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(pe) " +
            "RETURN DISTINCT c")
    Collection<Complex> getComplexesFor(@Param("identifier") String identifier, @Param("resource") String resource);

    @Query("MATCH (:PhysicalEntity{DB_ID:$dbId})-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(pe:PhysicalEntity) RETURN DISTINCT pe")
    Collection<PhysicalEntity> getPhysicalEntitySubunits(@Param("DB_ID") Long dbId);

    @Query(" MATCH (n:PhysicalEntity)-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(pe:PhysicalEntity) " +
            "MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
            "RETURN DISTINCT pe")
    Collection<PhysicalEntity> getPhysicalEntitySubunits(@Param("stId") String stId);

    @Query("MATCH (:PhysicalEntity{DB_ID:$dbId})-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(pe:PhysicalEntity) WHERE NOT (pe:Complex) AND NOT(pe:EntitySet) RETURN DISTINCT pe")
    Collection<PhysicalEntity> getPhysicalEntitySubunitsNoStructures(@Param("DB_ID") Long dbId);

    @Query( "MATCH (n:PhysicalEntity)-[:hasComponent|hasMember|hasCandidate|repeatedUnit*]->(pe:PhysicalEntity) " +
            "MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
            "AND NOT (pe:Complex) AND NOT(pe:EntitySet) RETURN DISTINCT pe")
    Collection<PhysicalEntity> getPhysicalEntitySubunitsNoStructures(@Param("$stId") String stId);

    @Query(" MATCH (n:DatabaseObject{DB_ID:$dbId})-[:hasEvent|input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]->(m:PhysicalEntity) " +
            "RETURN Distinct(m)")
    Collection<PhysicalEntity> getParticipatingPhysicalEntities(@Param("DB_ID") Long dbId);

    @Query(" MATCH (n:DatabaseObject)-[:hasEvent|input|output|catalystActivity|physicalEntity|entityFunctionalStatus|diseaseEntity|regulatedBy|regulator*]->(m:PhysicalEntity) " +
            "MATCH (n)-[:stableIdentifier]->(s:StableIdentifier) WHERE s.identifier = $stId " +
            "RETURN Distinct(m)")
    Collection<PhysicalEntity> getParticipatingPhysicalEntities(@Param("stId") String stId);
}