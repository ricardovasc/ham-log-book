package br.com.ricardovasc.hamlogbook.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

@Tag("IntegrationTest")
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CallsignIntegrationTest {

    public static final String CALLSIGN_PATH = "/callsigns";

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class FindAllTest {
        
        @Autowired
        MockMvc mockMvc;

        @Test
        @SneakyThrows
        void shouldFindAllAndReturnStatusOk() {
            final ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.get(CALLSIGN_PATH)
                            .contentType(MediaType.APPLICATION_JSON));
            
            resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        }
    }
}
