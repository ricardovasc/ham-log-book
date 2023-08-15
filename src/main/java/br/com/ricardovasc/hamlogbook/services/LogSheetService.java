package br.com.ricardovasc.hamlogbook.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardovasc.hamlogbook.exceptions.ResourceNotFoundException;
import br.com.ricardovasc.hamlogbook.models.LogSheet;
import br.com.ricardovasc.hamlogbook.models.dtos.LogSheetDTO;
import br.com.ricardovasc.hamlogbook.models.mappers.LogSheetMapper;
import br.com.ricardovasc.hamlogbook.repositories.LogSheetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogSheetService {
	
	private final LogSheetRepository logSheetRepository;
	private final LogSheetMapper logSheetDTOMapper;
	
	@Transactional(readOnly = true)
	public List<LogSheetDTO> findAll () {
		return logSheetRepository.findAll()
				.stream()
				.map(logSheetDTOMapper::logSheetToLogSheetDTO)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public LogSheetDTO findById(Integer id) {
		return logSheetRepository.findById(id)
				.map(logSheetDTOMapper::logSheetToLogSheetDTO)
				.orElseThrow(() -> new ResourceNotFoundException("Log Sheet not found with id: " + id));
	}
	
	@Transactional
	public LogSheetDTO save(LogSheetDTO logSheetDTO) {
		final LogSheet logSheet = logSheetDTOMapper.logSheetDTOToLogSheet(logSheetDTO);
		logSheetRepository.save(logSheet);
		return logSheetDTOMapper.logSheetToLogSheetDTO(logSheet);
	}
	
	@Transactional
	public void delete(Long id) {

	}
}
