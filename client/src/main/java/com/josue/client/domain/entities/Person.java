package com.josue.client.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Person {

    private String id;
    // UNIQUE
    private String documentNumber;
    private String name;
    private PersonGender gender;
    private OffsetDateTime bornDate;
    private String address;
    private String phoneNumber;

}
