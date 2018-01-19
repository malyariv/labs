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
        DBSafeList<String> list=new DBSafeList<>(adr,false);
//        List<String> list=new ArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        ListIterator<String> iterator=list.listIterator(0);
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        System.out.println(iterator.previous());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        iterator.set("hello");
        System.out.println(iterator.previous());
        System.out.println(iterator.next());

        System.out.println(list);




    }
}
