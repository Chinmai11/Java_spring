package com.kalthko.employee_crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CountryResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("region")
    private String region;
    @JsonProperty("currencies")
    private Map<String,Object> currencies;
    @JsonProperty("languages")
    private Map<String,String> languages;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, Object> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Object> currencies) {
        this.currencies = currencies;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }
}
