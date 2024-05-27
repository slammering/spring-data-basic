package com.hyand.spring.basic.service;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.persistence.LoanRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LoadRequestServiceMockitoTest {

    @InjectMocks
    LoanRequestService loanRequestService;

    @Mock
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
