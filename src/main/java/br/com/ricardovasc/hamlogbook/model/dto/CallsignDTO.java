package br.com.ricardovasc.hamlogbook.model.dto;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CallsignDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private UUID uuid;

	@NotNull
	private String code;

	@NotNull
	private String name;

}
