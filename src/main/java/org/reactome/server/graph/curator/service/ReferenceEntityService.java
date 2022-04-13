package org.reactome.server.graph.curator.service;

import org.reactome.server.graph.curator.domain.model.ReferenceEntity;
import org.reactome.server.graph.curator.repository.ReferenceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@SuppressWarnings("unused")
@Service
public class ReferenceEntityService {

    private final ReferenceEntityRepository referenceEntityRepository;

    @Autowired
    public ReferenceEntityService(ReferenceEntityRepository referenceEntityRepository) {
        this.referenceEntityRepository = referenceEntityRepository;
    }

    public Collection<ReferenceEntity> getReferenceEntitiesFor(String identifier){
        return referenceEntityRepository.getReferenceEntitiesFor(identifier);
    }

}
