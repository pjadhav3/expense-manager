package com.sthanik.expensetrack.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDetails {
    private int userId;
    private String firstName;
    private String lastName;
    private int birthYear;
    private String gender;
    private String email;
    private String password;
    private String phone;
    private int zip;
}
