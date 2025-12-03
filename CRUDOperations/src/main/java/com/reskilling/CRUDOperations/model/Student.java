package com.reskilling.CRUDOperations.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Students") // This tells Hibernate to name the table as Students
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // It will automatically create the id when a new student is added
    private long id;
    private String name;
    private String email;
    private String phone;

}
