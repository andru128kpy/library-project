package ru.javavision.loanservice.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavision.loanservice.DTO.BookDTO;
import ru.javavision.loanservice.DTO.LoanDTO;
import ru.javavision.loanservice.client.BookServiceClient;
import ru.javavision.loanservice.mapper.LoanMapper;
import ru.javavision.loanservice.model.Loan;
import ru.javavision.loanservice.repository.LoanRepository;
import ru.javavision.loanservice.service.LoanService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Primary
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookServiceClient bookServiceClient;
    private final LoanMapper loanMapper;

    private static final int LOAN_PERIOD_DAYS = 14;
    private static final BigDecimal DAILY_FINE_RATE = new BigDecimal("5.00");

    @Override
    @Transactional
    public LoanDTO creatLoan(Long bookId) {

        BookDTO book = bookServiceClient.getBookById(bookId);

        Loan loan = new Loan();
        loan.setBookId(bookId);
        loan.setLoanDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(LOAN_PERIOD_DAYS));
        loan.setStatus("ACTIVE");

        return loanMapper.loanToLoanDTO(loanRepository.save(loan), bookServiceClient);
    }

    @Override
    @Transactional
    public LoanDTO returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);

        loan.setReturnDate(LocalDate.now());
        loan.setStatus("RETURNED");

        if (loan.getDueDate().isBefore(LocalDate.now())) {
            long daysOverdue = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
            BigDecimal fine = DAILY_FINE_RATE.multiply(BigDecimal.valueOf(daysOverdue));
            loan.setAmount(fine);
        }

        Loan updatedLoan = loanRepository.save(loan);
        return loanMapper.loanToLoanDTO(updatedLoan, bookServiceClient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> getActiveLoan() {
        return loanRepository.findByStatus("ACTIVE").stream()
                .map(loan -> loanMapper.loanToLoanDTO(loan, bookServiceClient))
                .collect(Collectors.toList());

    }

    @Override
    public BigDecimal calculateFine(Loan loan) {
        return null;
    }
}
