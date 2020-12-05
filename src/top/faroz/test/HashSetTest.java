package top.faroz.test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName HashSetTest
 * @Description TODO
 * @Author FARO_Z
 * @Date 2020/12/5 下午2:06
 * @Version 1.0
 **/
public class HashSetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        for (String s : set) {
            System.out.println(s);
        }

        System.out.println();
        set.remove("b");
        for (String s : set) {
            System.out.println(s);
        }
    }
}
