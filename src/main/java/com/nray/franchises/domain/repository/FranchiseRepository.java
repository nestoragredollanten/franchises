package com.nray.franchises.domain.repository;

import com.nray.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> addFranchise(Franchise franchise);
}
