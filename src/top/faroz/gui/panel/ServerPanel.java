package top.faroz.gui.panel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @ClassName ServerPanel
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午1:22
 * @Version 1.0
 **/
public class ServerPanel extends JPanel {
    private JTextArea jta=new JTextArea();;
    private JScrollPane jsp=new JScrollPane(jta);
    public ServerPanel() {
        super();
        jta.setEditable(false);
        this.setLayout(new BorderLayout());
        this.add(jsp,BorderLayout.CENTER);
        jta.setLineWrap(true);
    }

    public JTextArea getJta() {
        return jta;
    }
}
