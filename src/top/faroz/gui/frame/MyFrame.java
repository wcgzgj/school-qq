package top.faroz.gui.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName MyFrame
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/1 上午11:07
 * @Version 1.0
 **/
public class MyFrame extends JFrame {
    public static MyFrame instance=new MyFrame();

    public static MyFrame getInstance() {
        return instance;
    }

    private MyFrame() throws HeadlessException {
        super();
        this.setLayout(new BorderLayout());
        this.setBounds(new Rectangle(100,100,400,800));
        this.setTitle("19180319张健 校园qq");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
