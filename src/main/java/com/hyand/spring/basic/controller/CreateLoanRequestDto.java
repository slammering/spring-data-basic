package com.hyand.spring.basic.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreateLoanRequestDto (

    UUID id,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    LocalDate requestDate,

    @NotEmpty
    String debitor,

    @NotNull
    Long amount,

    String description)
{
}
