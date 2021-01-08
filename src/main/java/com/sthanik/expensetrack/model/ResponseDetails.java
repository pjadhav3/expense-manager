package com.sthanik.expensetrack.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class ResponseDetails {
    int statusCode;
    List<String> errors;
    Object data;

    public ResponseDetails(int statusCode) {
        this.statusCode = statusCode;
    }
}
