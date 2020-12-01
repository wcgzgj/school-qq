package top.faroz.test;

import java.io.IOException;
import java.net.*;

/**
 * @ClassName UDPSender
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/1 上午11:26
 * @Version 1.0
 **/
public class UDPSender {
    public static void main(String[] args) throws IOException, InterruptedException {

        // for (int i = 0; i < 20; i++) {
        //     receive();
        //     Thread.sleep(2000);
        //     send("客户端发送信息");
        // }
        send("客户端发送信息");

    }

    public static void send(String info) throws IOException {
        byte[] bytes = info.getBytes();
        //创建数据报
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length,
                InetAddress.getByName("127.0.0.1"),
                9090);
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
    }

    public static void receive() throws IOException {
        DatagramSocket ds = new DatagramSocket(7070);
        byte[] b = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b, 0, b.length);
        ds.receive(dp);

        byte[] info = dp.getData();
        String str = new String(info, 0, info.length);
        System.out.println(str);
    }
}
