package com.example.demo.message;


import java.util.UUID;

public class MessageDto {
    private UUID id;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
