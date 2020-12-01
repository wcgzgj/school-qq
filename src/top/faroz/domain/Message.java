package top.faroz.domain;

import java.io.Serializable;

/**
 * @ClassName Message
 * @Description 信息类
 * @Author FARO_Z
 * @Date 2020/12/1 下午5:28
 * @Version 1.0
 **/
public class Message implements Serializable {//需要实现该类，可序列化
    private String context;
    private int type;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
