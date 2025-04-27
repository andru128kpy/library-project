package ru.javavision.loanservice.service;

import ru.javavision.loanservice.DTO.LoanDTO;
import ru.javavision.loanservice.model.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    LoanDTO creatLoan(Long bookId);
    LoanDTO returnLoan(Long loanId);
    List<LoanDTO> getActiveLoan();
    BigDecimal calculateFine(Loan loan);

}
