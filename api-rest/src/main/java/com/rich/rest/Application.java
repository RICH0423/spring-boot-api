package com.rich.rest;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication(scanBasePackages = "com.rich")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(1);

    @Bean
    ApplicationRunner runner(MeterRegistry meterRegistry) {
        return arg -> {
            this.executorService.scheduleWithFixedDelay(
                    () -> meterRegistry.timer("transform-data-task").record(Duration.ofMillis((long) (Math.random() * 1000))),
                    500, 500, TimeUnit.MILLISECONDS);
        };
    }
}
