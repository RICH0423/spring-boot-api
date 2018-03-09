package com.rich.service;

import static org.assertj.core.api.Assertions.*;

import com.rich.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest("service.message=Hello")
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void contextLoads() {
        assertThat(messageService.message()).isNotNull();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }

}
