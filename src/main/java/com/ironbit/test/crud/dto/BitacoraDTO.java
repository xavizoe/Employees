package com.ironbit.test.crud.dto;

import lombok.*;

import javax.persistence.*;

@Table(name = "bitacora")
@Entity
@Getter
@Setter
@ToString
public class BitacoraDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "operation")
    private String operation;

    @Column(name = "data")
    @Lob
    private String data;


}
