package br.com.ricardovasc.hamlogbook.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ricardovasc.hamlogbook.models.dtos.LogSheetDTO;
import br.com.ricardovasc.hamlogbook.services.LogSheetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/log-sheets")
@RequiredArgsConstructor
public class LogSheetController {

	private final LogSheetService logSheetService;

	@GetMapping
	public ResponseEntity<List<LogSheetDTO>> get() {
		return ResponseEntity.ok().body(logSheetService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<LogSheetDTO> getOne(@PathVariable @NonNull Integer id) {
		return ResponseEntity.ok().body(logSheetService.findById(id));
	}

	@PostMapping
	public ResponseEntity<LogSheetDTO> insert(@RequestBody @NonNull @Valid LogSheetDTO logSheetDTO) {
		final LogSheetDTO inserLogSheetDTO = logSheetService.save(logSheetDTO);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(inserLogSheetDTO.getId()).toUri();

		return ResponseEntity.created(uri).body(inserLogSheetDTO);
	}

	@PutMapping
	public ResponseEntity<LogSheetDTO> update(@RequestBody @NonNull @Valid LogSheetDTO logSheetDTO) {
		final LogSheetDTO updatedLogSheetDTO = logSheetService.save(logSheetDTO);

		return ResponseEntity.ok().body(updatedLogSheetDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable @NonNull Integer id) {
		logSheetService.delete(id);

		return ResponseEntity.noContent().build();
	}
}
