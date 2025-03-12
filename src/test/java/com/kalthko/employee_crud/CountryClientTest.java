package com.kalthko.employee_crud;

import com.kalthko.employee_crud.client.CountryClient;
import com.kalthko.employee_crud.dto.CountryResponse;
import com.kalthko.employee_crud.exception.CountryNotFoundException;
import com.kalthko.employee_crud.model.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CountryClientTest {


    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CountryClient countryClient;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

        @Test
         void testFetchCountryDetails_Success() {
            CountryResponse mockResponse = new CountryResponse();
            mockResponse.setStatus("Independent");
            mockResponse.setRegion("Asia");
            mockResponse.setCurrencies(Collections.singletonMap("INR", Map.of()));
            mockResponse.setLanguages(Collections.singletonMap("en", "English"));

            CountryResponse[] mockResponseArray = {mockResponse};

            when(restTemplate.getForObject(anyString(), eq(CountryResponse[].class)))
                    .thenReturn(mockResponseArray);
            Country result = countryClient.fetchCountryDetails("India");

            assertNotNull(result);
            assertEquals("Independent", result.getStatus());
            assertEquals("Asia", result.getRegion());
            assertEquals("INR", result.getCurrencies());
            assertEquals("English", result.getLanguages());
        }
            @Test
            void testFetchCountryDetails_CountryNotFound() {
                when(restTemplate.getForObject(anyString(), eq(CountryResponse[].class)))
                        .thenThrow(HttpClientErrorException.NotFound.class);


                assertThrows(CountryNotFoundException.class, () -> countryClient.fetchCountryDetails("Atlantis"));
            }

                @Test
                void testFetchCountryDetails_EmptyResponse() {
                    when(restTemplate.getForObject(anyString(), eq(CountryResponse[].class)))
                            .thenReturn(new CountryResponse[0]);

                    Exception exception = assertThrows(CountryNotFoundException.class, () -> countryClient.fetchCountryDetails("Unknown"));
                    assertTrue(exception.getMessage().contains("Country not found"));
                }

                    @Test
                    void testFetchCountryDetails_ExceptionHandling() {
                        when(restTemplate.getForObject(anyString(), eq(CountryResponse[].class)))
                                .thenThrow(new RuntimeException("API failure"));

                        Exception exception = assertThrows(RuntimeException.class, () -> countryClient.fetchCountryDetails("India"));
                        assertTrue(exception.getMessage().contains("error fetching country details"));
                }
            }





