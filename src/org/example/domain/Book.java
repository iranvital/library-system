package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    private String title;
    private Author author;
    private int code;
    private boolean available;
    private LocalDate dateRegistration;
    private LocalDate dateUpdate;
    private Genre genre;

    public boolean checkBookAvailabity(){
        return isAvailable();
    }
}
