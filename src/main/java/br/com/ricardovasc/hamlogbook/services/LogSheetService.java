package br.com.ricardovasc.hamlogbook.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardovasc.hamlogbook.model.LogSheet;
import br.com.ricardovasc.hamlogbook.model.dto.LogSheetDTO;
import br.com.ricardovasc.hamlogbook.model.mapper.LogSheetDTOMapper;
import br.com.ricardovasc.hamlogbook.repositories.LogSheetRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogSheetService {
	
	private final LogSheetRepository logSheetRepository;
	private final LogSheetDTOMapper logSheetDTOMapper;
	
	@Transactional(readOnly = true)
	public List<LogSheetDTO> findAll () {
		return logSheetRepository.findAll()
				.stream()
				.map(logSheetDTOMapper::toLogSheetDTO)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public LogSheetDTO insert(LogSheetDTO logSheetDTO) {
		final LogSheet logSheet = logSheetDTOMapper.toLogSheet(logSheetDTO);
		logSheetRepository.save(logSheet);
		return logSheetDTOMapper.toLogSheetDTO(logSheet);
	}
	
}
