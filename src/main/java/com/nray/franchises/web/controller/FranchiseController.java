package com.nray.franchises.web.controller;

import com.nray.franchises.application.service.FranchiseService;
import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/franchises")
public class FranchiseController {

    private final FranchiseService franchiseService;

    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }

    @PostMapping("/franchise")
    public Mono<ResponseEntity<Franchise>> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise)
                .map(savedFranchise -> ResponseEntity.status(HttpStatus.CREATED).body(savedFranchise))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PostMapping("/branch")
    public Mono<ResponseEntity<Branch>> addBranch(@RequestBody Branch branch) {
        return franchiseService.addBranch(branch)
                .map(savedBranch -> ResponseEntity.status(HttpStatus.CREATED).body(savedBranch))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PostMapping("/product")
    public Mono<ResponseEntity<Product>> addBranch(@RequestBody Product product) {
        return franchiseService.addProduct(product)
                .map(savedProduct -> ResponseEntity.status(HttpStatus.CREATED).body(savedProduct))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
}
