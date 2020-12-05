package top.faroz.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName Client
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/4 上午8:53
 * @Version 1.0
 **/
public class Client {
    /**
     * socket套接字
     */
    private DatagramSocket socket;

    /**
     * 缓冲区大小（UDP受以太网限制，每一个包的最大体积是65507字节，约等于64K），本案例中不会发送很长的内容，所以直接1024就够用了
     * <p>
     * 为什么最大是65507?
     * 因为udp包头有2个byte用于记录包体长度. 2个byte可表示最大值为: 2^16-1 = 64K-1 = 65535
     * udp包头占8字节, ip包头占20字节, 65535-28 = 65507
     */
    private static final int buf_size = 1024;

    /**
     * 服务器的IP
     */
    private static final String server_ip = "127.0.0.1";

    /**
     * 服务器的端口
     */
    private static final int server_port = 5555;

    private void start() {
        try {
            // 创建套接字，打开端口(端口要自己生成)
            socket = new DatagramSocket(getPort());

            System.out.println("连接服务器成功，身份证：[" + socket.getLocalSocketAddress() + "]");

            // 由于不是TCP，所以需要手动发送上线的消息通知服务端
            sendMsg("online");

            // 开启发送消息的线程
            new SendMessageListener().start();
            // 开启接收消息的线程
            new ReceiveMessageListener().start();
        } catch (IOException e) {
            // log
        }
    }

    /**
     * UDP不会自动生成客户端的端口（确切来说，UDP是么有服务端和客户端的区分的），需要自己生成
     *
     * @return 4位数的端口
     */
    private int getPort() {
        Random random = new Random();
        String s = "";
        for (int i = 0; i < 4; i++) {
            s += random.nextInt(9) + 1;
        }
        return Integer.valueOf(s);
    }

    /**
     * 发送消息
     *
     * @param msg 消息内容
     */
    private void sendMsg(String msg) {
        try {
            // 将需要发送的消息，转换成byte数组
            byte[] bytes = msg.getBytes();
            // 组装发送包，需要指定：包内容、包长度、目标IP、目标端口
            DatagramPacket send_packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(server_ip), server_port);
            // 发送
            socket.send(send_packet);
        } catch (IOException e) {
            // log
        }
    }

    /**
     * 接收消息
     *
     * @return 消息内容
     */
    private String receiveMsg() {
        try {
            // 组装接收包，需要指定：包内容、包长度，这里的包内容只是先声明一个空数组，等待数据的填充
            DatagramPacket receive_packet = new DatagramPacket(new byte[buf_size], buf_size);
            socket.receive(receive_packet);
            // 包内容都是字节数组，需要转换成字符串
            return new String(receive_packet.getData(), receive_packet.getOffset(), receive_packet.getLength());
        } catch (IOException e) {
            // log
        }
        return null;
    }

    class SendMessageListener extends Thread {
        @Override
        public void run() {
            // 监听console输入
            Scanner scanner = new Scanner(System.in);
            while (true) {
                sendMsg(scanner.next());
            }
        }
    }

    class ReceiveMessageListener extends Thread {
        @Override
        public void run() {
            // 监听消息的接收，死循环
            while (true) {
                System.out.println(receiveMsg());
            }
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }
}
