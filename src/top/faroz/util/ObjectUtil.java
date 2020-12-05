package top.faroz.util;

import java.io.*;

/**
 * @ClassName ObjectUtil
 * @Description 对象 字节数组互换
 * @Author FARO_Z
 * @Date 2020/12/1 下午5:48
 * @Version 1.0
 **/
public class ObjectUtil {
    public static byte[] objectToByte(Object o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public static Object byteToObject(byte[] b) {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        ObjectInputStream ois=null;
        try {
            ois = new ObjectInputStream(bais);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object o = null;
        try {
            o = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }
}
