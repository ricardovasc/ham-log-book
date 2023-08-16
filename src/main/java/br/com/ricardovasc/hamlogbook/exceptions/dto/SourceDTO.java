package br.com.ricardovasc.hamlogbook.exceptions.dto;

import java.util.Objects;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceDTO {
    
    private String pointer;

    @JsonIgnore
    public boolean isEmpty() {
        return Stream.of(pointer).allMatch(Objects::isNull);
    }
}
