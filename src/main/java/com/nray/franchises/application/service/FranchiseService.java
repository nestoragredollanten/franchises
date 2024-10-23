package com.nray.franchises.application.service;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public interface FranchiseService {
    Mono<Franchise> addFranchise(Franchise franchise);

    Mono<Branch> addBranch(Branch branch);

    Mono<Product> addProduct(Product product);
}
