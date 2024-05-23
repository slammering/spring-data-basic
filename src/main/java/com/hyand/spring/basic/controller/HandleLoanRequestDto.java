package com.hyand.spring.basic.controller;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;


@Builder
public record HandleLoanRequestDto(UUID id,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate requestDate, String debitor,
                                   Long amount, String description) {

}
