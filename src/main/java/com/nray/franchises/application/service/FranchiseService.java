package com.nray.franchises.application.service;

import com.nray.franchises.domain.model.Franchise;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> addFranchise(Franchise franchise);
}
