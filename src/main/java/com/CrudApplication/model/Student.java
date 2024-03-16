package com.CrudApplication.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;
    private String email;
    private String city;
    private String age;
}


