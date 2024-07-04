package com.patika.readercustomerservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "addressses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long adressNo;
    @Column(name = "title")
    private String title;
    @Column(name = "province")
    private String province;
    @Column(name = "description")
    private String description;

}
