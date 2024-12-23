package org.example.controller.udp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class UdpReceiverInitializer {

    @Autowired
    private UdpReceiver udpReceiver;

    @PostConstruct
    public void init() {
        log.info("Initializing UDP receiver");
        new Thread(udpReceiver::startReceiving).start(); // 启动接收线程
    }
}

