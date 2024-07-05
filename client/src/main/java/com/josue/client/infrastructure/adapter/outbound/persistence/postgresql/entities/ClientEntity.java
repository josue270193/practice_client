package com.josue.client.infrastructure.adapter.outbound.persistence.postgresql.entities;

import com.josue.client.domain.entities.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Table(name = "CLIENTS", schema = "practice")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientEntity extends PersonEntity implements Serializable {

    @NotEmpty
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClientStatus status;

}
