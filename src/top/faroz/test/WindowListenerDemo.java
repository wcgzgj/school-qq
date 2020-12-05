package top.faroz.test;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName WindowListenerDemo
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午3:02
 * @Version 1.0
 **/
public class WindowListenerDemo extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);
        System.exit(0);
    }
}
