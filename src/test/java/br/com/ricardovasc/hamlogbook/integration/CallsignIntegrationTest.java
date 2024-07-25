package br.com.ricardovasc.hamlogbook.integration;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
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
@SqlGroup({
    @Sql(value = "classpath:init/callsign.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(value = "classpath:empty/reset.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD),
})
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
            
            resultActions
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("PU7AAA"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Name for PU7AAA"));
        }
    }

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class SaveTest {
        
        @Autowired
        MockMvc mockMvc;

        @Test
        @SneakyThrows
        void shouldSaveAndReturnStatusCreated() {
            final String inputJson = """
                    {
                        "id": 4,
                        "code": "PU7ABC",
                        "name": "Tester"
                    }
                    """;

            final ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.post(CALLSIGN_PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(inputJson));
            
            resultActions
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("PU7ABC"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tester"));
        }
    }

    @Nested
    @FieldDefaults(level = AccessLevel.PRIVATE)
    class UpdateTest {
        
        @Autowired
        MockMvc mockMvc;

        @Test
        @SneakyThrows
        void shouldUpdateAndReturnStatusOk() {
            final String inputJson = """
                    {
                        "name": "Tester"
                    }
                    """;

            final ResultActions resultActions = mockMvc.perform(
                    MockMvcRequestBuilders.put(CALLSIGN_PATH + "/PU7AAA")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(inputJson));
            
            resultActions
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("PU7AAA"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tester"));
        }
    }
}
