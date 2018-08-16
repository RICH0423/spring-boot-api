package com.rich.rest.controller;

import com.rich.rest.aop.LogAction;
import com.rich.rest.controller.model.Content;
import com.rich.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * @author rich
 * @since 2018/3/9:下午3:28
 */
@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String getMessage() {
        return messageService.message();
    }

    @LogAction("create msg")
    @PostMapping
    public String createMsg(@RequestBody Content content) {
        return messageService.createMsg(content.getMsg());
    }

}
