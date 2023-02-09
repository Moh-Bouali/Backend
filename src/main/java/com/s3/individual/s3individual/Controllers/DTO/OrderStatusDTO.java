package com.s3.individual.s3individual.Controllers.DTO;

public class OrderStatusDTO {
    private String content;

    public OrderStatusDTO() {
    }

    public OrderStatusDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
