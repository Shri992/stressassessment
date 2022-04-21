package com.example.test.stressprediction_app.Model;

public class ResponseMessage {
    public int Status;
    public String Message;

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
}
