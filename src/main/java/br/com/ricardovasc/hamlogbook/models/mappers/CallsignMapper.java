package br.com.ricardovasc.hamlogbook.models.mappers;

import org.mapstruct.Mapper;

import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;

@Mapper(componentModel = "spring")
public interface CallsignMapper {
	
	Callsign callsignDTOToCallsign(CallsignDTO callsignDTO);
	
	CallsignDTO callsignToCallsignDTO(Callsign callsign);
}
