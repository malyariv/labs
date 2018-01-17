package test;

import dbList.DBSafeList;
import dbList.DBSmartList;
import dbMap.DBSafeMap;
import dbQueue.DBSafeQueue;
import dbSet.DBSet;
import testData.MyData;

import java.util.Arrays;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        String adr="/home/ivan/NetCracker/lab3/src/main/java/Data/";
        DBSafeList<String> set=new DBSafeList<String>(adr,false);

        set.add("1");
        set.add("2");
        set.remove("1");
        set.remove("2");
        set.add("1");
        set.remove("1");
        set.add("2");
        System.out.println(Arrays.toString(set.toArray()));


    }
}
