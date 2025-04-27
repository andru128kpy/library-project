package ru.javavision.loanservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javavision.loanservice.DTO.LoanDTO;
import ru.javavision.loanservice.service.LoanService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@AllArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/{bookId}")
    public ResponseEntity<LoanDTO> createLoan(@PathVariable Long bookId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanService.creatLoan(bookId));
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.returnLoan(loanId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<LoanDTO>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveLoan());
    }
}
