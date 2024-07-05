package com.josue.client.application.response;

import com.josue.client.domain.entities.PersonGender;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@ToString
@SuperBuilder
public class PersonResponse {

    private String documentNumber;
    private String name;
    private PersonGender gender;
    private OffsetDateTime bornDate;
    private String address;
    private String phoneNumber;

}
