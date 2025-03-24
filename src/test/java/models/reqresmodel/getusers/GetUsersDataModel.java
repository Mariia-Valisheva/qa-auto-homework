package models.reqresmodel.getusers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetUsersDataModel {
    private Integer id;
    private String email, avatar;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
}
