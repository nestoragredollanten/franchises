package com.nray.franchises.application.service.impl;

import com.nray.franchises.application.service.FranchiseService;
import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.repository.FranchiseRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Mono<Franchise> addFranchise(Franchise franchise) {
        return franchiseRepository.addFranchise(franchise);
    }

    @Override
    public Mono<Branch> addBranch(Branch branch) {
        return franchiseRepository.addBranch(branch);
    }
}
