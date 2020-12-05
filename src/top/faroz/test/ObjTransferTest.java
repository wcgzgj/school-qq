package top.faroz.test;

import top.faroz.util.ObjectUtil;

import javax.xml.transform.Source;

/**
 * @ClassName ObjTransferTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/4 上午9:26
 * @Version 1.0
 **/
public class ObjTransferTest {
    public static void main(String[] args) {
        byte[] hellos = ObjectUtil.objectToByte("Hello");
        String str = (String) ObjectUtil.byteToObject(hellos);
        System.out.println(str);
    }
}
