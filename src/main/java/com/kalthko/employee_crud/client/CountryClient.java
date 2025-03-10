package com.kalthko.employee_crud.client;

import com.kalthko.employee_crud.exception.CountryNotFoundException;
import com.kalthko.employee_crud.model.Country;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Component
public class CountryClient {
    private final RestTemplate restTemplate;


    public CountryClient(RestTemplate restTemplate){
         this.restTemplate = restTemplate;
    }

    public Country fetchCountryDetails (String countryName) {
        try {
            String url = "https://restcountries.com/v3.1/name/"+countryName;
            Object[] response = restTemplate.getForObject(url, Object[].class);

            if (response == null || response.length == 0)
            {
                System.out.println("country not found " +countryName);
               throw new CountryNotFoundException("Country not found " +countryName);
            }
                Map<String, Object> countryData = (Map<String, Object>) response[0];

                Country country = new Country();
                country.setStatus((String) countryData.get("status"));
                country.setRegion((String) countryData.get("region"));

                // extract currencies
                Map<String, Object> currencies = (Map<String, Object>) countryData.get("currencies");
                if (currencies != null) {
                    country.setCurrencies(currencies.keySet().toString());

                }

                // extract language

                Map<String, String> languages = (Map<String, String>) countryData.get("languages");
                if (languages != null) {
                    country.setLanguages(String.join(",", languages.values()));
                }

                return country;


        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Entered country not found " + countryName);
        } catch (Exception e) {
            throw new RuntimeException("error fetching country details " + e.getMessage());
        }
//          return null;
    }

}
