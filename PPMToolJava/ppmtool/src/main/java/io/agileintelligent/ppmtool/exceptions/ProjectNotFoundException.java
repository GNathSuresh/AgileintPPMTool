package io.agileintelligent.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException{

    private String message;

    public ProjectNotFoundException(String message) {
        super(message);
    }
}
