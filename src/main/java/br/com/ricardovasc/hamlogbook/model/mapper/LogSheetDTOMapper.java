package br.com.ricardovasc.hamlogbook.model.mapper;

import org.mapstruct.Mapper;

import br.com.ricardovasc.hamlogbook.model.LogSheet;
import br.com.ricardovasc.hamlogbook.model.dto.LogSheetDTO;

@Mapper
public interface LogSheetDTOMapper {
	
	LogSheet toLogSheet(LogSheetDTO logSheetDTO);
	
	LogSheetDTO toLogSheetDTO(LogSheet logSheet);

}
