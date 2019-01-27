package com.example.demo.objects;

import lombok.Data;

@Data
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
