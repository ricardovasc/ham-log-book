package br.com.ricardovasc.hamlogbook.exceptions.utils;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import org.springframework.data.util.ParsingUtils;
import org.springframework.http.ProblemDetail;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

import br.com.ricardovasc.hamlogbook.exceptions.dto.ErrorDTO;
import br.com.ricardovasc.hamlogbook.exceptions.dto.SourceDTO;
import br.com.ricardovasc.hamlogbook.exceptions.enums.HttpStatusEnum;
import jakarta.validation.ConstraintViolation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtils {
    
    public ErrorDTO buildErrorDTO(ProblemDetail problemDetail) {
        final ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.addError(problemDetail);
        return errorDTO;
    }

    public ErrorDTO buildErrorDTO(List<ProblemDetail> problemDetailList) {
        final ErrorDTO errorDTO = new ErrorDTO();

        for (ProblemDetail problemDetail : problemDetailList) {
            errorDTO.addError(problemDetail);
        }

        return errorDTO;
    }

    public ProblemDetail buildProblemDetail(HttpStatusEnum httpStatusEnum, String message) {
        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatusEnum.getCode(), message);
        problemDetail.setType(URI.create(httpStatusEnum.getType()));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    public ErrorDTO buildErrorDTO(HttpStatusEnum httpStatusEnum, String message) {
        ProblemDetail problemDetail = buildProblemDetail(httpStatusEnum, message);
        return buildErrorDTO(problemDetail);
    }

    public String getLastPropertiyFromConstraintViolation(ConstraintViolation<?> constraintViolation) {
        final List<String> propertyPathList = StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                .map(Objects::toString)
                .toList();

        return propertyPathList.get(propertyPathList.size() - 1);
    }

    public ProblemDetail mapConstraintViolationToProblemDetail(ConstraintViolation<?> constraintViolation) {
        final String lastPropertyFromPath = getLastPropertiyFromConstraintViolation(constraintViolation);
        final String humanReadbleProperty = String.join(" ", ParsingUtils.splitCamelCaseToLower(lastPropertyFromPath));
        final String message = StringUtils.capitalize(humanReadbleProperty);
        final ProblemDetail problemDetail = ExceptionUtils.buildProblemDetail(HttpStatusEnum.BAD_REQUEST, message);

        return problemDetail;
    }

    private void addSourcePropertyToProblemDetail(ProblemDetail problemDetail, String propertyName) {
        final SourceDTO sourceDTO = new SourceDTO();
        sourceDTO.setPointer("/data/attributes/" + propertyName);
        problemDetail.setProperty("source", sourceDTO);
    }

    public ProblemDetail mapFieldErrorToProblemDetail(FieldError fieldError) {
        final String propertyName = fieldError.getField();
        final String message = fieldError.getDefaultMessage();
        final ProblemDetail problemDetail = ExceptionUtils.buildProblemDetail(HttpStatusEnum.BAD_REQUEST, message);

        addSourcePropertyToProblemDetail(problemDetail, propertyName);

        return problemDetail;
    }
}
