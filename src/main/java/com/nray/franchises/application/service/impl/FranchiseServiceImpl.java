package com.nray.franchises.application.service.impl;

import com.nray.franchises.application.service.FranchiseService;
import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.model.Product;
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

    @Override
    public Mono<Product> addProduct(Product product) {
        return franchiseRepository.addProduct(product);
    }

    @Override
    public Mono<String> deleteProduct(String franchiseId, String branchId, String productId) {
        return franchiseRepository.deleteProduct(franchiseId, branchId, productId);
    }

    @Override
    public Mono<String> updateStockProduct(String franchiseId, String branchId, String productId, int stock) {
        return franchiseRepository.updateStockProduct(franchiseId, branchId, productId, stock);
    }
}
