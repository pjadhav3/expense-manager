package com.sthanik.expensetrack.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
public class HealthcheckResource {

    @GetMapping("/healthcheck")
    public HttpStatus healthcheck() {
        // RequestContextHolder.getRequestAttributes();
        return HttpStatus.OK;
    }
}
