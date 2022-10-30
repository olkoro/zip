package com.example.demo.service;

import com.example.demo.message.Message;
import com.example.demo.message.MessageDto;
import com.example.demo.message.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Message saveMessage(MessageDto messageDto) {
        Message message = new Message();
        message.setValue(messageDto.getValue());
        return messageRepository.save(message);
    }
}
