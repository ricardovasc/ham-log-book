package br.com.ricardovasc.hamlogbook.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.ricardovasc.hamlogbook.models.dtos.CallsignDTO;
import br.com.ricardovasc.hamlogbook.services.CallsignService;
import br.com.ricardovasc.hamlogbook.test.builders.TestCallsignDTOBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public class CallsignControllerTest {
    
    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class FindAllTest {

        @InjectMocks
        CallsignController callsignController;

        @Mock
        CallsignService callsignService;

        AutoCloseable autoCloseable;

        @BeforeEach
        void setUp() {
            autoCloseable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            autoCloseable.close();
        }

        @Test
        void shouldCallCallsignServiceFindAll() {
            final int listSize = 3;
            final List<CallsignDTO> callsignDTOList = TestCallsignDTOBuilder.listBuilder()
                    .createObjects().withSize(listSize).buildList();

            when(callsignService.findAll()).thenReturn(callsignDTOList);

            callsignController.findAll();

            verify(callsignService, only()).findAll();
        }

        @Test
        void shouldReturnHttpStatusOk() {
            final int listSize = 3;
            final List<CallsignDTO> callsignDTOList = TestCallsignDTOBuilder.listBuilder()
                    .createObjects().withSize(listSize).buildList();

            when(callsignService.findAll()).thenReturn(callsignDTOList);

            final ResponseEntity<List<CallsignDTO>> responseEntity = callsignController.findAll();

            assertThat(responseEntity).extracting(ResponseEntity::getStatusCode)
                    .isEqualTo(HttpStatus.OK);
        }
    }

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class GetOneTest {

        @InjectMocks
        CallsignController callsignController;

        @Mock
        CallsignService callsignService;

        AutoCloseable autoCloseable;

        @BeforeEach
        void setUp() {
            autoCloseable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            autoCloseable.close();
        }

        @Test
        void shouldCallCallsignServiceFindByCode() {
            final String code = "PU7ABC";
            final CallsignDTO callsignDTO = TestCallsignDTOBuilder.builder().createObject().build();

            when(callsignService.findByCode(code)).thenReturn(callsignDTO);

            callsignController.getOne(code);

            verify(callsignService, only()).findByCode(code);
        }

        @Test
        void shouldReturnHttpStatusOk() {
            final String code = "PU7ABC";
            final CallsignDTO callsignDTO = TestCallsignDTOBuilder.builder().createObject().build();

            when(callsignService.findByCode(code)).thenReturn(callsignDTO);

            final ResponseEntity<CallsignDTO> responseEntity = callsignController.getOne(code);

            assertThat(responseEntity).extracting(ResponseEntity::getStatusCode)
                    .isEqualTo(HttpStatus.OK);
        }
    }

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class SaveTest {

        @InjectMocks
        CallsignController callsignController;

        @Mock
        CallsignService callsignService;

        AutoCloseable autoCloseable;

        @BeforeEach
        void setUp() {
            autoCloseable = openMocks(this);
        }

        @AfterEach
        void tearDown() throws Exception {
            autoCloseable.close();
        }

        @Test
        void shouldCallCallsignServiceSave() {
            final CallsignDTO callsignDTO = new CallsignDTO();
            callsignDTO.setCode("PU7ABC");
            callsignDTO.setName("Name of PU7ABC");

            final MockHttpServletRequest request = new MockHttpServletRequest();
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

            when(callsignService.save(callsignDTO)).thenReturn(callsignDTO);

            callsignController.save(callsignDTO);

            verify(callsignService, only()).save(callsignDTO);

            RequestContextHolder.resetRequestAttributes();
        }

        @Test
        void shouldReturnHttpStatusCreated() {
            final CallsignDTO callsignDTO = new CallsignDTO();
            callsignDTO.setCode("PU7ABC");
            callsignDTO.setName("Name of PU7ABC");

            final MockHttpServletRequest request = new MockHttpServletRequest();
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

            when(callsignService.save(callsignDTO)).thenReturn(callsignDTO);

            final ResponseEntity<CallsignDTO> responseEntity = callsignController.save(callsignDTO);

            assertThat(responseEntity).extracting(ResponseEntity::getStatusCode)
                    .isEqualTo(HttpStatus.CREATED);

            RequestContextHolder.resetRequestAttributes();
        }
    }
}
