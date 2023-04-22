package com.musala.drone.drone.infrastructure.config;

import lombok.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse implements ErrorResponse {
    private String Message;
    private Integer Status;
    @Override
    public HttpStatusCode getStatusCode() {
        return null;
    }

    @Override
    public ProblemDetail getBody() {
        return null;
    }
}
