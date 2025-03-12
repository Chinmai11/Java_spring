package com.kalthko.employee_crud.client;

import com.kalthko.employee_crud.dto.CountryResponse;
import com.kalthko.employee_crud.exception.CountryNotFoundException;
import com.kalthko.employee_crud.exception.CountryServiceException;
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
             CountryResponse[] response = restTemplate.getForObject(url, CountryResponse[].class);
//            Object[] response = restTemplate.getForObject(url, Object[].class);

            if (response == null || response.length == 0)
            {
                System.out.println("country not found " +countryName);
               throw new CountryNotFoundException("Country not found " +countryName);
            }
//                Map<String, Object> countryData = (Map<String, Object>) response[0];

            CountryResponse countryData = response[0];

                Country country = new Country();


                if(countryData.getStatus() != null) {
                    country.setStatus(countryData.getStatus());
                }else{
                    country.setStatus("Unknown status");
                }
                if(countryData.getRegion() != null) {
                    country.setRegion(countryData.getRegion());
                } else{
                    country.setRegion("Unknown region");
                }
//                country.setCurrencies(String.join(",",countryData.getCurrencies().keySet());

//                country.setStatus((String) countryData.get("status"));
//                country.setRegion((String) countryData.get("region"));

                // extract currencies
//                Map<String, Object> currencies = (Map<String, Object>) countryData.get("currencies");
//                if (currencies != null) {
//                    country.setCurrencies(currencies.keySet().toString());
//
//                }

                // extract language

//                Map<String, String> languages = (Map<String, String>) countryData.get("languages");
//                if (languages != null) {
//                    country.setLanguages(String.join(",", languages.values()));
//                }
            if(countryData.getCurrencies() != null && !countryData.getCurrencies().isEmpty()){
                country.setCurrencies(String.join(",",countryData.getCurrencies().keySet()));
            }else {
                country.setCurrencies("no currency data available");
            }

            if (countryData.getLanguages() != null && !countryData.getLanguages().isEmpty()){
                country.setLanguages(String.join(",",countryData.getLanguages().values()));
            }else{
                country.setLanguages("no language data available");
            }
                return country;


        } catch (HttpClientErrorException.NotFound e) {
            throw new CountryNotFoundException("Entered country not found " + countryName);
        }catch (CountryNotFoundException e) {
            throw e;

        }catch (Exception e) {
            throw new CountryServiceException("error fetching country details " , e);
        }
//          return null;
    }

}
