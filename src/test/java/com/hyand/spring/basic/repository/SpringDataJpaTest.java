package com.hyand.spring.basic.repository;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.persistence.LoanRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = true)
public class SpringDataJpaTest {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @AfterEach
    public void deleteData() {
        loanRequestRepository.deleteAll();
    }

    @Test
    public void save() {
        LoanRequest loanRequest = LoanRequest.builder()
                .id(UUID.randomUUID())
                .debitor("Sebastian")
                .amount(2)
                .requestDate(LocalDate.now())
                .build();
        loanRequestRepository.save(loanRequest);

        assertThat(loanRequestRepository.findAll()).hasSize(1);
        assertThat(loanRequestRepository.findById(loanRequest.getId())).isPresent();
        assertThat(loanRequestRepository.findById(loanRequest.getId()).get().getDebitor()).isEqualTo("Sebastian");
    }

}
