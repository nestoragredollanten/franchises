package com.nray.franchises.domain.repository;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.model.Product;
import reactor.core.publisher.Mono;

public interface FranchiseRepository {
    Mono<Franchise> addFranchise(Franchise franchise);

    Mono<Branch> addBranch(Branch branch);

    Mono<Product> addProduct(Product product);

    Mono<String> deleteProduct(String franchiseId, String branchId, String productId);

    Mono<String> updateStockProduct(String franchiseId, String branchId, String productId, int stock);
}
