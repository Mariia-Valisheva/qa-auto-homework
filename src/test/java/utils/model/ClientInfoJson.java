package utils.model;

public class ClientInfoJson {
    private String clientName;
    private String clientAddress;
    private boolean resident;
    private String[] accountTypes;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public boolean isResident() {
        return resident;
    }


    public String[] getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(String[] accountTypes) {
        this.accountTypes = accountTypes;
    }

}

