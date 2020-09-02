package com.beer.msscbrewery.web.controller;

import com.beer.msscbrewery.web.model.BeerDTO;
import com.beer.msscbrewery.web.service.BeerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;


/**
 * This is a simple Controller test using MockMVC and
 * Mockito since we have used a Bean - BeerService in
 * Controller class
 *
 */
@ExtendWith(MockitoExtension.class)
public class BeerControllerTest {

    @Mock
    private BeerService beerService;

    @InjectMocks
    BeerController beerController;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(beerController)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getBeerById() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(beerService.getBeerById(uuid)).thenReturn(BeerDTO.builder().build());
        mockMvc.perform(get("/api/v1/beer/"+ uuid.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveBeer() throws Exception {
        BeerDTO beerDto = BeerDTO.builder().build();
        beerDto.setId(UUID.randomUUID());
        beerDto.setBeerName("abc");
        beerDto.setBeerStyle("ghg");
        beerDto.setUpc(8);
        when(beerService.saveBeerDto(beerDto)).thenReturn(beerDto);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    void updateBeerById() throws Exception{
        BeerDTO beerDto = BeerDTO
                            .builder()
                            .id(UUID.randomUUID())
                            .beerStyle("fjdgfhj")
                            .beerName("fdfgds")
                            .upc(9)
                            .build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);
        System.out.println(beerToJson);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isNoContent());
    }


    /**
     * This test works fine only if @ExceptionHandler is within the Controller class
     * If its placed in a separate place using @ControllerAdvice , it does not deduct the error
     * Need to check if mocks are missing
     * @throws Exception
     */
    @Test
    void updateBeerByIdForErrorScenario() throws Exception{
        BeerDTO beerDto = BeerDTO
                .builder()
                .id(null)
                .beerStyle("fjdgfhj")
                .beerName("fdfgds")
                .build();
        String beerToJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerToJson))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteBeer() throws Exception {
        mockMvc.perform(delete("/api/v1/beer/"+UUID.randomUUID().toString()))
                .andExpect(status().isNoContent());
    }

}
