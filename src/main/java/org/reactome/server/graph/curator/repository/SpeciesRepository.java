package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.Species;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends Neo4jRepository<Species, Long> {

    @Query("MATCH (n:Species) RETURN n ORDER BY n.displayName")
    List<Species> getAllSpecies();

    @Query("MATCH (n:Species{taxId:$taxId}) RETURN n")
    Species getSpeciesByTaxId(@Param("taxId") String taxId);

    @Query("MATCH (n:Species{DB_ID:$dbId}) RETURN n")
    Species getSpeciesByDbId(@Param("DB_ID") Long dbId);

    @Query("MATCH (n:Species) WHERE $name IN n.name RETURN n")
    Species getSpeciesByName(@Param("name") String name);
}
