package top.faroz.terminal;

import top.faroz.gui.frame.ClientFrame;
import top.faroz.gui.listener.WindowCloseListener;
import top.faroz.gui.panel.ClientPanel;
import top.faroz.util.PropUtil;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;
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
    private DatagramSocket socket;
    private static final int buf_size = 1024;
    private static String server_ip;
    private static int server_port;
    private JFrame frame;
    private JTextArea jta;


    public Client() {}

    /**
     * 构造函数获取GUI中frame对象
     * 对panel中的组件进行操控
     * @param frame
     */
    public void iniClient(JFrame frame) {
        this.frame = frame;
        ClientFrame clientFrame = (ClientFrame) frame;
        ClientPanel clientPanel = (ClientPanel) clientFrame.getPanel();
        jta=clientPanel.getTaUp();
    }




    public void start() {
        Properties prop = PropUtil.getProp();
        server_ip=prop.getProperty("server_ip");
        server_port=Integer.parseInt(prop.getProperty("server_port"));
        try {
            // 创建套接字，打开端口(端口要自己生成)
            socket = new DatagramSocket(getPort());

            System.out.println("连接服务器成功，身份证：[" + socket.getLocalSocketAddress() + "]");

            // 由于不是TCP，所以需要手动发送上线的消息通知服务端
            sendMsg("online");


            // // 开启发送消息的线程
            // new SendMessageListener().start();
            //使用GUI,代替消息发送线程


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
    public void sendMsg(String msg) {
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

    // class SendMessageListener extends Thread {
    //     @Override
    //     public void run() {
    //         // 监听console输入
    //         Scanner scanner = new Scanner(System.in);
    //
    //         while (true) {
    //             sendMsg(scanner.next());
    //         }
    //     }
    // }

    class ReceiveMessageListener extends Thread {
        @Override
        public void run() {
            // 监听消息的接收，死循环
            while (true) {
                jta.append(receiveMsg()+"\n");
            }
        }
    }

    public String getClient() {
        return socket.getLocalSocketAddress().toString();
    }

    public JFrame getFrame() {
        return frame;
    }


}
