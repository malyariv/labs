package test;

import dbList.DBSafeList;
import dbList.DBSmartList;
import dbMap.DBSafeMap;
import dbQueue.DBSafeQueue;
import dbSet.DBSet;
import testData.MyData;

import java.util.*;

public class TestMain {
    public static void main(String[] args) {
        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        List<String> list=new DBSmartList<>(adr,false);
//        List<String> list=new ArrayList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");

        System.out.println(list);

        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));
        System.out.println(list.remove(0));




    }
}
