package com.nray.franchises.infrastructure.repository;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.repository.FranchiseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Repository
public class FranchiseRepositoryImpl implements FranchiseRepository {

    public static final String FRANCHISE = "FRANCHISE#";
    private final DynamoDbAsyncClient dynamoDbAsyncClient;

    public FranchiseRepositoryImpl(DynamoDbAsyncClient dynamoDbAsyncClient) {
        this.dynamoDbAsyncClient = dynamoDbAsyncClient;
    }

    @Override
    public Mono<Franchise> addFranchise(Franchise franchise) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("PK", AttributeValue.builder().s(FRANCHISE + franchise.franchiseId()).build());
        itemValues.put("SK", AttributeValue.builder().s(FRANCHISE + franchise.franchiseId()).build());
        itemValues.put("EntityType", AttributeValue.builder().s("Franchise").build());
        itemValues.put("name", AttributeValue.builder().s(franchise.name()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("FranchiseTable")
                .item(itemValues)
                .build();

        CompletableFuture<PutItemResponse> future = dynamoDbAsyncClient.putItem(request);

        return Mono.fromFuture(future)
                .doOnError(error ->
                        log.error("Error add franchise: {}", error.getMessage())
                )
                .thenReturn(franchise);
    }

    @Override
    public Mono<Branch> addBranch(Branch branch) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("PK", AttributeValue.builder().s(FRANCHISE + branch.franchiseId()).build());
        itemValues.put("SK", AttributeValue.builder().s("BRANCH#" + branch.branchId()).build());
        itemValues.put("EntityType", AttributeValue.builder().s("Branch").build());
        itemValues.put("name", AttributeValue.builder().s(branch.name()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("FranchiseTable")
                .item(itemValues)
                .build();

        CompletableFuture<PutItemResponse> future = dynamoDbAsyncClient.putItem(request);

        return Mono.fromFuture(future)
                .doOnError(error ->
                        log.error("Error add branch: {}", error.getMessage())
                )
                .thenReturn(branch);
    }
}
