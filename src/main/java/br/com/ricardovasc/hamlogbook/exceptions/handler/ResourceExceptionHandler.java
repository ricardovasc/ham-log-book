package br.com.ricardovasc.hamlogbook.exceptions.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.ricardovasc.hamlogbook.exceptions.ResourceNotFoundException;
import br.com.ricardovasc.hamlogbook.exceptions.dto.ErrorDTO;
import br.com.ricardovasc.hamlogbook.exceptions.enums.HttpStatusEnum;
import br.com.ricardovasc.hamlogbook.exceptions.utils.ExceptionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDTO resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ExceptionUtils.buildErrorDTO(HttpStatusEnum.NOT_FOUND, "Resource not found");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO constraintViolationException(ConstraintViolationException e) {
        final List<ProblemDetail> problemDetailList = e.getConstraintViolations()
                .stream()
                .map(ExceptionUtils::mapConstraintViolationToProblemDetail)
                .collect(Collectors.toList());

        return ExceptionUtils.buildErrorDTO(problemDetailList);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDTO methodArgumentNotValidException(MethodArgumentNotValidException e) {
        final List<ProblemDetail> problemDetailList = e.getBindingResult().getFieldErrors()
                .stream()
                .map(ExceptionUtils::mapFieldErrorToProblemDetail)
                .collect(Collectors.toList());

        return ExceptionUtils.buildErrorDTO(problemDetailList);
    }
}
