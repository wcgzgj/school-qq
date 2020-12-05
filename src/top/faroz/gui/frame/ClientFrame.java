package top.faroz.gui.frame;

import top.faroz.gui.listener.WindowCloseListener;
import top.faroz.gui.panel.ClientPanel;
import top.faroz.gui.panel.ServerPanel;
import top.faroz.terminal.Client;

import javax.swing.*;

/**
 * @ClassName ClientFrame
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午2:09
 * @Version 1.0
 **/
public class ClientFrame extends MyFrame{
    private JPanel panel;
    private Client client;

    public ClientFrame(Client client) {
        super();
        this.setLocation(60,60);
        this.setSize(500,500);
        this.setTitle("客户端");
        //因为Client的界面在关闭的时候，还要执行某些操作，
        // 所以，默认关闭的方式不能放在父类
        // this.add(new WindowCloseListener())
        this.panel=new ClientPanel(client);
        this.add(panel);
    }

    public JPanel getPanel() {
        return panel;
    }
}
