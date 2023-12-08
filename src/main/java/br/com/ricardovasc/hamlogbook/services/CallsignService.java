package br.com.ricardovasc.hamlogbook.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ricardovasc.hamlogbook.exceptions.ResourceNotFoundException;
import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.models.dtos.UpdateCallsignDTO;
import br.com.ricardovasc.hamlogbook.models.mappers.CallsignMapper;
import br.com.ricardovasc.hamlogbook.repositories.CallsignRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CallsignService {
    
    private final CallsignRepository callsignRepository;
    private final CallsignMapper callsignDTOMapper;

    @Transactional(readOnly = true)
	public List<CallsignDTO> findAll () {
		return callsignRepository.findAll()
				.stream()
				.map(callsignDTOMapper::callsignToCallsignDTO)
				.collect(Collectors.toList());
	}

    @Transactional(readOnly = true)
    public CallsignDTO findByCode(@NonNull String code) {
        return callsignRepository.findByCode(code)
        .map(callsignDTOMapper::callsignToCallsignDTO)
        .orElseThrow(() -> new ResourceNotFoundException("Callsign not found with code: " + code));
    }

    @Transactional
    public CallsignDTO save(@NonNull CallsignDTO callsignDTO) {
        final Callsign callsign = callsignDTOMapper.callsignDTOToCallsign(callsignDTO);
        callsignRepository.save(callsign);
        return callsignDTOMapper.callsignToCallsignDTO(callsign);
    }

    @Transactional
    public CallsignDTO update(@NonNull String code, @NonNull UpdateCallsignDTO updateCallsignDTO) {
        final CallsignDTO callsignDTO = findByCode(code);

        final Callsign callsign = callsignDTOMapper.callsignDTOToCallsign(callsignDTO);
        callsign.setName(updateCallsignDTO.getName());
        callsignRepository.save(callsign);

        return callsignDTOMapper.callsignToCallsignDTO(callsign);
    }

    @Transactional
    public void delete(@NonNull String code) {
        callsignRepository.deleteByCode(code);
    }
}
