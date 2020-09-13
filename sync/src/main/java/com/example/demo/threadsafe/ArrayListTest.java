package com.example.demo.threadsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author meihewang
 * @date 2020/09/12  18:21
 */
public class ArrayListTest {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("aa");
        list.add("aaa");

        String remove = list.remove(0);
        System.out.println(remove);


        Vector<String> vector = new Vector<>();
        vector.add("1");


        String s = "hello";
        String replace = s.replace("a", "b");
        String substring = s.substring(1, 2);


    }

}
