package models.reqresmodel.getsingleresource;

import lombok.Data;
import models.reqresmodel.GetSupportModel;

@Data
public class GetSingleResourceModel {

    private GetSingleResourceDataModel data;
    private GetSupportModel support;
}
