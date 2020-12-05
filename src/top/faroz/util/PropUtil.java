package top.faroz.util;

import jdk.nashorn.internal.objects.annotations.Property;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName PropUtil
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/1 下午5:25
 * @Version 1.0
 **/
public class PropUtil {
    /**
     * 获取配置文件对象
     * @return
     */
    public static Properties getProp() {
        String f = "src/data.properties";
        Properties props = new Properties();
        try {
            props.load(new java.io.FileInputStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
