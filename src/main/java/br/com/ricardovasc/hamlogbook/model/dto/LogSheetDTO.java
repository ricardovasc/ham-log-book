package br.com.ricardovasc.hamlogbook.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LogSheetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	private LocalDateTime dateTime;

	@NotNull
	private Integer frequency;

	private String note;

	@NotNull
	@NotEmpty
	private Set<CallsignDTO> callsignList;
}
