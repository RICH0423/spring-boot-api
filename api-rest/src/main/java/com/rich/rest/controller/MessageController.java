package com.rich.rest.controller;

import com.rich.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author rich
 * @since 2018/3/9:下午3:28
 */
@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String getMessage() {
        return messageService.message();
    }
}
