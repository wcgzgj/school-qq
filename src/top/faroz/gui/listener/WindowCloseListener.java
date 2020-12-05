package top.faroz.gui.listener;

import top.faroz.terminal.Client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName WindowCloseListener
 * @Description 当客户端窗口关闭时
 * 会提醒服务器，用户退出
 * @Author FARO_Z
 * @Date 2020/12/5 下午1:50
 * @Version 1.0
 **/
public class WindowCloseListener extends WindowAdapter {
    private Client client;

    public WindowCloseListener(Client client) {
        this.client = client;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        String info = "offline";
        //向服务器发送特殊信息，表示下线
        this.client.sendMsg(info);
        System.exit(0);
    }
}
