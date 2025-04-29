package ru.javavision.loanservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.javavision.loanservice.DTO.LoanDTO;
import ru.javavision.loanservice.client.BookServiceClient;
import ru.javavision.loanservice.model.Loan;

@Mapper(componentModel = "spring") // Убрать uses
public interface LoanMapper {
    @Mapping(target = "book", expression = "java(bookServiceClient.getBookById(loan.getBookId()))")
    LoanDTO loanToLoanDTO(Loan loan, BookServiceClient bookServiceClient); // Добавить параметр

    @Mapping(target = "id", ignore = true) // Игнорировать при обратном маппинге
    Loan toEntity(LoanDTO loanDTO);
}