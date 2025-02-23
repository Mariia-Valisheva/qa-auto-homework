package utils.model.reqresmodel.getusers;

import lombok.Data;
import utils.model.reqresmodel.GetSupportModel;

import java.util.List;

@Data
public class GetUsersModel {
    private Integer page, per_page, total, total_pages;
    private List<GetUsersDataModel> data;
    private GetSupportModel support;
}
