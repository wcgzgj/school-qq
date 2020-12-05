package top.faroz.threadListener;

import top.faroz.terminal.Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @ClassName ServerMessageListener
 * @Description 服务端监听器
 * 用来监听客户端的消息发送
 * @Author FARO_Z
 * @Date 2020/12/4 上午9:54
 * @Version 1.0
 **/
public class ServerMessageListener extends Thread{
    private Server server=null;

    public ServerMessageListener(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        DatagramSocket server_socket = server.getServer_socket();
        DatagramPacket dp=null;
        String msg;//传入的信息
        String client;//传信息的客户端
        while (true) {
            dp = new DatagramPacket(new byte[server.getPackage_size()], server.getPackage_size());
            msg=this.server.getMsg(dp);//直接通过dp获得消息
            client=server_socket.getLocalSocketAddress().toString();///127.0.0.1:9827获得这样的形式
            if ("online".equals(msg)) {//客户端一上线，会发送online消息
                server.getClients().add(client);//将在线的客户端添加到服务器中

                String info = "[系统消息]客户端[" + client.substring(1) + "]进入聊天室,当前在线人数:" + server.getClients().size();
                /*
                向服务器界面中传输 xxx登录 当前有n人登录的信息
                 */
                System.out.println(info);

                /*
                向全局发送消息，后面要将这个消息改成在GUI中显示
                 */
                server.sendMsg(info, client, 0);
            } else {
                server.sendMsg(msg,client,1);
            }
        }
    }
}
