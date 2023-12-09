package br.com.ricardovasc.hamlogbook.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;

@Mapper(componentModel = "spring")
public interface CallsignMapper {
	
	@Mapping(target = "logSheetList", ignore = true)
	Callsign callsignDTOToCallsign(CallsignDTO callsignDTO);
	
	CallsignDTO callsignToCallsignDTO(Callsign callsign);
}
