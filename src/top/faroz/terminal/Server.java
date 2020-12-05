package top.faroz.terminal;

import com.sun.javafx.image.IntPixelGetter;
import jdk.nashorn.internal.ir.IfNode;
import sun.security.jca.GetInstance;
import top.faroz.threadListener.ServerMessageListener;
import top.faroz.util.ObjectUtil;
import top.faroz.util.PropUtil;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName Server
 * @Description 服务端
 * @Author FARO_Z
 * @Date 2020/12/4 上午9:41
 * @Version 1.0
 **/
public class Server {
    //服务端ip
    private static String server_ip=null;
    //服务端端口号
    private static int server_port=-1;
    //服务端套接字
    private static DatagramSocket server_socket = null;
    //记录所有在线客户端
    private List<String>clients=new ArrayList<String>();
    //包大小，因为不会发送比较大的数据，所以，就定1024
    private static final int package_size=1024;
    //服务器单例
    public static Server instance=new Server();

    public static Server getInstance() {
        return instance;
    }

    private Server() {
        Properties prop = PropUtil.getProp();
        server_ip=prop.getProperty("server_ip");
        server_port=Integer.parseInt(prop.getProperty("server_port"));
        try {
            server_socket=new DatagramSocket(server_port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过DatagramPackage获取String类型的消息
     * @param dp
     * @return
     */
    public String getMsg(DatagramPacket dp) {
        return (String)ObjectUtil.byteToObject(dp.getData());
    }

    /**
     * 发送消息
     */
    public void sendMsg(String msg,String client,int type) {
        //消息头
        String msgHead = "[" + client + "]说:\n";
        if (type!=0) msg=msgHead+"\t"+msg;//如果不是登陆信息，则添加消息头
        try {
            for (String s : clients) {
                if (client.equals(s)) continue;//不用向自己发送消息
                byte[] bytes = ObjectUtil.objectToByte(msg);
                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(getIP(s)),getPort(s));
                try {
                    server_socket.send(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启服务器，启动服务器监听
     */
    public void startServer() {
        /*
        向服务器GUI中，发送服务器开启的消息
         */
        new ServerMessageListener(instance).start();
    }

    /**
     * 通过localSocketAddress获取ip
     * 将 /127.0.0.1:9827 这样的消息，截取IP信息
     * @param info
     * @return
     */
    public String getIP(String info) {
        String[] split = info.split(":");
        return split[0].substring(1);
    }

    /**
     * 通过localSocketAddress获取端口号
     * 将 /127.0.0.1:9827 这样的消息，截取端口信息
     * @param info
     * @return
     */
    public int getPort(String info) {
        String[] split = info.split(":");
        return Integer.valueOf(split[1]);//将端口号转为int类型，发送
    }

    public static int getPackage_size() {
        return package_size;
    }

    public static DatagramSocket getServer_socket() {
        return server_socket;
    }

    public List<String> getClients() {
        return clients;
    }
}
