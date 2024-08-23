package by.mishelby.logistickapplication.domain.CityDTO;

import jakarta.validation.constraints.NotNull;


public class CityCreateDTO {
    @NotNull(message = "City name should not be a null")
    private String cityName;

    @NotNull(message = "Country map  should not be a null")
    private Integer countryMapId;

}
