package br.com.ricardovasc.hamlogbook.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ricardovasc.hamlogbook.model.dto.LogSheetDTO;
import br.com.ricardovasc.hamlogbook.services.LogSheetService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/log-sheets")
@RequiredArgsConstructor
public class LogSheetController {

	private final LogSheetService logSheetService;

	@GetMapping
	public ResponseEntity<List<LogSheetDTO>> findAll() {
		return ResponseEntity.ok().body(logSheetService.findAll());
	}

	@PostMapping
	public ResponseEntity<LogSheetDTO> insert(@RequestBody LogSheetDTO logSheetDTO) {
		logSheetDTO = logSheetService.insert(logSheetDTO);
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(logSheetDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(logSheetDTO);
	}

}
