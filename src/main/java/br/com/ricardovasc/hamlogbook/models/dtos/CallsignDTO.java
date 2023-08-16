package br.com.ricardovasc.hamlogbook.models.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CallsignDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	@Size(min = 5, max = 6)
	private String code;

	@NotNull
	@Size(min = 3, max = 100)
	private String name;
}
