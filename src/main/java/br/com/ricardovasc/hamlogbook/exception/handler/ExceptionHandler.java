package br.com.ricardovasc.hamlogbook.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import br.com.ricardovasc.hamlogbook.exception.ResourceNotFoundException;
import br.com.ricardovasc.hamlogbook.exception.dto.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandler {

    private ResponseEntity<ErrorDTO> buildErrorDTO(String error, String message, HttpServletRequest request, HttpStatus httpStatus) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .timestamp(Instant.now())
                .error(error)
                .message(message)
                .status(httpStatus.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(httpStatus).body(errorDTO);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDTO> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return buildErrorDTO("Resource not found", e.getMessage(), request, HttpStatus.NOT_FOUND);
    }
}
