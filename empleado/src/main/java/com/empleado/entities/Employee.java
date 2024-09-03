package com.empleado.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Valid
@Builder
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    @NotNull
    private String name;

    @Column(length = 60)
    @NotNull
    private String address;

    @Column(length = 60)
    @NotNull
    private String birth;

    @Column(unique = true, nullable = false, length = 12)
    private String idCard;

    @Column(length = 2)
    @NotNull
    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 65, message = "age must be less than 65")
    private int age;

    @Email(message = "Email must be valid")
    @Column(unique = true, nullable = false, length = 60)
    private String email;

    @NotNull
    private String job;

    @NotNull
    private double salary;

    @NotNull
    private String contractDuration;

    @Column(unique = true, nullable = false, length = 12)
    private String accountNumber;

    @NotNull
    private String bank;

    private Long overtimeId;
}
