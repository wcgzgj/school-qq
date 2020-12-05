package top.faroz.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @ClassName TalkSend
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/2 上午9:09
 * @Version 1.0
 **/
public class TalkSend implements Runnable{

    private DatagramSocket client;
    private String toIP;
    private int toPort;
    public TalkSend(int port,String toIP,int toPort) {
        this.toIP=toIP;
        this.toPort=toPort;
        try {
            client=new DatagramSocket(port);  //指定端口创建接收端
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String msg=br.readLine();  //接收BufferRead传来的数据
                byte[] datas=msg.getBytes();  //将数据转为字节数组
                //封装成DatagramPacket包裹
                DatagramPacket packet=new DatagramPacket(datas, datas.length, new InetSocketAddress(toIP, toPort));
                //发送包裹
                client.send(packet);
                if (msg.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //释放资源
        client.close();
    }
}
