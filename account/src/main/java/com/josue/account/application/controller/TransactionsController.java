package com.josue.account.application.controller;

import com.josue.account.application.mapper.TransactionMapper;
import com.josue.account.application.request.TransactionCreateRequest;
import com.josue.account.application.request.TransactionUpdateRequest;
import com.josue.account.application.response.TransactionResponse;
import com.josue.account.domain.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/movimientos")
@AllArgsConstructor
public class TransactionsController {

    private final TransactionService transactionService;

    @GetMapping()
    public Flux<TransactionResponse> getAll() {
        return Mono.fromCallable(transactionService::getAll)
                .publishOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable)
                .map(TransactionMapper::toResponse);
    }

    @PostMapping()
    public Mono<TransactionResponse> create(
            @Valid @RequestBody TransactionCreateRequest request
    ) {
        return Mono.just(request)
                .map(TransactionMapper::toDomain)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> transactionService.create(domain)))
                .map(TransactionMapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<TransactionResponse> edit(
            @Valid @RequestBody TransactionUpdateRequest request,
            @PathVariable String id
    ) {
        return Mono.just(request)
                .map(TransactionMapper::toDomain)
                .map(account -> {
                    account.setId(id);
                    return account;
                })
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> transactionService.edit(domain)))
                .map(TransactionMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> delete(
            @PathVariable String id
    ) {
        return Mono.just(id)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> transactionService.delete(domain)))
                .map(result -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
