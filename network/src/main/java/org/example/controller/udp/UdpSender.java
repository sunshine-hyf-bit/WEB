package org.example.controller.udp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class UdpSender {

    private static final String IP_ADDRESS = "234.2.2.2";
    private static final int PORT = 1006;
    private static final int THREAD_COUNT = 10;
    private static final int MESSAGES_PER_SECOND = 60;
    private static final int DELAY_BETWEEN_MESSAGES_MS = 1000 / MESSAGES_PER_SECOND; // 计算每条消息之间的延迟

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREAD_COUNT);

    public void startSending() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            scheduler.scheduleAtFixedRate(this::sendMessages, 0, DELAY_BETWEEN_MESSAGES_MS, TimeUnit.MILLISECONDS);
        }
    }

    private void sendMessages() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(IP_ADDRESS);
            String json = generateJsonCoordinates(50); // 生成100个坐标
            byte[] buffer = json.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateJsonCoordinates(int count) throws Exception {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            coordinates.add(new Coordinate(random.nextInt(100), random.nextInt(100)));
        }
        return objectMapper.writeValueAsString(coordinates);
    }

    @Data
    @AllArgsConstructor
    public static class Coordinate {
        private int x;
        private int y;
    }
}
