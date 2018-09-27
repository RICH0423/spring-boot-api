package com.rich.rest.controller;

import com.rich.rest.aop.LogAction;
import com.rich.rest.controller.model.Content;
import com.rich.rest.utils.Constants;
import com.rich.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * @author rich
 * @since 2018/3/9:下午3:28
 */
@RestController
@RequestMapping(Constants.API_VERSION_1 + Constants.MESSAGE_ENTRY)
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String getMessage() {
        return messageService.message();
    }

    @LogAction("Aspect log")
    @PostMapping
    public String createMsg(@RequestBody Content content) {
        return messageService.createMsg(content.getMsg());
    }

}
