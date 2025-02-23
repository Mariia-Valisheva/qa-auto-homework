package utils.model.reqresmodel.getsingleresource;

import lombok.Data;

@Data
public class GetSingleResourceDataModel {
    private String name, color, pantone_value;
    private Integer id, year;
}
