package org.reactome.server.graph.curator.service;

import org.reactome.server.graph.curator.domain.result.Referrals;
import org.reactome.server.graph.curator.repository.ReferralsLinkageRepository;
import org.reactome.server.graph.curator.service.util.DatabaseObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdvancedLinkageService {

    private final ReferralsLinkageRepository referralsLinkageRepository;

    @Autowired
    public AdvancedLinkageService(ReferralsLinkageRepository referralsLinkageRepository) {
        this.referralsLinkageRepository = referralsLinkageRepository;
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
