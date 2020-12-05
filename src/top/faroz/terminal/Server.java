package top.faroz.terminal;

import top.faroz.gui.frame.ServerFrame;
import top.faroz.gui.panel.ServerPanel;
import top.faroz.util.PropUtil;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

/**
 * @ClassName Server
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/4 上午8:52
 * @Version 1.0
 **/
public class Server {
    //服务器只有一个，使用单例模式
    private JFrame frame;
    private static Server instance;
    private DatagramSocket socket;
    private static int port;
    private static final int buf_size = 1024;
    private static Set<String> clients = new HashSet<>();

    public static void iniServer(JFrame frame) {
        if (instance==null) {
            instance=new Server(frame);
        }
    }

    public static Server getInstance() {
        return instance;
    }

    private Server(JFrame frame) {
        this.frame=frame;
    }

    public void start() {
        ServerFrame serverFrame = (ServerFrame) frame;
        ServerPanel panel = (ServerPanel) serverFrame.getPanel();
        JTextArea jta = panel.getJta();

        //通过配置文件获取服务器端口号
        Properties prop = PropUtil.getProp();
        port=Integer.parseInt(prop.getProperty("server_port"));
        try {
            socket = new DatagramSocket(port);
            //GUI
            String info = "服务开启[" + socket.getLocalSocketAddress() + "]，等待客户端连接中...";
            System.out.println(info);
            jta.append(info+"\n");

            new MessageListener().start();
        } catch (IOException e) {
            // log
        }
    }

    /**
     * 服务端消息监听线程
     */
    class MessageListener extends Thread {
        ServerFrame serverFrame = (ServerFrame) frame;
        ServerPanel panel = (ServerPanel) serverFrame.getPanel();
        JTextArea jta = panel.getJta();

        @Override
        public void run() {
            try {
                DatagramPacket packet;
                String msg, client;
                // 循环监听消息，后面可以考虑加上flag规避死循环
                while (true) {
                    packet = new DatagramPacket(new byte[buf_size], buf_size);
                    msg = receiveMsg(packet); //获得数据包中的内容（已经转化为String）
                    // 解析出client
                    client = packet.getSocketAddress().toString();
                    // 不能再用TCP那一套了，只能通过消息来确定上线与否
                    // 因为UDP发了就不管了，所以默认只要发了消息就一定在线，下线只能通过心跳和主动退出

                    jta.append(msg);
                    if ("online".equals(msg)) {
                        clients.add(client);

                        String info = "客户端[" + client + "]连接成功，当前在线客户端" + clients.size() + "个";
                        jta.append(info+"\n");

                        sendMsg(0, "[系统消息]：欢迎" + client + "来到聊天室，当前共有" + clients.size() + "人在聊天", client);

                        /**
                         * 添加else if
                         * 当客户端点击关闭按钮的时候
                         * 向服务端发送特定信息
                         * 服务端将发送该信息的客户端
                         * 从客户端队列中删除
                         */
                    } else if("offline".equals(msg)) {
                        //去除退出用户信息
                        clients.remove(client);
                        String info = client + "已退出聊天室，当前剩余" + clients.size() + "人";
                        jta.append(info+"\n");
                    } else {
                        sendMsg(1, "[" + client + "]：" + msg, client);
                    }

                }
            } catch (IOException e) {
                // log
            }
        }
    }

    /**
     * 解析出client的IP
     *
     * @param address socket地址，如：/127.0.0.1:9524
     * @return IP
     */
    private String getIp(String address) {
        String[] array = address.split(":");
        String s = array[0];
        return s.substring(1);
    }

    /**
     * 解析出client的端口
     *
     * @param address socket地址，如：/127.0.0.1:9524
     * @return 端口
     */
    private int getPort(String address) {
        String[] array = address.split(":");
        String s = array[1];
        return Integer.valueOf(s);
    }

    /**
     * 发送消息
     *
     * @param type   消息类型（0、系统消息；1、用户消息）
     * @param msg    消息内容
     * @param client 客户端（用来作比对，是否跳过自己）
     * @throws IOException
     */
    private void sendMsg(int type, String msg, String client) throws IOException {
        if (type != 0) {
            System.out.println("处理消息：" + msg);
        }
        DatagramPacket send_packet;
        byte[] bytes;
        //为所有客户端进行全局广播，后面也可以通过这边的筛选，实现点对点通信
        for (String address : clients) {
            if (type != 0 && client.equals(address)) {//这里是为了排除发送消息的客户端
                continue;
            }
            // 将需要发送的消息，转换成byte数组
            bytes = msg.getBytes();
            // 组装发送包，需要指定：包内容、包长度、目标IP、目标端口
            send_packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(getIp(address)), getPort(address));
            // 发送
            socket.send(send_packet);
        }
    }

    /**
     * 接收消息，由于receive是阻塞方法，所以可以直接将packet包当参数传进来
     *
     * @param packet 接收的数据包
     * @return 消息内容
     * @throws IOException
     */
    private String receiveMsg(DatagramPacket packet) throws IOException {
        // 组装接收包，需要指定：包内容、包长度，这里的包内容只是先声明一个空数组，等待数据的填充
        // DatagramPacket packet = new DatagramPacket(new byte[buf_size], buf_size);
        socket.receive(packet);
        // 包内容都是字节数组，需要转换成字符串
        return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }

    public JFrame getFrame() {
        return frame;
    }

}
