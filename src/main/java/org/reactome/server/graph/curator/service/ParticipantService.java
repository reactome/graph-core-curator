package org.reactome.server.graph.curator.service;

import org.reactome.server.graph.curator.domain.model.PhysicalEntity;
import org.reactome.server.graph.curator.domain.model.ReferenceEntity;
import org.reactome.server.graph.curator.domain.result.Participant;
import org.reactome.server.graph.curator.repository.ParticipantRepository;
import org.reactome.server.graph.curator.repository.PhysicalEntityRepository;
import org.reactome.server.graph.curator.repository.ReferenceEntityRepository;
import org.reactome.server.graph.curator.service.util.DatabaseObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@SuppressWarnings("WeakerAccess")
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ReferenceEntityRepository referenceEntityRepository;
    private final PhysicalEntityRepository physicalEntityRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository, ReferenceEntityRepository referenceEntityRepository, PhysicalEntityRepository physicalEntityRepository) {
        this.participantRepository = participantRepository;
        this.referenceEntityRepository = referenceEntityRepository;
        this.physicalEntityRepository = physicalEntityRepository;
    }

    public Collection<ReferenceEntity> getParticipatingReferenceEntities(String identifier) {
        String id = DatabaseObjectUtils.getIdentifier(identifier);
        if (DatabaseObjectUtils.isStId(id)) {
            return referenceEntityRepository.getParticipatingReferenceEntities(id);
        } else if (DatabaseObjectUtils.isDbId(id)){
            return referenceEntityRepository.getParticipatingReferenceEntities(Long.parseLong(id));
        }
        return null;
    }

    public Collection<PhysicalEntity> getParticipatingPhysicalEntities(String identifier) {
        String id = DatabaseObjectUtils.getIdentifier(identifier);
        if (DatabaseObjectUtils.isStId(id)) {
            return physicalEntityRepository.getParticipatingPhysicalEntities(id);
        } else if (DatabaseObjectUtils.isDbId(id)){
            return physicalEntityRepository.getParticipatingPhysicalEntities(Long.parseLong(id));
        }
        return null;
    }

    public Collection<Participant> getParticipants(String identifier) {
        String id = DatabaseObjectUtils.getIdentifier(identifier);
        if (DatabaseObjectUtils.isStId(id)) {
            return participantRepository.getParticipants(id);
        } else if (DatabaseObjectUtils.isDbId(id)){
            return participantRepository.getParticipants(Long.parseLong(id));
        }
        return null;
    }

}
