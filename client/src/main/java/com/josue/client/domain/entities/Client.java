package com.josue.client.domain.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Client extends Person {

    // UNIQUE
    private String id;
    private String password;
    private ClientStatus status;

}
