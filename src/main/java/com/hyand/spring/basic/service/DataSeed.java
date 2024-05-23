package com.hyand.spring.basic.service;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.model.RequestStatus;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@Transactional
@RequiredArgsConstructor
public class DataSeed {

    public static final int NUMBER_OF_REQUESTS = 25;
    private final LoanRequestService loanRequestRepository;

    @PostConstruct
    void seedDb() {
        IntStream.range(0, NUMBER_OF_REQUESTS)
                .mapToObj(this::buildLoanRequest)
                .forEach(loanRequestRepository::save);
    }

    private LoanRequest buildLoanRequest(int i) {
        return LoanRequest.builder()
                .id(UUID.randomUUID())
                .requestDate(LocalDate.now().minusDays(NUMBER_OF_REQUESTS - i))
                .debitor("Debitor " + i)
                .amount(Double.valueOf(Math.random() * 50_000).longValue())
                .description("Description" + i)
                .status(RequestStatus.REQUESTED)
                .build();
    }
}
