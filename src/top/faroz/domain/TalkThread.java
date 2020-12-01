package top.faroz.domain;

import java.net.DatagramPacket;
import java.net.Socket;

/**
 * @ClassName TalkThread
 * @Description 聊天线程
 * @Author FARO_Z
 * @Date 2020/12/1 下午5:35
 * @Version 1.0
 **/
public class TalkThread extends Thread{
    private DatagramPacket dp;

    public TalkThread(DatagramPacket dp) {
        this.dp = dp;
    }

    @Override
    public void run() {


    }
}
