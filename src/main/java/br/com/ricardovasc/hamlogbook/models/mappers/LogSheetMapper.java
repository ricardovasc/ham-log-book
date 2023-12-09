package br.com.ricardovasc.hamlogbook.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ricardovasc.hamlogbook.models.LogSheet;
import br.com.ricardovasc.hamlogbook.models.dtos.LogSheetDTO;

@Mapper(componentModel = "spring")
public interface LogSheetMapper {
	
	@Mapping(target = "callsignList", ignore = true)
	LogSheet logSheetDTOToLogSheet(LogSheetDTO logSheetDTO);
	
	LogSheetDTO logSheetToLogSheetDTO(LogSheet logSheet);
}
