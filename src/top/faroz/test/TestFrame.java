package top.faroz.test;

import javax.swing.*;
import java.awt.*;

/**
 * @ClassName TestFrame
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午3:03
 * @Version 1.0
 **/
public class TestFrame extends JFrame {
    public TestFrame() {
        super();
        this.addWindowListener(new WindowListenerDemo());
        this.setVisible(true);
        this.setBounds(30,30,400,400);
    }
}
