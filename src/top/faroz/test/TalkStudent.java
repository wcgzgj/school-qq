package top.faroz.test;

/**
 * @ClassName TalkStudent
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/2 上午9:09
 * @Version 1.0
 **/
public class TalkStudent {
    public static void main(String[] args) {
        System.out.println("学生端启动中...");
        new Thread(new TalkReceive(6666, "老师")).start();  //接收
        new Thread(new TalkSend(9999, "localhost", 7777)).start();  //发送
    }
}
