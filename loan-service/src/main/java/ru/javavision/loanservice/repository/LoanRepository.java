package ru.javavision.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavision.loanservice.model.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatus(String status);
    Optional<Loan> findByBookIdAndStatus(Long bookId, String status);
}
