package com.nray.franchises.application.service;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> addFranchise(Franchise franchise);

    Mono<Branch> addBranch(Branch branch);
}
