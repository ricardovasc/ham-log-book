package br.com.ricardovasc.hamlogbook.exceptions.dto;

import java.io.Serializable;
import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO implements Serializable {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    
}
