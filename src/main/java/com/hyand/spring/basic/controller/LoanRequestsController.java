package com.hyand.spring.basic.controller;

import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoanRequestsController {

    private final LoanRequestService loanRequestService;

    @GetMapping(path = {"/", "/loanRequests"})
    private String showAll(
            Model model,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int pageNumber) {
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);

        Page<LoanRequest> loanRequestPage = loanRequestService.findAll(pageNumber, pageSize);
        model.addAttribute("loanRequests", loanRequestPage.map(this::map).toList());
        model.addAttribute("totalPages", loanRequestPage.getTotalPages());
        return "loanRequests";
    }


    private LoanRequestDto map(LoanRequest loanRequest) {
        return LoanRequestDto.builder()
                .id(loanRequest.getId())
                .requestDate(loanRequest.getRequestDate())
                .debitor(loanRequest.getDebitor())
                .amount(loanRequest.getAmount())
                .description(loanRequest.getDescription())
                .status(loanRequest.getStatus())
                .build();
    }




}
