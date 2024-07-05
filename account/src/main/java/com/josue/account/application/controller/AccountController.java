package com.josue.account.application.controller;

import com.josue.account.application.mapper.AccountMapper;
import com.josue.account.application.request.AccountCreateRequest;
import com.josue.account.application.request.AccountUpdateRequest;
import com.josue.account.application.response.AccountResponse;
import com.josue.account.domain.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/cuentas")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping()
    public Flux<AccountResponse> getAll() {
        return Mono.fromCallable(accountService::getAll)
                .publishOn(Schedulers.boundedElastic())
                .flatMapMany(Flux::fromIterable)
                .map(AccountMapper::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<AccountResponse> getById(
            @PathVariable String id
    ) {
        return Mono.fromCallable(() -> accountService.getById(id))
                .publishOn(Schedulers.boundedElastic())
                .map(AccountMapper::toResponse);
    }

    @PostMapping()
    public Mono<AccountResponse> create(@Valid @RequestBody AccountCreateRequest request
    ) {
        return Mono.just(request)
                .map(AccountMapper::toDomain)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> accountService.create(domain)))
                .map(AccountMapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<AccountResponse> edit(
            @Valid @RequestBody AccountUpdateRequest request,
            @PathVariable String id
    ) {
        return Mono.just(request)
                .map(AccountMapper::toDomain)
                .map(account -> {
                    account.setId(id);
                    return account;
                })
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> accountService.edit(domain)))
                .map(AccountMapper::toResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<?>> delete(@PathVariable String id
    ) {
        return Mono.just(id)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(domain -> Mono.fromCallable(() -> accountService.delete(domain)))
                .map(result -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

}
