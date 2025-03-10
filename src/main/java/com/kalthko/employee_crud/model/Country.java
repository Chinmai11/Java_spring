package com.kalthko.employee_crud.model;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable                        // this allows country object to be stored in Employee object
@Getter
@Setter
public class Country {

    private String status;
    private String currencies;
    private String languages;
    private String region;

}
