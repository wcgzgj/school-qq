package top.faroz.start;

import com.sun.org.apache.xml.internal.security.utils.JavaUtils;
import top.faroz.gui.frame.MyFrame;

import javax.swing.*;

/**
 * @ClassName BootStart
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/1 上午11:09
 * @Version 1.0
 **/
public class BootStart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MyFrame.getInstance().setVisible(true);
            }
        });
    }
}
