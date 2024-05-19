package com.hyand.spring.basic.controller;

import com.hyand.spring.basic.model.RequestStatus;
import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HandleLoanRequestController {

    private final LoanRequestService loanRequestService;

    @GetMapping("/handleLoanRequest/{id}")
    public String showCreateForm(Model model, @PathVariable(name = "id") UUID id) {
        loanRequestService
                .findById(id)
                .ifPresent(loanRequest -> model.addAttribute("handleLoanRequestDto", this.map(loanRequest)));
        return "handleLoanRequest";
    }

    @PostMapping(value = "/handleLoanRequest", params = "approve", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String approveLoanRequest(@ModelAttribute HandleLoanRequestDto dto) {
        loanRequestService.updateStatus(dto.id(), RequestStatus.APPROVED);
        return "redirect:/loanRequests";
    }

    @PostMapping(value = "/handleLoanRequest", params = "reject", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String rejectLoanRequest(@ModelAttribute HandleLoanRequestDto dto) {
        loanRequestService.updateStatus(dto.id(), RequestStatus.REJECTED);
        return "redirect:/loanRequests";
    }

    @PostMapping(value = "/handleLoanRequest", params = "cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cancel() {
        return "redirect:/loanRequests";
    }

    private HandleLoanRequestDto map(LoanRequest loanRequest) {
        return HandleLoanRequestDto.builder()
                .id(loanRequest.getId())
                .requestDate(loanRequest.getRequestDate())
                .debitor(loanRequest.getDebitor())
                .amount(loanRequest.getAmount())
                .description(loanRequest.getDescription())
                .build();
    }



}
