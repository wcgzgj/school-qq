package top.faroz.test;

/**
 * @ClassName TalkTeacher
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/2 上午9:09
 * @Version 1.0
 **/
public class TalkTeacher {
    public static void main(String[] args) {
        System.out.println("教师端启动中...");
        new Thread(new TalkSend(8888, "localhost", 6666)).start();  //发送
        new Thread(new TalkReceive(7777, "学生")).start();  //接收
    }

}
