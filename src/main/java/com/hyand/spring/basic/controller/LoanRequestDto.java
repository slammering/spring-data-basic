package com.hyand.spring.basic.controller;

import com.hyand.spring.basic.model.RequestStatus;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record LoanRequestDto(UUID id,
                             @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate requestDate,
                             String debitor,
                             long amount,
                             RequestStatus status,
                             String description) {

}
