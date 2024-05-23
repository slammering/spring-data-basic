package com.hyand.spring.basic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {

    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "uuid", updatable = false)
    private UUID id;

    @Version
    private int version;

    @NotNull
    private LocalDate requestDate;

    @NotEmpty
    private String debitor;

    @Min(1)
    private long amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private RequestStatus status = RequestStatus.REQUESTED;

    private String description;

}
