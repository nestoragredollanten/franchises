package com.nray.franchises.domain.repository;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> addFranchise(Franchise franchise);

    Mono<Branch> addBranch(Branch branch);
}
