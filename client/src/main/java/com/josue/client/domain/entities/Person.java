package com.josue.client.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person {

    // UNIQUE
    private String documentNumber;
    private String name;
    private PersonGender gender;
    private OffsetDateTime bornDate;
    private String address;
    private String phoneNumber;

}
