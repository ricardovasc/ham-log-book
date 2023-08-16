package br.com.ricardovasc.hamlogbook.exceptions.enums;

import org.springframework.http.HttpStatus;

public enum HttpStatusEnum {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, ""),
    NOT_FOUND(HttpStatus.NOT_FOUND, "");
    
    private HttpStatus code;
    private String type;

    HttpStatusEnum(HttpStatus code, String type) {
        this.code = code;
        this.type = type;
    }

    public HttpStatus getCode() {
        return this.code;
    }

    public String getType() {
        return this.type;
    }
}
