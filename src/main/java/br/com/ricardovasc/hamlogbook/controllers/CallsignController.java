package br.com.ricardovasc.hamlogbook.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.services.CallsignService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/callsigns")
@RequiredArgsConstructor
public class CallsignController {
    
    private final CallsignService callsignService;

    @GetMapping
	public ResponseEntity<List<CallsignDTO>> findAll() {
		return ResponseEntity.ok().body(callsignService.findAll());
	}

    @GetMapping("/{code}")
    public ResponseEntity<CallsignDTO> getOne(@PathVariable String code) {
        return ResponseEntity.ok().body(callsignService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<CallsignDTO> save(@RequestBody CallsignDTO callsignDTO) {
        final CallsignDTO insertedCallsign = callsignService.save(callsignDTO);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}")
                .buildAndExpand(insertedCallsign).toUri();

        return ResponseEntity.created(uri).body(insertedCallsign);
    }

}
