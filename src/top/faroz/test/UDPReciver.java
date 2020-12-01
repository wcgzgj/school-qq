package top.faroz.test;

import top.faroz.domain.Message;
import top.faroz.util.ObjectUtil;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @ClassName UDPReciver
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/1 上午11:26
 * @Version 1.0
 **/
public class UDPReciver {

    public static DatagramSocket ds;
    static {
        try {
            ds = new DatagramSocket(9090);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        // for (int i = 0; i < 10; i++) {
        //     receive();
        //     Thread.sleep(2000);
        //     send("服务器发送信息");
        // }
        while (true) {
            receive();
        }
    }

    public static void send(String info) throws IOException {
        byte[] bytes = info.getBytes();
        //创建数据报
        DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length,
                InetAddress.getByName("127.0.0.1"),
                7070);
        DatagramSocket ds = new DatagramSocket();
        ds.send(dp);
    }

    public static void receive() throws IOException {
        byte[] b = new byte[1024];
        DatagramPacket dp = new DatagramPacket(b, 0, b.length);
        ds.receive(dp);

        byte[] info = dp.getData();
        Message mess = (Message) ObjectUtil.byteToObject(info);
        System.out.println(mess.getContext());
    }
}
