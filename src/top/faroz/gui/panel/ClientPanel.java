package top.faroz.gui.panel;

import top.faroz.terminal.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @ClassName ClientPanel
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午2:19
 * @Version 1.0
 **/
public class ClientPanel extends JPanel {
    private Client client;

    //上半部分只读，只能显示
    public JTextArea taUp =new JTextArea();
    public JScrollPane spUp=new JScrollPane(taUp);

    //下半部分可以写指令
    public JTextArea taDown =new JTextArea();
    public JScrollPane spDown=new JScrollPane(taDown);

    public ClientPanel(Client client) {
        super();
        this.setLayout(new BorderLayout());
        this.client=client;

        taUp.setLineWrap(true);
        taUp.setEditable(false);

        taDown.setLineWrap(true);

        this.add(spUp,BorderLayout.CENTER);
        this.add(spDown,BorderLayout.SOUTH);

        taDown.setPreferredSize(new Dimension(500,100));

        taDown.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code) {
                    case 10://回车键
                        String text = taDown.getText();
                        taDown.setText("");
                        text.replaceAll("\n","");
                        client.sendMsg(text);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JTextArea getTaUp() {
        return taUp;
    }

    public JTextArea getTaDown() {
        return taDown;
    }
}
