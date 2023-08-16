package br.com.ricardovasc.hamlogbook.exceptions.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ProblemDetail;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<ProblemDetail> problemDetailList = new ArrayList<>();
    
    public void addError(ProblemDetail problemDetail) {
        problemDetailList.add(problemDetail);
    }
}
