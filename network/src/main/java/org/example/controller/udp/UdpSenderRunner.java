package org.example.controller.udp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class UdpSenderRunner implements CommandLineRunner {

    @Autowired
    private UdpSender udpSender;

    @Override
    public void run(String... args) throws Exception {
        log.info("Starting UDP sender");
        udpSender.startSending();
    }
}
