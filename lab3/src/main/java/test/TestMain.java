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
        List<String> list=new DBSafeList<>(adr,false);
//        List<String> list=new ArrayList<>();

        list.add("2");
        list.add("5");
        list.add("1");
        list.add("4");
        list.add("3");

        Collections.sort(list);

        System.out.println(list);




    }
}
