package top.faroz.gui.frame;

import top.faroz.gui.panel.ServerPanel;

import javax.swing.*;

/**
 * @ClassName ServerFrame
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午1:19
 * @Version 1.0
 **/
public class ServerFrame extends MyFrame{
    private JPanel panel;

    public ServerFrame() {
        super();
        this.setLocation(40,40);
        this.setSize(600,600);
        this.setTitle("服务器");
        //因为Client的界面在关闭的时候，还要执行某些操作，
        // 所以，默认关闭的方式不能放在父类
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panel=new ServerPanel();
        this.add(panel);
    }

    public JPanel getPanel() {
        return panel;
    }
}
