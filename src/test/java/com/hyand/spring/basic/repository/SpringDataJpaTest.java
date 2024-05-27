package com.hyand.spring.basic.repository;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.persistence.LoanRequestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    public void findByAmountGreaterThanOrderByRequestDateDesc() {
        UUID id1002 = UUID.randomUUID();
        loanRequestRepository.save(LoanRequest.builder()
                .id(id1002)
                .debitor("Sebastian")
                .amount(1002)
                .requestDate(LocalDate.now().minusDays(2))
                .build());

        UUID id1005 = UUID.randomUUID();
        loanRequestRepository.save(LoanRequest.builder()
                .id(id1005)
                .debitor("Sebastiann 1005")
                .amount(1005)
                .requestDate(LocalDate.now())
                .build());

        UUID id999 = UUID.randomUUID();
        loanRequestRepository.save(LoanRequest.builder()
                .id(id999)
                .debitor("Sebastiann 999")
                .amount(999)
                .requestDate(LocalDate.now())
                .build());

        List<LoanRequest> loanRequests = loanRequestRepository.findByAmountGreaterThanOrderByRequestDateDesc(1000);

        assertThat(loanRequests).hasSize(2);
        assertThat(loanRequests).extracting(LoanRequest::getId).containsExactly(id1005, id1002);

    }

}
