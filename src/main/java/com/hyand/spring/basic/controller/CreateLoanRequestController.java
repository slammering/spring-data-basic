package com.hyand.spring.basic.controller;

import com.hyand.spring.basic.model.RequestStatus;
import com.hyand.spring.basic.model.LoanRequest;
import com.hyand.spring.basic.service.LoanRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CreateLoanRequestController {

    private final LoanRequestService loanRequestService;

    @GetMapping("/createLoanRequest")
    public String showCreateForm(Model model) {
        CreateLoanRequestDto createLoanRequestDto = CreateLoanRequestDto
                .builder()
                .id(UUID.randomUUID())
                .requestDate(LocalDate.now())
                .build();
        model.addAttribute("createLoanDto", createLoanRequestDto);
        return "createLoanRequest";
    }

    @PostMapping(value = "/createLoanRequest", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createLoanRequest(@ModelAttribute CreateLoanRequestDto createLoanDto) {
        createLoanRequestEntity(createLoanDto);
        return "redirect:/loanRequests";
    }

    private void createLoanRequestEntity(CreateLoanRequestDto createLoanDto) {
        var loanRequest = LoanRequest.builder()
                .id(createLoanDto.id())
                .requestDate(createLoanDto.requestDate())
                .status(RequestStatus.REQUESTED)
                .debitor(createLoanDto.debitor())
                .amount(createLoanDto.amount())
                .description(createLoanDto.description())
                .build();
        try {
            loanRequestService.save(loanRequest);
        } catch (Exception e) {
            log.error("Creation failed for loan request {}", loanRequest, e);
        }
    }

}
