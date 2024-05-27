package com.hyand.spring.basic.persistence;

import com.hyand.spring.basic.model.LoanRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRequestRepository extends CrudRepository<LoanRequest, UUID>, PagingAndSortingRepository<LoanRequest, UUID> {

    List<LoanRequest> findByAmountGreaterThanOrderByRequestDateDesc(long amount);

}
