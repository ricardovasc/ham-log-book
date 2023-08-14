package br.com.ricardovasc.hamlogbook.model.mapper;

import org.mapstruct.Mapper;

import br.com.ricardovasc.hamlogbook.model.Callsign;
import br.com.ricardovasc.hamlogbook.model.dto.CallsignDTO;

@Mapper(componentModel = "spring")
public interface CallsignMapper {
	
	Callsign callsignDTOToCallsign(CallsignDTO callsignDTO);
	
	CallsignDTO callsignToCallsignDTO(Callsign callsign);
}
