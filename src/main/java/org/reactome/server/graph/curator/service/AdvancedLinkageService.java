package org.reactome.server.graph.curator.service;

import org.reactome.server.graph.curator.domain.result.ComponentOf;
import org.reactome.server.graph.curator.domain.result.Referrals;
import org.reactome.server.graph.curator.repository.AdvancedLinkageRepository;
import org.reactome.server.graph.curator.repository.ComponentOfLinkageRepository;
import org.reactome.server.graph.curator.repository.ReferralsLinkageRepository;
import org.reactome.server.graph.curator.service.util.DatabaseObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdvancedLinkageService {

    private AdvancedLinkageRepository advancedLinkageRepository;

    private final ComponentOfLinkageRepository componentOfLinkageRepository;
    private final ReferralsLinkageRepository referralsLinkageRepository;

    @Autowired
    public AdvancedLinkageService(ComponentOfLinkageRepository componentOfLinkageRepository, ReferralsLinkageRepository referralsLinkageRepository) {
        this.componentOfLinkageRepository = componentOfLinkageRepository;
        this.referralsLinkageRepository = referralsLinkageRepository;
    }

    public Collection<ComponentOf> getComponentsOf(Object identifier) {
        String id = DatabaseObjectUtils.getIdentifier(identifier);
        if (DatabaseObjectUtils.isStId(id)) {
            return componentOfLinkageRepository.getComponentsOf(id);
        } else if (DatabaseObjectUtils.isDbId(id)) {
            return componentOfLinkageRepository.getComponentsOf(Long.parseLong(id));
        }
        return null;
    }

    public Collection<Referrals> getReferralsTo(Object identifier){
        String id = DatabaseObjectUtils.getIdentifier(identifier);
        if (DatabaseObjectUtils.isStId(id)) {
            return referralsLinkageRepository.getReferralsTo(id);
        } else if (DatabaseObjectUtils.isDbId(id)) {
            return referralsLinkageRepository.getReferralsTo(Long.parseLong(id));
        }
        return null;
    }
}
