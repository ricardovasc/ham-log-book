package br.com.ricardovasc.hamlogbook.models.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LogSheetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	private LocalDateTime dateTime;

	@NotNull
	@Min(value = 120000, message = "Frequency must be a VHS or UHF valid value")
	@Max(value = 500000, message = "Frequency must be a VHS or UHF valid value")
	private Integer frequency;

	@Size(min = 5, max = 200)
	private String note;

	@NotNull
	@NotEmpty
	private Set<CallsignDTO> callsignList;
}
