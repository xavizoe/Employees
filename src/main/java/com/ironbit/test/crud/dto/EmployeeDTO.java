package com.ironbit.test.crud.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@ToString
public class EmployeeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "secondLastName")
    private String secondLastName;

    @Column(name = "age")
    private String age;

    @Column(name = "sex")
    private String sex;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "job")
    private String job;

    public EmployeeDTO() {

    }
}
