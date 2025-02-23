package utils.model.reqresmodel.getusers;

import lombok.Data;

@Data
public class GetUsersDataModel {
    private Integer id;
    private String email, first_name, last_name, avatar;
}
