package com.hyand.spring.basic.service;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.model.RequestStatus;
import com.hyand.spring.basic.persistence.LoanRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;

    public Page<LoanRequest> findAll(int pageNumber, int pageSize) {
        return loanRequestRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }

    public void updateStatus(UUID id, RequestStatus requestStatus) {
        try {
            loanRequestRepository.findById(id).ifPresent(loanRequestEntity -> {
                loanRequestEntity.setStatus(requestStatus);
                loanRequestRepository.save(loanRequestEntity);
            });
        } catch (Exception e) {
            log.error("Update entity for ID {} can not be executed", id, e);
        }
    }

    public void save(LoanRequest loanRequest) {
        loanRequestRepository.save(loanRequest);
    }

    public Optional<LoanRequest> findById(UUID id) {
        return loanRequestRepository.findById(id);
    }
}
