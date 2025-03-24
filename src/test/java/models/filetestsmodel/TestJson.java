package models.filetestsmodel;

public class TestJson {

    private String paymentType;
    private Integer paymentPriority;
    private ClientInfoJson clientInfo;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentPriority() {
        return paymentPriority;
    }

    public void setPaymentPriority(Integer paymentPriority) {
        this.paymentPriority = paymentPriority;
    }

    public ClientInfoJson getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfoJson clientInfo) {
        this.clientInfo = clientInfo;
    }
}

