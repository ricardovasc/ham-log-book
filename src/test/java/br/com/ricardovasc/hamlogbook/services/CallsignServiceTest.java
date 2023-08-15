package br.com.ricardovasc.hamlogbook.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.ricardovasc.hamlogbook.exceptions.ResourceNotFoundException;
import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.models.mappers.CallsignMapper;
import br.com.ricardovasc.hamlogbook.repositories.CallsignRepository;
import br.com.ricardovasc.hamlogbook.services.CallsignService;

@SpringBootTest
public class CallsignServiceTest {

    @Mock
    private CallsignRepository callsignRepository;

    @Mock
    private CallsignMapper callsignMapper;

    @InjectMocks
    private CallsignService callsignService;

    @Test
    public void testFindAll() {
        // Mocking data
        Callsign callsign1 = new Callsign();
        callsign1.setId(1);
        callsign1.setCode("ABC123");
        callsign1.setName("John Doe");

        Callsign callsign2 = new Callsign();
        callsign2.setId(2);
        callsign2.setCode("XYZ456");
        callsign2.setName("Jane Smith");

        when(callsignRepository.findAll()).thenReturn(List.of(callsign1, callsign2));

        // Mocking mapper
        CallsignDTO callsignDTO1 = new CallsignDTO();
        callsignDTO1.setId(1);
        callsignDTO1.setCode("ABC123");
        callsignDTO1.setName("John Doe");

        CallsignDTO callsignDTO2 = new CallsignDTO();
        callsignDTO2.setId(2);
        callsignDTO2.setCode("XYZ456");
        callsignDTO2.setName("Jane Smith");

        when(callsignMapper.callsignToCallsignDTO(callsign1)).thenReturn(callsignDTO1);
        when(callsignMapper.callsignToCallsignDTO(callsign2)).thenReturn(callsignDTO2);

        // Perform the test
        List<CallsignDTO> result = callsignService.findAll();

        // Assertions
        assertEquals(2, result.size());
        assertEquals(callsignDTO1, result.get(0));
        assertEquals(callsignDTO2, result.get(1));
    }

    @Test
    public void testFindByCode() {
        // Mocking data
        Callsign callsign = new Callsign();
        callsign.setId(1);
        callsign.setCode("ABC123");
        callsign.setName("John Doe");

        when(callsignRepository.findByCode("ABC123")).thenReturn(Optional.of(callsign));

        // Mocking mapper
        CallsignDTO callsignDTO = new CallsignDTO();
        callsignDTO.setId(1);
        callsignDTO.setCode("ABC123");
        callsignDTO.setName("John Doe");

        when(callsignMapper.callsignToCallsignDTO(callsign)).thenReturn(callsignDTO);

        // Perform the test
        CallsignDTO result = callsignService.findByCode("ABC123");

        // Assertions
        assertEquals(callsignDTO, result);
    }

    @Test
    public void testFindByCodeNotFound() {
        when(callsignRepository.findByCode("NONEXISTENT")).thenReturn(Optional.empty());

        // Perform the test and expect a ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> callsignService.findByCode("NONEXISTENT"));
    }
}
