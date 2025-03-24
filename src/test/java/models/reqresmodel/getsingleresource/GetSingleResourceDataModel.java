package models.reqresmodel.getsingleresource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetSingleResourceDataModel {
    private String name, color;
    private Integer id, year;

    @JsonProperty("pantone_value")
    private String pantoneValue;
}
