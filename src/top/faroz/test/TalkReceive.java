package top.faroz.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ClassName TalkReceive
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/2 上午9:08
 * @Version 1.0
 **/
public class TalkReceive implements Runnable{
    private DatagramSocket server;
    private String name;
    public TalkReceive(int port,String name) {
        this.name=name;
        try {
            server=new DatagramSocket(port);  //指定端口创建接收端
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {
            byte[] container=new byte[1024*60];  //创建接收容器
            DatagramPacket packet=new DatagramPacket(container, container.length);  //封装成DatagramPacket包裹
            try {
                server.receive(packet);  //阻塞式接收
                //分析数据
                byte[] datas=packet.getData();
                int len=packet.getLength();
                String msg=new String(datas,0,len);
                System.out.println(name+"说："+msg);
                if (msg.equals("bye")) {
                    System.out.println(name+"已下线。");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        server.close();
    }

}
