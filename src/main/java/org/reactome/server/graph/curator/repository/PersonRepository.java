package org.reactome.server.graph.curator.repository;

import org.reactome.server.graph.curator.domain.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long>{

    @Query(" MATCH (n:Person) " +
            "WHERE n.surname in $name AND n.firstname in $name " +
            "OPTIONAL MATCH (n)-[r]->(m) WHERE NOT (m:InstanceEdit) " +
            "RETURN n, COLLECT(r), COLLECT(m)")
    Collection<Person> findPersonByName(@Param("name") String[] name);

    @Query(" MATCH (n:Person) " +
            "WHERE n.surname in $name OR n.firstname in $name " +
            "OPTIONAL MATCH (n)-[r]->(m) WHERE NOT (m:InstanceEdit) " +
            "RETURN n, COLLECT(r), COLLECT(m)")
    Collection<Person> queryPersonByName(@Param("name") String[] name);

    @Query(" MATCH (n:Person{DB_ID:$dbId}) " +
            "OPTIONAL MATCH (n)-[r]->(m) WHERE NOT (m:InstanceEdit) " +
            "RETURN n, COLLECT(r), COLLECT(m)")
    Person findPersonByDbId(@Param("DB_ID") Long dbId);

}
