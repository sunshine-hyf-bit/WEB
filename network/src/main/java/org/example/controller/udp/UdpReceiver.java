package org.example.controller.udp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

@Component
public class UdpReceiver {

    private static final String MULTICAST_ADDRESS = "234.2.2.2";
    private static final int PORT = 1006;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void startReceiving() {
        try (MulticastSocket multicastSocket = new MulticastSocket(PORT)) {
            // 加入组播组
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            multicastSocket.joinGroup(group);

            byte[] buffer = new byte[1024]; // 创建接收缓冲区
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // 接收数据
                multicastSocket.receive(packet);
                String jsonString = new String(packet.getData(), 0, packet.getLength());

                // 验证 JSON 格式
                if (isValidJson(jsonString)) {
                   // System.out.println("Received valid JSON: " + jsonString);
                } else {
                    System.out.println("Received invalid JSON: " + jsonString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidJson(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return jsonNode != null; // 如果能成功解析，说明是有效的 JSON
        } catch (IOException e) {
            return false; // 解析失败，说明不是有效的 JSON
        }
    }
}
