package com.example.demo.message;

public class MessageMapper {

    private MessageMapper() {
    }

    public static MessageDto toDto(Message message) {
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setValue(message.getValue());
        return messageDto;
    }
}
