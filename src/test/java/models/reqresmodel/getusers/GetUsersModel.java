package models.reqresmodel.getusers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import models.reqresmodel.GetSupportModel;

import java.util.List;

@Data
public class GetUsersModel {
    private Integer page, total;

    @JsonProperty("per_page")
    private Integer perPage;
    @JsonProperty("total_pages")
    private Integer totalPage;

    private List<GetUsersDataModel> data;
    private GetSupportModel support;
}
