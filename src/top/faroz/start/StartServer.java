package top.faroz.start;

import top.faroz.gui.frame.ServerFrame;
import top.faroz.terminal.Server;

/**
 * @ClassName StartServer
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/4 上午9:40
 * @Version 1.0
 **/
public class StartServer {
    public static void main(String[] args) {
        ServerFrame serverFrame = new ServerFrame();
        Server.iniServer(serverFrame);
        Server.getInstance().getFrame().setVisible(true);
        Server.getInstance().start();
    }
}
