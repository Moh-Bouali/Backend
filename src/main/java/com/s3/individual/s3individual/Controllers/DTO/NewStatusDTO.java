package com.s3.individual.s3individual.Controllers.DTO;

public class NewStatusDTO {
    private String newStatusMessage;

    public NewStatusDTO() {
    }

    public NewStatusDTO(String newStatusMessage) {
        this.newStatusMessage = newStatusMessage;
    }

    public String getNewStatusMessage() {
        return newStatusMessage;
    }

    public void setName(String newStatusMessage) {
        this.newStatusMessage = newStatusMessage;
    }
}
