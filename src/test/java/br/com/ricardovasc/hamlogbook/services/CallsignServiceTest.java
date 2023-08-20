package br.com.ricardovasc.hamlogbook.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import br.com.ricardovasc.hamlogbook.models.Callsign;
import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.models.mappers.CallsignMapper;
import br.com.ricardovasc.hamlogbook.repositories.CallsignRepository;
import br.com.ricardovasc.hamlogbook.test.builders.TestCallsignBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public class CallsignServiceTest {

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class findAllTest {

        @InjectMocks
        private CallsignService callsignService;

        @Mock
        private CallsignRepository callsignRepository;

        @Spy
        private CallsignMapper callsignMapper;

        AutoCloseable closeable;

        @BeforeEach
        void setUp() {
            callsignMapper = Mappers.getMapper(CallsignMapper.class);
            closeable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            closeable.close();
        }

        @Test
        void shouldFindAll() {
            final List<Callsign> callsignList = TestCallsignBuilder
                    .listBuilder()
                    .createObjects()
                    .buildList();
            final List<Integer> expectedIdList = callsignList
                    .stream()
                    .map(Callsign::getId)
                    .toList();

            when(callsignRepository.findAll()).thenReturn(callsignList);

            final List<CallsignDTO> result = callsignService.findAll();

            verify(callsignMapper, times(callsignList.size())).callsignToCallsignDTO(any(Callsign.class));
            verify(callsignRepository, times(1)).findAll();
            verifyNoMoreInteractions(callsignMapper, callsignRepository);

            assertThat(result)
                    .isNotEmpty()
                    .extracting(CallsignDTO::getId)
                    .containsAll(expectedIdList);
        }

        @Test
        void shouldReturnAnEmptyListWhenNoCallsignFound() {
            when(callsignRepository.findAll()).thenReturn(Collections.emptyList());

            final List<CallsignDTO> result = callsignService.findAll();

            assertThat(result).isEmpty();
        }
    }
}
