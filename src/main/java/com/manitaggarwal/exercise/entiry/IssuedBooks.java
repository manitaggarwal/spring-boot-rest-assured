package com.manitaggarwal.exercise.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ISSUED_BOOKS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssuedBooks {

    @Id
    private String issueId;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")
    private Book book;

    private Date issueDate;

}
