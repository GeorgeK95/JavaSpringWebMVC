package carDealer.model.response;

import carDealer.model.response.UserResponseModel;

import java.util.Date;

/**
 * Created by George-Lenovo on 10/03/2018.
 */
public class LoggerResponseModel {
    private UserResponseModel user;

    private String operation;

    private String modifiedTable;

    private Date date;

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getModifiedTable() {
        return modifiedTable;
    }

    public void setModifiedTable(String modifiedTable) {
        this.modifiedTable = modifiedTable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
