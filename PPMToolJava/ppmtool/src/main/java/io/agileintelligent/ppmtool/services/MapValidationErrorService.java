package io.agileintelligent.ppmtool.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapValidationErrorService {
    public ResponseEntity<?> validProject(BindingResult bindingResult)
    {
        if(bindingResult.hasFieldErrors())
        {
            Map<String,String> mapErrors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(x->x.getField(), x->x.getDefaultMessage()));
            return new ResponseEntity<>(mapErrors, HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
