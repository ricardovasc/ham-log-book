package br.com.ricardovasc.hamlogbook.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardovasc.hamlogbook.model.LogSheet;
import br.com.ricardovasc.hamlogbook.model.dto.LogSheetDTO;
import br.com.ricardovasc.hamlogbook.model.mapper.LogSheetMapper;
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
	
	@Transactional
	public LogSheetDTO save(LogSheetDTO logSheetDTO) {
		final LogSheet logSheet = logSheetDTOMapper.logSheetDTOToLogSheet(logSheetDTO);
		logSheetRepository.save(logSheet);
		return logSheetDTOMapper.logSheetToLogSheetDTO(logSheet);
	}
	
}
