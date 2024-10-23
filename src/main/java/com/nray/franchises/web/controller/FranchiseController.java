package com.nray.franchises.web.controller;

import com.nray.franchises.application.service.FranchiseService;
import com.nray.franchises.domain.model.Franchise;
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

    @PostMapping
    public Mono<ResponseEntity<Franchise>> addFranchise(@RequestBody Franchise franchise) {
        return franchiseService.addFranchise(franchise)
                .map(savedFranchise -> ResponseEntity.status(HttpStatus.CREATED).body(savedFranchise))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
}