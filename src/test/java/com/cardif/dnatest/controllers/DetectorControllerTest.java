package com.cardif.dnatest.controllers;

import com.cardif.dnatest.requests.DnaRequestDTO;
import com.cardif.dnatest.responses.DnaStatsResponse;
import com.cardif.dnatest.services.AlienDetectorService;
import com.cardif.dnatest.services.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes = DetectorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DetectorControllerTest {

    @MockBean
    private AlienDetectorService alienDetectorService;

    @MockBean
    private StatisticsService statisticsService;

    private DetectorController detectorController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDetectAlien() throws Exception {
        String[] dna = {"HTGCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CCCCTH", "TCHCTD"};
        DnaRequestDTO request = new DnaRequestDTO(dna);

        when(alienDetectorService.isAlien(dna)).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(post("/alien")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"dna\":[\"HTGCDH\",\"CHDTDC\",\"TTHTDT\",\"AGHHDD\",\"CCCCTH\",\"TCHCTD\"]}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Alien detected"))
                .andReturn();
    }

    @Test
    public void testDetectHuman() throws Exception {
        String[] dna = {"HTGCDH", "CHDTDC", "TTHTDT", "AGHHDD", "CTGCTH", "TCHCTD"};
        DnaRequestDTO request = new DnaRequestDTO(dna);

        when(alienDetectorService.isAlien(dna)).thenReturn(false);

        mockMvc.perform(post("/alien")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"dna\":[\"HTGCDH\",\"CHDTDC\",\"TTHTDT\",\"AGHHDD\",\"CTGCTH\",\"TCHCTD\"]}"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("Human detected"));
    }

    @Test
    public void testStatistics() throws Exception {
        DnaStatsResponse statsResponse = new DnaStatsResponse(40L, 100L, 0.4);

        when(statisticsService.getStats()).thenReturn(statsResponse);

        mockMvc.perform(get("/stats")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countAlienDna", is(40)))
                .andExpect(jsonPath("$.countHumanDna", is(100)))
                .andExpect(jsonPath("$.ratio", is(0.4)));
    }
}
