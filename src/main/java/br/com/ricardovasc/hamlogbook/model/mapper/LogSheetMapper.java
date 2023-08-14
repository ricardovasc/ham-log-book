package br.com.ricardovasc.hamlogbook.model.mapper;

import org.mapstruct.Mapper;

import br.com.ricardovasc.hamlogbook.model.LogSheet;
import br.com.ricardovasc.hamlogbook.model.dto.LogSheetDTO;

@Mapper(componentModel = "spring")
public interface LogSheetMapper {
	
	LogSheet logSheetDTOToLogSheet(LogSheetDTO logSheetDTO);
	
	LogSheetDTO logSheetToLogSheetDTO(LogSheet logSheet);
}
