package com.example.demo.controller;

import com.example.demo.service.MessageService;
import com.example.demo.message.MessageDto;
import com.example.demo.message.MessageMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public List<MessageDto> getMessages() {
        return messageService.getMessages().stream().map(MessageMapper :: toDto).collect(Collectors.toList());
    }

    @PostMapping()
    public MessageDto saveMessage(@RequestBody MessageDto messageDto) {
        return MessageMapper.toDto(messageService.saveMessage(messageDto));
    }
}
