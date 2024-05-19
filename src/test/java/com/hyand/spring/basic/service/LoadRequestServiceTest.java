package com.hyand.spring.basic.service;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.persistence.LoanRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LoanRequestService.class)
public class LoadRequestServiceTest {

    @Autowired
    LoanRequestService loanRequestService;

    @MockBean
    LoanRequestRepository loanRequestRepository;

    @Test
    public void findById() {
        UUID id = UUID.randomUUID();
        Mockito.when(loanRequestRepository.findById(id)).thenReturn(Optional.of(LoanRequest.builder().id(id).build()));
        Optional<LoanRequest> loanRequest = loanRequestService.findById(id);
        assertThat(loanRequest.isPresent()).isTrue();
        assertThat(loanRequest.get().getId()).isEqualTo(id);
    }

}
