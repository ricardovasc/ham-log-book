package br.com.ricardovasc.hamlogbook.model.mapper;

import org.mapstruct.Mapper;

import br.com.ricardovasc.hamlogbook.model.Callsign;
import br.com.ricardovasc.hamlogbook.model.dto.CallsignDTO;

@Mapper
public interface CallsignDTOMapper {
	
	Callsign toCallsign(CallsignDTO callsignDTO);
	
	CallsignDTO toCallsignDTO(Callsign callsign);

}
