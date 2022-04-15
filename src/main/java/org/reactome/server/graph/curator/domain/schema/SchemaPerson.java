package org.reactome.server.graph.curator.domain.schema;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.reactome.server.graph.curator.domain.model.Affiliation;
import org.reactome.server.graph.curator.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SchemaPerson extends SchemaCreator {

    private final String givenName;
    private final String familyName;
    private final List<SchemaOrganization> affiliation = new ArrayList<>();

    SchemaPerson(Person person) {
        this.givenName = person.getFirstname();
        this.familyName = person.getSurname();
        if(person.getAffiliation()!=null) {
            for (Affiliation a : person.getAffiliation()) {
                this.affiliation.add(new SchemaOrganization(a));
            }
        }
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public List<SchemaOrganization> getAffiliation() {
        return affiliation;
    }

    @JsonGetter(value = "@type")
    public String getType(){
        return "Person";
    }
}
