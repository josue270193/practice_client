package com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.entities;

import com.josue.client.domain.entities.PersonGender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "PERSONS", schema = "practice")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class PersonEntity implements Serializable {

    @Id
    @NotEmpty
    @Column(unique = true)
    private String id;

    @NotEmpty
    @Column(name = "document_number", unique = true)
    private String documentNumber;

    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PersonGender gender;

    @NotNull
    @Column(name = "born_date")
    private OffsetDateTime bornDate;

    @NotEmpty
    private String address;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

}
