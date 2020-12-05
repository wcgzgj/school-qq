package top.faroz.start;

import top.faroz.gui.frame.ClientFrame;
import top.faroz.gui.frame.MyFrame;
import top.faroz.gui.listener.WindowCloseListener;
import top.faroz.terminal.Client;
import top.faroz.terminal.Server;

/**
 * @ClassName StartClient
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/4 上午9:40
 * @Version 1.0
 **/
public class StartClient {
    public static void main(String[] args) {
        Client client = new Client();
        ClientFrame frame = new ClientFrame(client);
        client.iniClient(frame);

        frame.addWindowListener(new WindowCloseListener(client));
        client.start();

        /**
         * 添加登录判断
         */

        client.getFrame().setVisible(true);
    }
}