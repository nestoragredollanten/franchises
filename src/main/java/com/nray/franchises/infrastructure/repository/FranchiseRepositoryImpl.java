package com.nray.franchises.infrastructure.repository;

import com.nray.franchises.domain.model.Branch;
import com.nray.franchises.domain.model.Franchise;
import com.nray.franchises.domain.model.Product;
import com.nray.franchises.domain.repository.FranchiseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Repository
public class FranchiseRepositoryImpl implements FranchiseRepository {

    public static final String FRANCHISE = "FRANCHISE#";
    public static final String BRANCH = "BRANCH#";
    public static final String FRANCHISE_TABLE = "FranchiseTable";
    public static final String ENTITY_TYPE = "EntityType";
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String PRODUCT = "#PRODUCT#";
    private final DynamoDbAsyncClient dynamoDbAsyncClient;
    private final Random random = new Random();

    public FranchiseRepositoryImpl(DynamoDbAsyncClient dynamoDbAsyncClient) {
        this.dynamoDbAsyncClient = dynamoDbAsyncClient;
    }

    @Override
    public Mono<Franchise> addFranchise(Franchise franchise) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("PK", AttributeValue.builder().s(FRANCHISE + franchise.franchiseId()).build());
        itemValues.put("SK", AttributeValue.builder().s(FRANCHISE + franchise.franchiseId()).build());
        itemValues.put(ENTITY_TYPE, AttributeValue.builder().s("Franchise").build());
        itemValues.put("name", AttributeValue.builder().s(franchise.name()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(FRANCHISE_TABLE)
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
        itemValues.put("SK", AttributeValue.builder().s(BRANCH + branch.branchId()).build());
        itemValues.put(ENTITY_TYPE, AttributeValue.builder().s("Branch").build());
        itemValues.put("name", AttributeValue.builder().s(branch.name()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(FRANCHISE_TABLE)
                .item(itemValues)
                .build();

        CompletableFuture<PutItemResponse> future = dynamoDbAsyncClient.putItem(request);

        return Mono.fromFuture(future)
                .doOnError(error ->
                        log.error("Error add branch: {}", error.getMessage())
                )
                .thenReturn(branch);
    }

    @Override
    public Mono<Product> addProduct(Product product) {
        String productId = generateRandomId();

        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("PK", AttributeValue.builder().s(FRANCHISE + product.franchiseId()).build());
        itemValues.put("SK", AttributeValue.builder().s(BRANCH + product.branchProductId() + PRODUCT + productId).build());
        itemValues.put(ENTITY_TYPE, AttributeValue.builder().s("Product").build());
        itemValues.put("name", AttributeValue.builder().s(product.productName()).build());
        itemValues.put("stock", AttributeValue.builder().n(Integer.toString(product.stock())).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(FRANCHISE_TABLE)
                .item(itemValues)
                .build();

        CompletableFuture<PutItemResponse> future = dynamoDbAsyncClient.putItem(request);

        return Mono.fromFuture(future)
                .doOnError(error ->
                        log.error("Error add product: {}", error.getMessage())
                )
                .thenReturn(product);
    }

    @Override
    public Mono<String> deleteProduct(String franchiseId, String branchId, String productId) {

        String pk = FRANCHISE + franchiseId;
        String sk = BRANCH + branchId + PRODUCT + productId;

        Map<String, AttributeValue> keyToDelete = new HashMap<>();
        keyToDelete.put("PK", AttributeValue.builder().s(pk).build());
        keyToDelete.put("SK", AttributeValue.builder().s(sk).build());

        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName(FRANCHISE_TABLE)
                .key(keyToDelete)
                .build();

        CompletableFuture<String> future = dynamoDbAsyncClient.deleteItem(deleteRequest)
                .thenApply(deleteItemResponse -> "Product deleted");

        return Mono.fromFuture(future)
                .doOnError(error -> log.error("Error deleting product: {}", error.getMessage()));
    }

    @Override
    public Mono<String> updateStockProduct(String franchiseId, String branchId, String productId, int stock) {
        String pk = FRANCHISE + franchiseId;
        String sk = BRANCH + branchId + PRODUCT + productId;

        Map<String, AttributeValue> keyToUpdate = new HashMap<>();
        keyToUpdate.put("PK", AttributeValue.builder().s(pk).build());
        keyToUpdate.put("SK", AttributeValue.builder().s(sk).build());

        Map<String, String> expressionAttributeNames = new HashMap<>();
        expressionAttributeNames.put("#S", "stock");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":newStock", AttributeValue.builder().n(Integer.toString(stock)).build());

        UpdateItemRequest updateItemRequest = UpdateItemRequest.builder()
                .tableName(FRANCHISE_TABLE)
                .key(keyToUpdate)
                .updateExpression("SET #S = :newStock")
                .expressionAttributeNames(expressionAttributeNames)
                .expressionAttributeValues(expressionAttributeValues)
                .build();

        CompletableFuture<String> future = dynamoDbAsyncClient.updateItem(updateItemRequest)
                .thenApply(deleteItemResponse -> "Stock product updated");

        return Mono.fromFuture(future)
                .doOnError(error -> log.error("Error update stock product: {}", error.getMessage()));
    }

    private String generateRandomId() {
        String characters = ABC;
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(characters.length());
            id.append(characters.charAt(index));
        }

        return id.toString();
    }
}
