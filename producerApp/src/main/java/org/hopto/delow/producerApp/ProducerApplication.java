package org.hopto.delow.producerApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ProducerApplication implements CommandLineRunner {

    public ProducerApplication(ProducerService producerService) {
        this.producerService = producerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    private final ProducerService producerService;

//    After app start we send these messages to kafka topic. Spring will serialize these messages as JSON and add type
//    value information to message headers so that it will be possible to deserialize them.
    @Override
    public void run(String... args) throws Exception {
        producerService.sendCreateMessage("First created");
        producerService.sendUpdateMessage("First updated");

        producerService.sendCreateMessage("Second created");
        producerService.sendUpdateMessage("Second updated");

        producerService.sendCreateMessage("Third created");
        producerService.sendUpdateMessage("Third updated");

        log.info("All sent");
    }

}
