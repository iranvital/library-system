package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private int id;
    private Book book;
    private Customer customer;
    private LocalDate dateLoan;
    private LocalDate dateReturn;
}
