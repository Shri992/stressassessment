package com.example.test.stressprediction_app.Model;

public class ResponseLogin {

    public int Status;
    public String Message;
    public int LoginUserID;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getLoginUserID() {
        return LoginUserID;
    }

    public void setLoginUserID(int loginUserID) {
        LoginUserID = loginUserID;
    }
}
